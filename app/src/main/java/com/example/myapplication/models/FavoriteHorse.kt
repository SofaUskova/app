package com.example.myapplication.models

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class FavoriteHorse(
    @SerializedName("idDoc")
    @Expose
    val horse: Horse,
    @SerializedName("docType")
    @Expose
    val seller: Seller
)
