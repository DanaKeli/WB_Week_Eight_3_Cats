package com.example.catsapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Cat(
    val id: String = "",
    val url: String = ""
) : Parcelable
