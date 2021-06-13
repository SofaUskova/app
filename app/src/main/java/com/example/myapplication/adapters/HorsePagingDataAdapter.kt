package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.SalesContract

class HorsePagingDataAdapter :
    PagingDataAdapter<SalesContract, RecyclerView.ViewHolder>(UI_MODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return HorsePagingDataViewHolder.create(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SalesContract -> R.layout.cardview_horse
            else -> throw UnsupportedOperationException("HorsePagingDataAdapter: Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contract = getItem(position)
        contract?.let {
            (holder as HorsePagingDataViewHolder).bind(contract.horse, contract)
            holder.initListeners(contract.horse)
        }
    }

    companion object {
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<SalesContract>() {
            override fun areItemsTheSame(oldItem: SalesContract, newItem: SalesContract): Boolean {
                return (oldItem.horse.idHorse == newItem.horse.idHorse)
            }

            override fun areContentsTheSame(oldItem: SalesContract, newItem: SalesContract): Boolean =
                oldItem == newItem
        }
    }
}
