package com.example.myapplication.adapters

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.SalesContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HorsePagingSource(
    private val isFavorite: Boolean,
    private val login: String
) : PagingSource<Int, SalesContract>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SalesContract> {
        val nextKey = params.key ?: 1

        val list = withContext(Dispatchers.IO) {
            if (isFavorite) getFavoriteHorse() else getHorses()
        }

        return LoadResult.Page(
            data = list,
            prevKey = if (nextKey == 1) null else nextKey - 1,
            nextKey = if (nextKey == 1) null else nextKey + 1
        )
    }

    private fun getFavoriteHorse(): List<SalesContract> {
        return try {
            NetworkService.getInstance()
                ?.getJSONApi()
                ?.getFavoriteHorses(login)
                ?.execute()
                ?.body()!!
        } catch (e: Exception) {
            Log.e("ExceptionGetFavHor", e.message ?: "")
            listOf()
        }
    }

    private fun getHorses(): List<SalesContract> {
        return try {
            NetworkService.getInstance()
                ?.getJSONApi()
                ?.getAllHorses()
                ?.execute()
                ?.body()!!
        } catch (e: Exception) {
            Log.e("ExceptionGetHorses", e.message ?: "")
            listOf()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SalesContract>): Int? {
        TODO("Not yet implemented")
    }
}