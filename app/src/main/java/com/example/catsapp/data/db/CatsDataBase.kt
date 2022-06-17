package com.example.catsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catsapp.data.Cat
import com.example.catsapp.data.FavoriteCat

@Database(entities = [Cat::class, FavoriteCat::class], version = 1, exportSchema = true)
abstract class CatsDataBase : RoomDatabase() {

    abstract fun getCatsDao(): CatsDao

    companion object {

        const val DB_NAME = "cats_database"
        const val CATS_TABLE = "cats_table"
        const val FAVORITE_CATS_TABLE = "favorite_cats_table"

        @Volatile
        private var INSTANCE: CatsDataBase? = null

        fun getDatabase(context: Context): CatsDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CatsDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CatsDataBase::class.java,
                DB_NAME
            )
                .build()
        }
    }
}