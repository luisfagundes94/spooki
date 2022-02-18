package com.luisfelipe.movies.presentation.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.extensions.load
import com.luisfelipe.movies.databinding.MovieItemBinding
import com.luisfelipe.movies.domain.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.count()

    class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            this.imgPoster.load(movie.imageUrl)
        }
    }
}