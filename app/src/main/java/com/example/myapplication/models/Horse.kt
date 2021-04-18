package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Horse(
    @PrimaryKey
    @SerializedName("idHorse")
    @Expose
    val idHorse: Int,
    @SerializedName("document")
    @Expose
    var document: Document,
    @SerializedName("image")
    @Expose
    val image: Image?,
    @SerializedName("allInform")
    @Expose
    val allInform: String?,
    @SerializedName("price")
    @Expose
    val price: String
)