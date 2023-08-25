package com.example.rxjavaandrxandroid.usecases

import com.example.rxjavaandrxandroid.api.FakeApi
import com.example.rxjavaandrxandroid.models.FakeUser
import io.reactivex.Observable
import io.reactivex.rxkotlin.flatMapIterable
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version LoopChain, v 0.1 Fri 6/23/2023 9:55 PM by Houwen Lie
 */
class LoopChain @Inject constructor(
    private val fakeApi: FakeApi
) {

    fun getUsersConcurrently(): Observable<List<FakeUser>> {
        return fakeApi.getUserIds()
//            .flatMapIterable { it }
            .flatMapIterable()
            .concatMapEager { userId -> fakeApi.getUser(userId) }
            .toList()
            .toObservable()
    }

    fun getUsersConcurrentLyWithZip(): Observable<List<FakeUser>> {
        return fakeApi.getUserIds()
            .flatMap { userIds ->
                Observable.zip(
                    userIds.map { fakeApi.getUser(it) }.asIterable()
                ) {
                    it as List<FakeUser>
                }
            }
    }

    fun getUsersSequentially(): Observable<List<FakeUser>> {
        return fakeApi.getUserIds()
            .flatMapIterable { it }
            .concatMap { userId -> fakeApi.getUser(userId) }
            .toList()
            .toObservable()
    }


}