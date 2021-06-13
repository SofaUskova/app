package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.models.Horse
import com.example.myapplication.models.SalesContract

class FavoriteHorsePagingDataAdapter :
    PagingDataAdapter<SalesContract, HorsePagingDataViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorsePagingDataViewHolder {
        return HorsePagingDataViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HorsePagingDataViewHolder, position: Int) {
        val contract = getItem(position)
        contract?.let {
            holder.bind(it.horse, it)
            holder.initListenersFavorite(it.horse)
            holder.setFavoriteOn(it.horse)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<SalesContract>() {
            override fun areItemsTheSame(oldItem: SalesContract, newItem: SalesContract): Boolean =
                oldItem.horse.idHorse == newItem.horse.idHorse

            override fun areContentsTheSame(oldItem: SalesContract, newItem: SalesContract): Boolean =
                oldItem == newItem
        }
    }
}
