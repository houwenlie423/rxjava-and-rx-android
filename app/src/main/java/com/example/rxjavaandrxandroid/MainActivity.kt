package com.example.rxjavaandrxandroid

import android.content.Intent
import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun init() {
        binding.btnNavigate.setOnClickListener {
            Intent(this, SecondaryActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}
