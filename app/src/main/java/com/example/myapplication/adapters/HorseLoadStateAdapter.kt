package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class HorseLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<HorseLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: HorseLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): HorseLoadStateViewHolder {
        return HorseLoadStateViewHolder.create(parent, retry)
    }
}
