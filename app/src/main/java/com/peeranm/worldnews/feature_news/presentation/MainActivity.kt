package com.peeranm.worldnews.feature_news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.peeranm.worldnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.bindBottomNavBar()
    }

    private fun ActivityMainBinding.bindBottomNavBar() {
        bottomNavigationView.setupWithNavController(navHost.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}