package com.example.myapplication.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.Horse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdverbsListViewModel : ViewModel() {
    val adverbsHorses = MutableLiveData<List<Horse>>()

    fun getAdverbsHorses(id: Int) {
        NetworkService.getJSONApi()
            ?.getSellersHorses(id)
            ?.enqueue(object : Callback<List<Horse>> {
                override fun onResponse(
                    call: Call<List<Horse>>,
                    response: Response<List<Horse>>
                ) {
                    adverbsHorses.postValue(response.body())
                    Log.e("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<List<Horse>>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}