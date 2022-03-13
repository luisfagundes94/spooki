package com.luisfagundes.commons_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.commons_ui.databinding.MovieItemBinding
import com.luisfagundes.extensions.load
import com.luisfagundes.domain.model.Movie

class MovieAdapter(
    private val navigateToMovieDetails: (id: Int) -> Unit,
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, navigateToMovieDetails)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.count()

    class ViewHolder(
        private val binding: MovieItemBinding,
        private val navigateToMovieDetails: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            imgPoster.load(movie.imageUrl)
            imgPoster.contentDescription = movie.title
            root.setOnClickListener {
                navigateToMovieDetails(movie.id)
            }
        }
    }
}