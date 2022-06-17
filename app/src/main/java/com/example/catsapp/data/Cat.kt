package com.example.catsapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catsapp.data.db.CatsDataBase.Companion.CATS_TABLE
import com.example.catsapp.data.db.CatsDataBase.Companion.DB_NAME
import com.example.catsapp.data.db.CatsDataBase.Companion.FAVORITE_CATS_TABLE
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = CATS_TABLE)
@Parcelize
@Serializable
data class Cat(
    @PrimaryKey
    val id: String = "",
    val url: String = ""
) : Parcelable

@Entity(tableName = FAVORITE_CATS_TABLE)
@Parcelize
@Serializable
data class FavoriteCat(
    @PrimaryKey
    val id: String = "",
    val url: String = ""
) : Parcelable

