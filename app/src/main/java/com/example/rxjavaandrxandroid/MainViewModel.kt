package com.example.rxjavaandrxandroid

import com.example.rxjavaandrxandroid.api.TypicodeApi
import com.example.rxjavaandrxandroid.base.RxViewModel
import com.example.rxjavaandrxandroid.usecases.LoopChain
import com.example.rxjavaandrxandroid.utils.LogUtil
import com.example.rxjavaandrxandroid.utils.applySchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainViewModel, v 0.1 Thu 6/15/2023 3:29 PM by Houwen Lie
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val loopChain: LoopChain,
    private val typicodeApi: TypicodeApi
) : RxViewModel() {

    fun getUsers() {
        loopChain.getUsersConcurrentLyWithZip()
            .applySchedulers()
            .subscribeBy(
                onNext = {
                    LogUtil.log("Result = $it")
                },
                onError = {
                    LogUtil.log("Error = $it")
                }
            )
            .addTo(disposeBag)
    }

}

