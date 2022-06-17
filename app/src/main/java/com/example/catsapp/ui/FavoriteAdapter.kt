package com.example.catsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapp.data.FavoriteCat
import com.example.catsapp.databinding.FavoriteItemBinding
import com.facebook.drawee.backends.pipeline.Fresco

class FavoriteAdapter(private val cats: List<FavoriteCat>, private val context: Context?) :
    RecyclerView.Adapter<FavoriteVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)
        Fresco.initialize(context)
        return FavoriteVH(binding)
    }

    override fun onBindViewHolder(holder: FavoriteVH, position: Int) {
        holder.bind(cats[position])
    }

    override fun getItemCount(): Int = cats.size
}

class FavoriteVH(private val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FavoriteCat) {
        binding.ivSmallCat.setImageURI(item.url)
    }
}