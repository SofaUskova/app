package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class SalesContract(
    @PrimaryKey
    @SerializedName("idSalesContract")
    @Expose
    val idSalesContract: Int,
    @SerializedName("price")
    @Expose
    val price: String,
    @SerializedName("seller")
    @Expose
    val seller: Seller,
    @SerializedName("objectOfSales")
    @Expose
    val objectOfSales: Document
)
