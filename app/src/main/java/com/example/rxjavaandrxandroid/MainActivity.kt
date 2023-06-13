package com.example.rxjavaandrxandroid

import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.presentation.ViewBindingActivity

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version asd, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

}
