package com.example.myapplication.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapters.FavoriteHorsePagingDataAdapter
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.ui.getSearchResultStream
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel : ViewModel() {
    private var searchJob: Job? = null
    val horsePagingDataAdapter: FavoriteHorsePagingDataAdapter = FavoriteHorsePagingDataAdapter()

    fun searchData(database: AppDatabase) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getSearchResultStream(database).collectLatest {
                horsePagingDataAdapter.submitData(it)
            }
        }
    }
}