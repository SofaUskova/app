package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.models.Horse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class HorsePagingSource(
    private val appDatabase: AppDatabase,
    private val sortByMore: Boolean? = null
) : PagingSource<Int, Horse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Horse> {
        return try {
            val nextKey = params.key ?: 1

            val list = withContext(Dispatchers.IO) {
                //delay(2000)
                appDatabase.daoHorse().getAll()
            }

            LoadResult.Page(
                data = sortedData(sortByMore, list) ?: list,
                prevKey = if (nextKey == 1) null else nextKey - 1,
                nextKey = if (nextKey == 10) null else nextKey + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    private fun sortedData(sortByMore: Boolean?, list: List<Horse>): List<Horse>? {
        return sortByMore?.let {isSorted ->
            if (isSorted) {
                list.sortedBy { it.price?.toInt() }
            } else {
                list.sortedByDescending { it.price?.toInt() }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Horse>): Int? {
        TODO("Not yet implemented")
    }
}