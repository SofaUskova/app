package com.example.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.Seller
import com.example.myapplication.models.UserPrincipal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val currentSeller = MutableLiveData<Seller?>()

    fun getSeller(login: String) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.getSeller(login)
            ?.enqueue(object : Callback<Seller> {
                override fun onResponse(
                    call: Call<Seller>,
                    response: Response<Seller>
                ) {
                    if (response.isSuccessful) {
                        Log.e("entry", "onResponseSuccessful ${response.body().toString()}")
                        currentSeller.postValue(response.body())
                    } else {
                        Log.e("entry", "onResponseNotSuccessful ${response.body().toString()}")
                        currentSeller.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Seller>, t: Throwable) {
                    Log.e("entry", "onFailure $t")
                    currentSeller.postValue(null)
                }
            })
    }
}