package com.example.catsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catsapp.data.Cat

@Database(entities = [Cat::class], version = 1)
abstract class CatsDataBase : RoomDatabase() {
    abstract fun getCatsDao(): CatsDao
}