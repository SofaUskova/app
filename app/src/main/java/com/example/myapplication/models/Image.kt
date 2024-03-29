package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Image(
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("horse")
    @Expose
    val horse: Horse
)
