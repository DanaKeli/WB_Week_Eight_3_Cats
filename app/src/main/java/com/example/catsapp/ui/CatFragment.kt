package com.example.catsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.catsapp.R
import com.example.catsapp.databinding.FragmentCatBinding
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!
    private lateinit var fm: FragmentManager
    private lateinit var vm: CatVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fresco.initialize(context)
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(requireActivity())[CatVM::class.java]
        fm = requireActivity().supportFragmentManager


        initViews()
        return binding.root
    }

    private fun initViews() {
        setImage()
        binding.apply {
            btnLike.setOnClickListener {
                vm.onLike()
                setImage()
            }
            btnDislike.setOnClickListener {
                setImage()
            }
            btnFavorite.setOnClickListener {
                fm.beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.main_container, FavoriteFragment())
                    .commit()
            }
        }
    }

    private fun setImage() {
        CoroutineScope(Dispatchers.IO).launch {
            binding.ivCat.setImageURI(vm.getCat().url)
        }
    }
}