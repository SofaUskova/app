package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Breed(
    @PrimaryKey
    @SerializedName("idBreed")
    @Expose
    val idBreed: Int,
    @SerializedName("breed")
    @Expose
    val breed: String
)
