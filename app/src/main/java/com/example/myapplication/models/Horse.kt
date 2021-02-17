package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horses")
data class Horse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "age")
    val age: String?,
    @ColumnInfo(name = "mother")
    val mother: String?,
    @ColumnInfo(name = "father")
    val father: String?,
    @ColumnInfo(name = "color")
    val color: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "price")
    val price: String?,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)