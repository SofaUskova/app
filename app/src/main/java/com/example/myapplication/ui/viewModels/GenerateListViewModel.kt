package com.example.myapplication.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenerateListViewModel : ViewModel() {
    val list = MutableLiveData<List<String>>()

    fun generateListCities() {
        NetworkService.getJSONApi()
            ?.getAllCities()
            ?.enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    list.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    fun generateListColors() {
        NetworkService.getJSONApi()
            ?.getAllColors()
            ?.enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    list.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    fun generateListBreeds() {
        NetworkService.getJSONApi()
            ?.getAllBreeds()
            ?.enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    list.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}