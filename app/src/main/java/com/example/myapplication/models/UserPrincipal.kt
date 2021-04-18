package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class UserPrincipal(
    @PrimaryKey
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("password")
    @Expose
    val password: String
)
