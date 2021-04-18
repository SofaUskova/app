package com.example.myapplication

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.Horse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HorsePagingSource(private val isFavorite: Boolean) : PagingSource<Int, Horse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Horse> {
        val nextKey = params.key ?: 1

        val list = withContext(Dispatchers.IO) {
           if(isFavorite) getFavoriteHorse() else getHorses()
        }

        return LoadResult.Page(
            data = list,
            prevKey = if (nextKey == 1) null else nextKey - 1,
            nextKey = if (nextKey == 5) null else nextKey + 1
        )
    }

    private fun getFavoriteHorse(): List<Horse> {
        //TODO seller id
        return  try {
            NetworkService.getInstance()
                ?.getJSONApi()
                ?.getFavoriteHorses(2)
                ?.execute()
                ?.body()!!
        } catch (e: Exception) {
            Log.e("ExceptionGetFavHor", e.message ?: "")
            listOf()
        }
    }

    private fun getHorses(): List<Horse> {
        return try {
            NetworkService.getInstance()
                ?.getJSONApi()
                ?.getAllHorses()
                ?.execute()
                ?.body()!!
        } catch (e: Exception) {
            Log.e("ExceptionGetHor", e.message ?: "")
            listOf()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Horse>): Int? {
        TODO("Not yet implemented")
    }
}