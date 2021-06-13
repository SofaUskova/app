package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Seller(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("location")
    @Expose
    val location: Location,
    @SerializedName("userPrincipal")
    @Expose
    val userPrincipal: UserPrincipal,
    @SerializedName("image")
    @Expose
    val image: Image? = null
)
