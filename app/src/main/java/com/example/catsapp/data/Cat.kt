package com.example.catsapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity
@Parcelize
@Serializable
data class Cat(
    @PrimaryKey val id: String = "",
    val url: String = ""
) : Parcelable
