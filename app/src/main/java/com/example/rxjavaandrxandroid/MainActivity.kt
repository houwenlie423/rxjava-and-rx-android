package com.example.rxjavaandrxandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version asd, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
