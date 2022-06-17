package com.example.catsapp.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapp.data.Cat
import com.example.catsapp.data.CatRepositoryImp
import com.example.catsapp.data.CatRepositoryImp.Companion.CATS_COUNT
import com.example.catsapp.data.FavoriteCat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatVM(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val repository = CatRepositoryImp(context)
    private var currentCat = Cat()
    private var id: Int = -1
    private var flag = 0

    private suspend fun onCreate() {
        repository.getCatsFromApi()
        repository.addCatsToDB()
    }

    // в бд загружается по 10 котиков
    suspend fun getCat(): Cat {
        id++
        if (flag == 0) {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                onCreate()
            }
            flag = 1
        }
        // при достижении последнего котика, список обновляется
        if (id == CATS_COUNT - 1) {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                repository.deleteAll()
                flag = 0
                onCreate()
                flag = 1
                id = 0
            }
        }
        withContext(viewModelScope.coroutineContext) {
            currentCat = repository.getCats()[id]
        }
        return currentCat
    }

    fun onLike() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addFavoriteToDB(currentCat)
        }
    }

    suspend fun getFavorite(): List<FavoriteCat> = repository.getFavorite()

}
