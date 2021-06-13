package com.example.myapplication.adapters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.models.Horse
import com.example.myapplication.models.SalesContract
import kotlinx.coroutines.flow.Flow

fun searchHorses(isFavorite: Boolean): Flow<PagingData<SalesContract>> {
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
