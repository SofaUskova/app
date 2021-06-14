package com.example.myapplication.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.FavoriteHorse
import com.example.myapplication.models.SalesContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInformationViewModel : ViewModel() {
    val currentHorse = MutableLiveData<SalesContract>()

    fun getDetailInformationHorse(id: Int) {
        NetworkService.getJSONApi()
            ?.getDetailInformHorse(id)
            ?.enqueue(object : Callback<SalesContract> {
                override fun onResponse(
                    call: Call<SalesContract>,
                    response: Response<SalesContract>
                ) {
                    if (response.isSuccessful) {
                        currentHorse.postValue(response.body())
                        Log.e("onResponse", "isSuccessful getDetailInformHorse ${response.body().toString()}")
                    } else {
                        Log.e("onResponse", "getDetailInformHorse ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<SalesContract>, t: Throwable) {
                    Log.e("onFailure", "getDetailInformHorse $t")
                }
            })
    }

    fun getHorseInFavorite(favoriteHorse: FavoriteHorse) {
        NetworkService.getJSONApi()
            ?.addFavoriteHorse(favoriteHorse)
            ?.enqueue(object : Callback<FavoriteHorse> {
                override fun onResponse(
                    call: Call<FavoriteHorse>,
                    response: Response<FavoriteHorse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse", "isSuccessful getHorseInFavorite ${response.body().toString()}")
                    } else {
                        Log.e("onResponse", "getHorseInFavorite ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<FavoriteHorse>, t: Throwable) {
                    Log.e("onFailure", "getHorseInFavorite $t")
                }
            })
    }
}