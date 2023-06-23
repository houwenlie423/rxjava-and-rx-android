package com.example.rxjavaandrxandroid

import android.util.Log
import com.example.rxjavaandrxandroid.base.RxViewModel
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
    private val loopChain: LoopChain
) : RxViewModel() {


    fun getNumber() {
        getSingleJust()
            .applySchedulers()
            .subscribe { Log.d("RxApp", "onNext = $it") }
            .addTo(disposeBag)

//        getSingleFromCallable()
//            .applySchedulers()
//            .subscribe { Log.d("RxApp", "onNext = $it") }
//            .addTo(disposeBag)
    }

    private fun getSingleJust(): Observable<Int> {
        return Observable.just(doSomethingHeavy(source = "Just"))
            .delay(3L, TimeUnit.SECONDS)
    }

    private fun getSingleFromCallable(): Observable<Int> {
        return Observable.fromCallable { doSomethingHeavy(source = "fromCallable") }
            .delay(3L, TimeUnit.SECONDS)
    }


    private fun doSomethingHeavy(source: String) : Int {
        Log.d("RxApp", "[$source] Current Thread = ${Thread.currentThread().name}")
        return fibRecursive(50)
    }

    private fun fibRecursive(num: Int) : Int {
        return if(num <= 0) {
            0
        } else if(num == 1 || num == 2 ) {
            1
        } else {
            fibRecursive(num - 2) + fibRecursive(num - 1)
        }
    }

    private fun getUserIds(): Observable<List<Int>> {
        return Observable.fromCallable {
            Log.d("RxApp", "GET USER ID")
            listOf(1, 2, 3)
        }
            .delay(5, TimeUnit.SECONDS)
    }

    private fun getUser(userId: Int): Observable<User> {
        return Observable.fromCallable {
            Log.d("RxApp", "GET USER WITH USER ID = $userId")
            User(id = userId, name = "User #${userId}")
        }
            .delay(5, TimeUnit.SECONDS)
    }

}

data class User(
    val id: Int,
    val name: String
)