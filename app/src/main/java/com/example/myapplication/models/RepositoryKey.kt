package com.example.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepositoryKey(
    @SerializedName("salesContract")
    @Expose
    val salesContract: SalesContract,
    @SerializedName("seller")
    @Expose
    val seller: Seller
)
