package com.example.myapplication.models

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class FavoriteHorse(
    @SerializedName("repositoryKey")
    @Expose
    val repositoryKey: RepositoryKey
)
