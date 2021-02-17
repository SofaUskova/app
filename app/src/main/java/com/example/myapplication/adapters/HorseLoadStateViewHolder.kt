package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HorseLoadStateViewHolder(
    private val retry: () -> Unit,
    view: View
) : RecyclerView.ViewHolder(view) {
    private var errorMess: TextView = view.findViewById(R.id.load_state_errorMessage)
    private var retryButton: Button = view.findViewById(R.id.load_state_retry)
    private var progressBar: ProgressBar = view.findViewById(R.id.load_state_progress)

    init {
        retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMess.text = loadState.error.localizedMessage
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState is LoadState.Error
        errorMess.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): HorseLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading_recycler, parent, false)
            return HorseLoadStateViewHolder(retry, view)
        }
    }
}