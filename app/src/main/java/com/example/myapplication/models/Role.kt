package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Role (
    @PrimaryKey
    @SerializedName("idRole")
    @Expose
    val idRole: Int = 1,
    @SerializedName("typeRole")
    @Expose
    val typeRole: String = "пользователь"
)