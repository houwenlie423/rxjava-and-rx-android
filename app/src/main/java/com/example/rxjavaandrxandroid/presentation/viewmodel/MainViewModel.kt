package com.example.rxjavaandrxandroid.presentation.viewmodel

import android.util.Log
import com.example.rxjavaandrxandroid.presentation.base.RxViewModel
import com.example.rxjavaandrxandroid.shared.applySchedulers
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainViewModel, v 0.1 Thu 6/15/2023 3:29 PM by Houwen Lie
 */
class MainViewModel() : RxViewModel() {


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

    fun getUsers() {
//        Works -> executions of getUser() are done concurrently
        getUserIds()
            .flatMapIterable { it }
            .flatMap { userId -> getUser(userId) }
            .toList()
            .toObservable()
            .applySchedulers()
            .subscribe { users -> Log.d("RxApp", "Users = $users") }
            .addTo(disposeBag)

//        This also works concurrently
//        getUserIds()
//            .flatMap { userIds ->
//                Observable.zip(
//                    userIds.map { getUser(it) }.asIterable()
//                ) {
//                    it as List<User>
//                }
//            }.applySchedulers()
//            .subscribe { users -> Log.d("RxApp", "Users = $users") }
//            .addTo(disposeBag)

//        THIS ALSO  WORKS, I guess it's quite identical to first one
//        getUserIds()
//            .flatMap { userIds ->
//                Observable.fromIterable(userIds)
//                    .flatMap { userId -> getUser(userId) }
//                    .toList()
//                    .toObservable()
//            }
//            .applySchedulers()
//            .subscribe { users -> Log.d("RxApp", "Users = $users") }
//            .addTo(disposeBag)


//        Sequentially -> executions of getUser() are done sequentially one by one
//        getUserIds()
//            .flatMapIterable { it }
//            .concatMap { getUser(it) }
//            .toList()
//            .toObservable()
//            .applySchedulers()
//            .subscribe { users -> Log.d("RxApp", "Users = $users") }
//            .addTo(disposeBag)
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