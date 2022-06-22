package com.example.catsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.catsapp.R
import com.example.catsapp.databinding.FragmentCatBinding
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: CatVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fresco.initialize(context)
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(requireActivity())[CatVM::class.java]
        setImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        val navHostFragment = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val nc = navHostFragment.navController

        binding.apply {
            btnLike.setOnClickListener {
                vm.onLike()
                setImage()
            }
            btnDislike.setOnClickListener {
                setImage()
            }
            btnFavorite.setOnClickListener {
                nc.navigate(R.id.action_catFragment_to_favoriteFragment)
            }
            btnInfo.setOnClickListener {
                nc.navigate(R.id.action_catFragment_to_infoFragment)
            }
        }
    }

    private fun setImage() {
        CoroutineScope(Dispatchers.IO).launch {
            binding.ivCat.setImageURI(vm.getCat().url)
        }
    }
}