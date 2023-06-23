package com.example.rxjavaandrxandroid

import android.util.Log
import com.example.rxjavaandrxandroid.base.RxViewModel
import com.example.rxjavaandrxandroid.usecases.HeavyOperation
import com.example.rxjavaandrxandroid.usecases.LoopChain
import com.example.rxjavaandrxandroid.utils.applySchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainViewModel, v 0.1 Thu 6/15/2023 3:29 PM by Houwen Lie
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val loopChain: LoopChain,
    private val heavyOperation: HeavyOperation
) : RxViewModel() {


}