package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import com.example.myapplication.adapters.HorsePagingDataViewHolder as HorsePagingDataViewHolder1

class HorsePagingDataAdapter :
    PagingDataAdapter<Horse, RecyclerView.ViewHolder>(UI_MODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return HorsePagingDataViewHolder1.create(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Horse -> R.layout.cardview_horse
            else -> throw UnsupportedOperationException("HorsePagingDataAdapter: Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val horse = getItem(position)
        horse.let {
            (holder as HorsePagingDataViewHolder1).bind(horse)
            holder.initListeners(horse)
        }
    }

    companion object {
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<Horse>() {
            override fun areItemsTheSame(oldItem: Horse, newItem: Horse): Boolean {
                return (oldItem.idHorse == newItem.idHorse)
            }

            override fun areContentsTheSame(oldItem: Horse, newItem: Horse): Boolean =
                oldItem == newItem
        }
    }
}
