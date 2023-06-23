package com.example.rxjavaandrxandroid.usecases

import android.util.Log
import com.example.rxjavaandrxandroid.utils.LogUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version HeavyOperation, v 0.1 Fri 6/23/2023 10:02 PM by Houwen Lie
 */
class HeavyOperation @Inject constructor() {

    fun getFibonacciJust(): Observable<Int> {
        return Observable.just(doSomethingHeavy(source = "Just"))
            .delay(3L, TimeUnit.SECONDS)
    }

    fun getFibonacciFromCallable(): Observable<Int> {
        return Observable.fromCallable { doSomethingHeavy(source = "fromCallable") }
            .delay(3L, TimeUnit.SECONDS)
    }

    private fun doSomethingHeavy(source: String) : Int {
        LogUtil.log("[$source] Current Thread = ${Thread.currentThread().name}")
        return fibRecursive(50)
    }

    private fun fibRecursive(num: Int) : Int {
        // Mock Heavy Operation
        return if(num <= 0) {
            0
        } else if(num == 1 || num == 2 ) {
            1
        } else {
            fibRecursive(num - 2) + fibRecursive(num - 1)
        }
    }
}