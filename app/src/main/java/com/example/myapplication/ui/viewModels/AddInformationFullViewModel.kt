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

    fun addDocument(newDocument: Document, view: View, horse: Horse) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addDocument(newDocument)
            ?.enqueue(object : Callback<Document> {
                override fun onResponse(
                    call: Call<Document>,
                    response: Response<Document>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        addNewHorse(response.body()!!, view, horse)
                    }
                }

                override fun onFailure(call: Call<Document>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    fun addNewHorse(document: Document, view: View, horse: Horse) {
        horse.document = document
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addHorse(horse)
            ?.enqueue(object : Callback<Horse> {
                override fun onResponse(
                    call: Call<Horse>,
                    response: Response<Horse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("onResponse isSuccessful", response.body().toString())
                        addNewSalesContract(document, view, horse)
                    }
                    Log.e("onResponse", response.toString())
                }

                override fun onFailure(call: Call<Horse>, t: Throwable) {
                    Log.e("onFailure", t.toString())
                }
            })
    }

    private fun addNewSalesContract(document: Document, view: View, horse: Horse) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.addSalesContract(
                SalesContract(
                    idSalesContract = 1,
                    price = horse.price,
                    seller = Seller(
                        idSeller = 1,
                        name = "Киану Ривз",
                        phone = "89155077493",
                        location = horse.document.location,
                        login = UserPrincipal("login", "password"),
                        image = null,
                        favoriteHorses = listOf()
                    ),
                    objectOfSales = document
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