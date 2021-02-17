package com.example.myapplication.ui

import androidx.paging.*
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.models.Horse
import com.example.myapplication.models.UiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun searchHorses(
    appDatabase: AppDatabase,
    sortByMore: Boolean? = null,
    viewModelScope: CoroutineScope
): Flow<PagingData<UiModel>> {
    return getSearchResultStream(appDatabase, sortByMore)
        .map { pagingData -> pagingData.map { UiModel.HorseItem(it) } }
        .map {
            it.insertSeparators<UiModel.HorseItem, UiModel> { before, after ->
                if (after == null) {
                    return@insertSeparators UiModel.SeparatorItem("конец неизбежен")
                }

                if (before == null) {
                    return@insertSeparators UiModel.SeparatorItem("начало списка")
                } else {
                    // no separator
                    null
                }
            }
        }
        .cachedIn(viewModelScope)
}

private fun getSearchResultStream(
    appDatabase: AppDatabase,
    sortByMore: Boolean? = null
): Flow<PagingData<Horse>> {
    return Pager(
        config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false,
            maxSize = 15
        ),
        pagingSourceFactory = {
            HorsePagingSource(appDatabase, sortByMore)
        }
    ).flow
}
