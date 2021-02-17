package com.example.myapplication.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapters.HorsePagingDataAdapter
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.ui.searchHorses
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel : ViewModel() {
    private var searchJob: Job? = null
    val horsePagingDataAdapter: HorsePagingDataAdapter = HorsePagingDataAdapter()

    fun searchData(database: AppDatabase) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchHorses(database, viewModelScope = viewModelScope).collectLatest {
                horsePagingDataAdapter.submitData(it)
            }
        }
    }
}