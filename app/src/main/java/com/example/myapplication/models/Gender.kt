package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Gender(
    @PrimaryKey
    @SerializedName("idGender")
    @Expose
    val idGender: Int,
    @SerializedName("gender")
    @Expose
    val gender: String
)
