package com.luisfagundes.commons_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.commons_ui.R
import com.luisfagundes.commons_ui.databinding.MovieCategoryBinding
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.extensions.dp
import com.luisfagundes.extensions.readAs
import com.luisfagundes.utils.RecyclerViewLeftItemMargin

class MovieCategoryAdapter(
    private val navigateToMovieDetails: (id: Int) -> Unit,
) : RecyclerView.Adapter<MovieCategoryAdapter.ViewHolder>() {

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
        return ViewHolder(binding, navigateToMovieDetails)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.count()

    class ViewHolder(
        private val binding: MovieCategoryBinding,
        private val navigateToMovieDetails: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MovieCategory) = with(binding) {
            btnSeeAll.readAs<Button>()

            tvCategoryTitle.text = category.title
            tvCategoryTitle.contentDescription = category.title

            rvHorizontalMovies.contentDescription = root.context.getString(
                R.string.desc_movie_recycler_view
            )
            rvHorizontalMovies.setupHorizontalMoviesRecyclerView(binding.root.context)
            rvHorizontalMovies.adapter = MovieAdapter(navigateToMovieDetails, category.movieList)
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
            const val DEFAULT_LEFT_ITEM_MARGIN = 16
        }
    }
}