package com.example.myapplication.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapters.FavoriteHorsePagingDataAdapter
import com.example.myapplication.adapters.searchHorses
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel : ViewModel() {
    private var searchJob: Job? = null
    val horsePagingDataAdapter: FavoriteHorsePagingDataAdapter = FavoriteHorsePagingDataAdapter()

    fun searchData() {
//        searchJob?.cancel()
//        searchJob = viewModelScope.launch {
//            searchHorses(isFavorite = true).collectLatest {
//                horsePagingDataAdapter.submitData(it)
//            }
//        }
    }
}