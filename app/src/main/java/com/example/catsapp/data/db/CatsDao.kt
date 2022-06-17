package com.example.catsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catsapp.data.Cat
import com.example.catsapp.data.FavoriteCat
import com.example.catsapp.data.db.CatsDataBase.Companion.CATS_TABLE
import com.example.catsapp.data.db.CatsDataBase.Companion.FAVORITE_CATS_TABLE

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cats: List<Cat>)

    @Query("SELECT * FROM $CATS_TABLE")
    fun getCats(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(cat: FavoriteCat)

    @Query("SELECT * FROM $FAVORITE_CATS_TABLE")
    fun getFavorite(): List<FavoriteCat>

    @Query("SELECT COUNT(url) FROM $CATS_TABLE")
    fun getRowCount(): Int

    @Query("DELETE FROM $CATS_TABLE")
    fun deleteAll()
}