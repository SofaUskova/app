package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Location(
    @PrimaryKey
    @SerializedName("idLocation")
    @Expose
    val idLocation: Int,
    @SerializedName("country")
    @Expose
    val country: String,
    @SerializedName("city")
    @Expose
    val city: String
)
