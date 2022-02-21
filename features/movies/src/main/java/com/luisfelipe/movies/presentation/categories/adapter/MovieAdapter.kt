package com.luisfelipe.movies.presentation.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.extensions.load
import com.luisfelipe.movies.databinding.MovieItemBinding
import com.luisfelipe.domain.model.Media

class MovieAdapter(
    private val media: List<com.luisfelipe.domain.model.Media>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(media[position])
    }

    override fun getItemCount() = media.count()

    class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: com.luisfelipe.domain.model.Media) = with(binding) {
            this.imgPoster.load(movie.imageUrl)
        }
    }
}