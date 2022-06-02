package com.example.catsapp.ui

import androidx.lifecycle.ViewModel
import com.example.catsapp.data.Cat
import com.example.catsapp.data.CatRepositoryImp

class CatVM : ViewModel() {

    private var currentCat = Cat()
    private val repository = CatRepositoryImp()
    private var favoriteCats: MutableList<Cat> = mutableListOf()

    suspend fun getCat(): Cat {
        currentCat = repository.getCat()[0]
        return currentCat
    }

    fun onLike() {
        favoriteCats.add(currentCat)
    }

    fun getFavoriteCats(): List<Cat> {
        return favoriteCats
    }
}