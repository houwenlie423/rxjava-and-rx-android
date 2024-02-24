package com.example.rxjavaandrxandroid

import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.usecases.LoopChain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var loopChain: LoopChain

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun init() {

        binding.btnExecute.setOnClickListener {

        }
    }
}


