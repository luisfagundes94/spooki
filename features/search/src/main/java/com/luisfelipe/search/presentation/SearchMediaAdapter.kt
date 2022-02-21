package com.luisfelipe.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.domain.model.Media
import com.luisfelipe.search.databinding.SearchMediaItemBinding
import com.luisfelipe.search.extensions.loadPoster

class SearchMediaAdapter: RecyclerView.Adapter<SearchMediaAdapter.ViewHolder>() {

    private val medias = mutableListOf<Media>()

    fun updateList(medias: List<Media>) {
        this.medias.clear()
        this.medias.addAll(medias)

        notifyDataSetChanged()
    }

    fun clearMediaList() {
        this.medias.clear()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchMediaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medias[position])
    }

    override fun getItemCount() = medias.count()

    class ViewHolder(
        private val binding: SearchMediaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media) = with(binding) {
            this.imgPoster.loadPoster(media.imageUrl)
        }
    }
}