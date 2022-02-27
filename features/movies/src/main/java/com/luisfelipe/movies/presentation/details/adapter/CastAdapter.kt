package com.luisfelipe.movies.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.domain.model.Cast
import com.luisfelipe.extensions.load
import com.luisfelipe.movies.databinding.CastItemBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val cast = mutableListOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder {
        val binding = CastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount() = cast.count()

    inner class ViewHolder(
        private val binding: CastItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) = with(binding) {
            imgActor.load(cast.imageUrl)
        }
    }
}