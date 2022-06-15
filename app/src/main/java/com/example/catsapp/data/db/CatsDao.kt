package com.example.catsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catsapp.data.Cat

@Dao
interface CatsDao {

    @Insert
    fun insertAll(cats: List<Cat>)

    @Insert
    fun insertFavorite(cat: Cat)

    @Query("SELECT * FROM cat")
    fun getFavorite() : List<Cat>
}