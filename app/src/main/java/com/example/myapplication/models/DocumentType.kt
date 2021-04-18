package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class DocumentType(
    @PrimaryKey
    @SerializedName("idDoc")
    @Expose
    val idDoc: Int,
    @SerializedName("docType")
    @Expose
    val docType: String?
)
