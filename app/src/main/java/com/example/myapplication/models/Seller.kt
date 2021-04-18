package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Seller(
    @PrimaryKey
    @SerializedName("idSeller")
    @Expose
    val idSeller: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("location")
    @Expose
    val location: Location,
    @SerializedName("login")
    @Expose
    val login: UserPrincipal,
    @SerializedName("image")
    @Expose
    val image: Image?,
    @SerializedName("favoriteHorses")
    @Expose
    val favoriteHorses: List<Horse>?
)
