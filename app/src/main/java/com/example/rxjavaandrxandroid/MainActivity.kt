package com.example.rxjavaandrxandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
