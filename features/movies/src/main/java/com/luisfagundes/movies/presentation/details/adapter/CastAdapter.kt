package com.luisfagundes.movies.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.extensions.loadPoster
import com.luisfagundes.movies.databinding.CastItemBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val cast = mutableListOf<Actor>()

    fun updateList(cast: List<Actor>)  {
        this.cast.clear()
        this.cast.addAll(cast)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount() = cast.count()

    inner class ViewHolder(
        private val binding: CastItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) = with(binding) {
            imgActor.loadPoster(actor.profilePath)
        }
    }
}