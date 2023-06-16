package com.example.rxjavaandrxandroid.presentation.viewmodel

import android.util.Log
import com.example.rxjavaandrxandroid.presentation.base.RxViewModel


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainViewModel, v 0.1 Thu 6/15/2023 3:29 PM by Houwen Lie
 */
class MainViewModel : RxViewModel() {

    fun doSomething() {
        Log.d("RxApp","VIEW MODEL DO SOMETHING")
    }
}