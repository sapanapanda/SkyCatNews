package com.example.catnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catnews.CatNewsApplication
import com.example.catnews.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CatNewsApplication).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}