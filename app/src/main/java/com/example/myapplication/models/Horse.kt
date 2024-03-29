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
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("height")
    @Expose
    var height: String,
    @SerializedName("yearBirth")
    @Expose
    var yearBirth: String,
    @SerializedName("organization")
    @Expose
    var organization: String?,
    @SerializedName("brand")
    @Expose
    var brand: String?,
    @SerializedName("marks")
    @Expose
    var marks: String?,
    @SerializedName("mother")
    @Expose
    var mother: String?,
    @SerializedName("father")
    @Expose
    var father: String?,
    @SerializedName("breed")
    @Expose
    var breed: Breed,
    @SerializedName("color")
    @Expose
    var color: Color,
    @SerializedName("gender")
    @Expose
    var gender: Gender,
    @SerializedName("location")
    @Expose
    var location: Location,
    @SerializedName("allInform")
    @Expose
    val allInform: String?
)