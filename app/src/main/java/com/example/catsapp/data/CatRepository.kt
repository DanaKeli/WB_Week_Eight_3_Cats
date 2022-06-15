package com.example.catsapp.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface CatRepository {

    suspend fun getCatFromApi(): List<Cat>

    suspend fun getCatFromDB(): List<Cat>

    suspend fun addCatsToDB()
}


class CatRepositoryImp : CatRepository {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/images/search?limit=50"
    }

    private val client = HttpClient(CIO)
    private val json = Json { ignoreUnknownKeys = true }
    private var result: MutableList<Cat> = mutableListOf()

    override suspend fun getCatFromApi(): List<Cat> = withContext(Dispatchers.IO) {
        val response: HttpResponse = client.get(BASE_URL)

        result = json.decodeFromString(response.body())
        result
    }

    override suspend fun getCatFromDB(): List<Cat> {
        return listOf()
    }

    override suspend fun addCatsToDB() {

    }
}