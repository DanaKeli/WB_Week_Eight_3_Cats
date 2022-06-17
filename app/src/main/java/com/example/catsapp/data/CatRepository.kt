package com.example.catsapp.data

import android.content.Context
import com.example.catsapp.data.db.CatsDataBase
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface CatRepository {

    suspend fun getCatsFromApi(): List<Cat>

    suspend fun addCatsToDB()

    suspend fun getCats(): List<Cat>

    suspend fun addFavoriteToDB(cat: Cat)

    suspend fun getFavorite(): List<FavoriteCat>

    suspend fun isDBCreated(): Boolean

    suspend fun deleteAll()
}

class CatRepositoryImp(context: Context) : CatRepository {

    companion object {
        const val CATS_COUNT = 10
        const val BASE_URL = "https://api.thecatapi.com/v1/images/search?limit=$CATS_COUNT"
    }

    private val client = HttpClient(CIO)
    private val json = Json { ignoreUnknownKeys = true }
    private var catsList: MutableList<Cat> = mutableListOf()
    private val catsDb by lazy { CatsDataBase.getDatabase(context).getCatsDao() }

    override suspend fun getCatsFromApi(): List<Cat> = withContext(Dispatchers.IO) {
        val response: HttpResponse = client.get(BASE_URL)
        catsList = json.decodeFromString(response.body())
        catsList

    }

    override suspend fun addCatsToDB() = withContext(Dispatchers.IO) {
        catsDb.insertAll(catsList)
    }

    override suspend fun getCats(): List<Cat> = withContext(Dispatchers.IO) {
        catsDb.getCats()
    }

    override suspend fun addFavoriteToDB(cat: Cat) {
        catsDb.addFavorite(FavoriteCat(id = cat.id, url = cat.url))
    }

    override suspend fun getFavorite(): List<FavoriteCat> = withContext(Dispatchers.IO) {
        catsDb.getFavorite()
    }

    override suspend fun isDBCreated(): Boolean = catsDb.getRowCount() != 0

    override suspend fun deleteAll() = catsDb.deleteAll()
}