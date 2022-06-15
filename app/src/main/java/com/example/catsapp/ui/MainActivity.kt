package com.example.catsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.catsapp.R
import com.example.catsapp.data.Cat
import com.example.catsapp.databinding.ActivityMainBinding
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: CatVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = resources.getColor(R.color.background_light_gray)

        vm = ViewModelProvider(this)[CatVM::class.java]

        initViews()
    }

    private fun initViews() {
        setImage()
        binding.apply {
            btnLike.setOnClickListener {
                vm.onLike()
                setImage()
                vm.getFavoriteCats()
            }
            btnDislike.setOnClickListener {
                setImage()
                vm.getFavoriteCats()
            }
            btnFavorite.setOnClickListener {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                intent.putParcelableArrayListExtra(
                    "favorite", vm.getFavoriteCats() as ArrayList<out Parcelable>
                )
                startActivity(intent)
            }
        }
    }

    private fun setImage() {
        CoroutineScope(Job()).launch {
            binding.ivCat.setImageURI(vm.getCatFromApi().url)
        }
    }
}