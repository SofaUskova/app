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
    val currentSeller = MutableLiveData<Seller>()

    fun entry(login: String) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.entry(login)
            ?.enqueue(object : Callback<UserPrincipal> {
                override fun onResponse(
                    call: Call<UserPrincipal>,
                    response: Response<UserPrincipal>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        getSeller(login)
                    }
                }

                override fun onFailure(call: Call<UserPrincipal>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

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
                        Log.e("onResponse isSuccessful", response.body().toString())
                        currentSeller.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Seller>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}