package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.Horse

@Database(entities = [Horse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoHorse(): DaoHorse
}