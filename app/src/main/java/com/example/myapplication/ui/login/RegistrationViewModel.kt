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

class RegistrationViewModel : ViewModel() {
    val isSuccessful = MutableLiveData<Boolean>()

    fun addSeller(seller: Seller) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addUser(seller.login)
            ?.enqueue(object : Callback<UserPrincipal> {
                override fun onResponse(
                    call: Call<UserPrincipal>,
                    response: Response<UserPrincipal>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        addNewSeller(seller)
                    }
                }

                override fun onFailure(call: Call<UserPrincipal>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    private fun addNewSeller(seller: Seller) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addNewSeller(seller)
            ?.enqueue(object : Callback<Seller> {
                override fun onResponse(
                    call: Call<Seller>,
                    response: Response<Seller>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        isSuccessful.postValue(true)
                    }
                }

                override fun onFailure(call: Call<Seller>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}