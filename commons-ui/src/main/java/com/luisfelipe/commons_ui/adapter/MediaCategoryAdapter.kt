package com.luisfelipe.commons_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.commons_ui.databinding.MediaCategoryBinding
import com.luisfelipe.extensions.dp
import com.luisfelipe.domain.model.MediaCategory
import com.luisfelipe.utils.RecyclerViewLeftItemMargin

class MediaCategoryAdapter: RecyclerView.Adapter<MediaCategoryAdapter.ViewHolder>() {

    private val categories = mutableListOf<MediaCategory>()

    fun updateCategories(categories: List<MediaCategory>) {
        this.categories.clear()
        this.categories.addAll(categories)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MediaCategoryBinding.inflate(
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
        private val binding: MediaCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MediaCategory) = with(binding) {
            tvCategoryTitle.text = category.title
            rvHorizontalMovies.setupHorizontalMoviesRecyclerView(binding.root.context)
            rvHorizontalMovies.adapter = MediaAdapter(category.mediaList)
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