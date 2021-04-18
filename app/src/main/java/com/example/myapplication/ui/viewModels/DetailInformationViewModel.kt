package com.example.myapplication.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.Horse
import com.example.myapplication.models.Seller
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInformationViewModel : ViewModel() {

    val currentHorse = MutableLiveData<Horse>()
    val seller = MutableLiveData<Seller>()

    fun getDetailInformationHorse(id: Int) {
        NetworkService.getJSONApi()
            ?.getDetailInformHorse(id)
            ?.enqueue(object : Callback<Horse> {
                override fun onResponse(
                    call: Call<Horse>,
                    response: Response<Horse>
                ) {
                    currentHorse.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<Horse>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    fun getSeller(id: Int) {
        NetworkService.getJSONApi()
            ?.getSellerId(id)
            ?.enqueue(object : Callback<Seller> {
                override fun onResponse(
                    call: Call<Seller>,
                    response: Response<Seller>
                ) {
                    seller.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<Seller>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}