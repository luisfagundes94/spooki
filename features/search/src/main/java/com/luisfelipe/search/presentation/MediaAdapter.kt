package com.luisfelipe.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.extensions.load
import com.luisfelipe.domain.model.Media
import com.luisfelipe.search.databinding.MediaItemBinding

class MediaAdapter: RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    private val medias = mutableListOf<com.luisfelipe.domain.model.Media>()

    fun updateList(medias: List<com.luisfelipe.domain.model.Media>) {
        this.medias.clear()
        this.medias.addAll(medias)

        notifyDataSetChanged()
    }

    fun clearMediaList() {
        this.medias.clear()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MediaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medias[position])
    }

    override fun getItemCount() = medias.count()

    class ViewHolder(
        private val binding: MediaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(media: com.luisfelipe.domain.model.Media) = with(binding) {
            this.imgPoster.load(media.imageUrl)
        }
    }
}