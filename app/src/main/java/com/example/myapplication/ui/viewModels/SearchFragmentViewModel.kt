package com.example.myapplication.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapters.HorsePagingDataAdapter
import com.example.myapplication.adapters.searchHorses
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragmentViewModel : ViewModel() {
    private var searchJob: Job? = null
    val horsePagingDataAdapter: HorsePagingDataAdapter = HorsePagingDataAdapter()

    fun getAllHorses() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchHorses(isFavorite = false).collectLatest {
                horsePagingDataAdapter.submitData(it)
            }
        }
    }

    fun sortedData() {
//        searchJob?.cancel()
//        searchJob = viewModelScope.launch {
//            searchHorses(isFavorite = false).collectLatest {
//                horsePagingDataAdapter.notifyDataSetChanged()
//                horsePagingDataAdapter.submitData(it)
//            }
//        }
    }
}