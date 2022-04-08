package com.luisfagundes.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.commons_ui.databinding.MovieItemBinding
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.extensions.loadPoster

class SearchMovieAdapter(
    private val navigateToMovieDetails: (Int) -> Unit
): RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun updateList(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)

        notifyDataSetChanged()
    }

    fun clearMediaList() {
        this.movies.clear()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(navigateToMovieDetails, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.count()

    class ViewHolder(
        private val navigateToMovieDetails: (Int) -> Unit,
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            this.imgPoster.loadPoster(
                url = movie.imageUrl,
                placeholder = com.luisfagundes.commons_ui.R.color.mine_shaft,
                error = com.luisfagundes.commons_ui.R.drawable.spooki_media_placeholder
            )
            root.setOnClickListener {
                navigateToMovieDetails(movie.id)
            }
        }
    }
}