package com.example.catsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catsapp.R
import com.example.catsapp.data.Cat
import com.example.catsapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = resources.getColor(R.color.background_light_gray)

        val favoriteList = intent.getParcelableArrayListExtra<Cat>("favorite")

        adapter = FavoriteAdapter(favoriteList as List<Cat>, this)
        binding.rvFavorite.layoutManager = GridLayoutManager(this, 3)
        binding.rvFavorite.adapter = adapter
    }
}