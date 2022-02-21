package com.luisfelipe.commons_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.commons_ui.databinding.MediaItemBinding
import com.luisfelipe.extensions.load
import com.luisfelipe.domain.model.Media

class MediaAdapter(
    private val media: List<Media>
) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MediaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(media[position])
    }

    override fun getItemCount() = media.count()

    class ViewHolder(
        private val binding: MediaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Media) = with(binding) {
            imgPoster.load(movie.imageUrl)
            imgPoster.contentDescription = movie.title
        }
    }
}