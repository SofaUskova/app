package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.models.Horse

class FavoriteHorsePagingDataAdapter :
    PagingDataAdapter<Horse, HorsePagingDataViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorsePagingDataViewHolder {
        return HorsePagingDataViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HorsePagingDataViewHolder, position: Int) {
        val horse = getItem(position)
        horse.let {
            holder.bind(it)
            holder.initListenersFavorite(it!!)
            holder.setFavoriteOn(it)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Horse>() {
            override fun areItemsTheSame(oldItem: Horse, newItem: Horse): Boolean =
                oldItem.idHorse == newItem.idHorse

            override fun areContentsTheSame(oldItem: Horse, newItem: Horse): Boolean =
                oldItem == newItem
        }
    }
}
