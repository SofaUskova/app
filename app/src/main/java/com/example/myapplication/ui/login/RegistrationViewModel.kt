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
    val isSuccess = MutableLiveData<Boolean>()

    fun addSeller(seller: Seller) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addUser(seller.userPrincipal)
            ?.enqueue(object : Callback<UserPrincipal> {
                override fun onResponse(
                    call: Call<UserPrincipal>,
                    response: Response<UserPrincipal>
                ) {
                    if (response.isSuccessful) {
                        Log.e("addSeller", "onResponseSuccessful ${response.body().toString()}")
                        addNewSeller(seller)
                    } else {
                        Log.e("addSeller", "onResponseNotSuccessful $seller")
                        isSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<UserPrincipal>, t: Throwable) {
                    Log.e("addSeller", "onFailure $t")
                    isSuccess.postValue(false)
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
                        Log.e("addNewSeller", "onResponseSuccessful ${response.body().toString()}")
                        isSuccess.postValue(true)
                    } else {
                        Log.e(
                            "addNewSeller",
                            "onResponseNotSuccessful ${response.body().toString()}"
                        )
                        isSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<Seller>, t: Throwable) {
                    Log.e("addNewSeller", "onFailure $t")
                    isSuccess.postValue(false)
                }
            })
    }
}