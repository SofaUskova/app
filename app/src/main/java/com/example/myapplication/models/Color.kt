package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Color(
    @PrimaryKey
    @SerializedName("idColor")
    @Expose
    val idColor: Int,
    @SerializedName("color")
    @Expose
    val color: String
)
