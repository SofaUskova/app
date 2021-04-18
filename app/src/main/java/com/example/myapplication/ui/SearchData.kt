package com.example.myapplication.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.models.Horse
import kotlinx.coroutines.flow.Flow

fun searchHorses(isFavorite: Boolean): Flow<PagingData<Horse>> {
    return Pager(
        config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false,
            maxSize = 15
        ),
        pagingSourceFactory = {
            HorsePagingSource(isFavorite)
        }
    ).flow
}
