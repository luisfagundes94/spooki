package com.luisfelipe.movies.presentation.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.extensions.dp
import com.luisfelipe.movies.databinding.MovieCategoryBinding
import com.luisfelipe.movies.domain.model.MovieCategory
import com.luisfelipe.utils.RecyclerViewLeftItemMargin

class MovieCategoryAdapter: RecyclerView.Adapter<MovieCategoryAdapter.ViewHolder>() {

    private val categories = mutableListOf<MovieCategory>()

    fun updateCategories(categories: List<MovieCategory>) {
        this.categories.clear()
        this.categories.addAll(categories)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.count()

    class ViewHolder(
        private val binding: MovieCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MovieCategory) = with(binding) {
            tvCategoryTitle.text = category.title
            rvHorizontalMovies.setupHorizontalMoviesRecyclerView(binding.root.context)
            rvHorizontalMovies.adapter = MovieAdapter(category.movies)
        }

        private fun RecyclerView.setupHorizontalMoviesRecyclerView(itemViewContext: Context) {
            val layoutManager = LinearLayoutManager(
                itemViewContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            val decoration = RecyclerViewLeftItemMargin(DEFAULT_LEFT_ITEM_MARGIN.dp)

            setHasFixedSize(true)
            addItemDecoration(decoration)
            this.layoutManager = layoutManager
        }

        private companion object {
            const val DEFAULT_LEFT_ITEM_MARGIN = 32
        }
    }
}