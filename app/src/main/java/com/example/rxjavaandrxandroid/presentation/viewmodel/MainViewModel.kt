package com.example.rxjavaandrxandroid.presentation.viewmodel

import android.util.Log
import com.example.rxjavaandrxandroid.presentation.base.RxViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainViewModel, v 0.1 Thu 6/15/2023 3:29 PM by Houwen Lie
 */
class MainViewModel() : RxViewModel() {


    fun getUserIds(): Observable<List<Int>> {
        return Observable.fromCallable {
            Log.d("RxApp", "GET USER ID")
            listOf(1, 2, 3)
        }
            .delay(3, TimeUnit.SECONDS)
    }

}