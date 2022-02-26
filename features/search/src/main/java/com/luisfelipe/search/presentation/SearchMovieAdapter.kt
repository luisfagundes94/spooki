package com.luisfelipe.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.search.databinding.SearchMovieItemBinding
import com.luisfelipe.search.extensions.loadPoster

class SearchMovieAdapter: RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {

    private val medias = mutableListOf<Movie>()

    fun updateList(movies: List<Movie>) {
        this.medias.clear()
        this.medias.addAll(movies)

        notifyDataSetChanged()
    }

    fun clearMediaList() {
        this.medias.clear()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medias[position])
    }

    override fun getItemCount() = medias.count()

    class ViewHolder(
        private val binding: SearchMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            this.imgPoster.loadPoster(movie.imageUrl)
        }
    }
}