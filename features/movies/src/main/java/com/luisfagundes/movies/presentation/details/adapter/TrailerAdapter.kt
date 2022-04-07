package com.luisfagundes.movies.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.Trailer
import com.luisfagundes.movies.databinding.TrailerItemBinding
import com.luisfagundes.movies.utils.loadThumbnail

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    private val trailers = mutableListOf<Trailer>()

    fun updateList(trailers: List<Trailer>)  {
        this.trailers.clear()
        this.trailers.addAll(trailers)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrailerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trailers[position])
    }

    override fun getItemCount() = trailers.count()

    inner class ViewHolder(
        private val binding: TrailerItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(trailer: Trailer) = with(binding) {
            imgTrailer.loadThumbnail(trailer.youtubeUrl)
        }
    }
}