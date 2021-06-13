package com.example.myapplication.ui.viewModels

import android.util.Log
import android.view.View
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.api.NetworkService
import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInformationFullViewModel : ViewModel() {

    fun addNewHorse(view: View, horse: Horse, price: String, seller: Seller) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addHorse(horse)
            ?.enqueue(object : Callback<Horse> {
                override fun onResponse(
                    call: Call<Horse>,
                    response: Response<Horse>
                ) {
                    if (response.isSuccessful) {
                        Log.e(
                            "addNewHorse",
                            "onResponse isSuccessful ${response.body().toString()}"
                        )
                        addNewSalesContract(view, response.body()!!, price, seller)
                    } else {
                        Log.e("addNewHorse", "onResponse ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<Horse>, t: Throwable) {
                    Log.e("addNewHorse", "onFailure $t")
                }
            })
    }

    private fun addNewSalesContract(view: View, horse: Horse, price: String, seller: Seller) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addSalesContract(
                SalesContract(
                    idSalesContract = 0,
                    price = price,
                    horse = horse,
                    seller = seller
                )
            )?.enqueue(object : Callback<SalesContract> {
                override fun onResponse(
                    call: Call<SalesContract>,
                    response: Response<SalesContract>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        NavHostFragment.findNavController(view.findFragment())
                            .navigate(R.id.action_navigation_add_full_information_to_navigation_search)
                    }
                    Log.e("onResponse", response.toString())
                }

                override fun onFailure(call: Call<SalesContract>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }
}