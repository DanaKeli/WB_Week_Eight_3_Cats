package com.example.catsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catsapp.data.FavoriteCat
import com.example.catsapp.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var fm: FragmentManager
    private lateinit var adapter: FavoriteAdapter
    private lateinit var vm: CatVM
    private var favoriteList: List<FavoriteCat> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(requireActivity())[CatVM::class.java]
        fm = requireActivity().supportFragmentManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            favoriteList = vm.getFavorite()
            adapter = FavoriteAdapter(favoriteList, context)
            Log.i("dana", "FavList 1 >> $favoriteList")
        }
        binding.rvFavorite.layoutManager = GridLayoutManager(context, 3)
        binding.rvFavorite.adapter = adapter
    }
}