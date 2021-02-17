package com.example.myapplication.db

import androidx.room.*
import com.example.myapplication.models.Horse

@Dao
interface DaoHorse {
    @Insert
    fun insertAll(horse: List<Horse>)

    @Delete
    fun delete(horse: Horse)

    @Query("SELECT * FROM horses")
    fun getAll(): List<Horse>
}