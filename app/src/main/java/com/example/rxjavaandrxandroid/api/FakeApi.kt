package com.example.rxjavaandrxandroid.api

import com.example.rxjavaandrxandroid.models.FakeUser
import com.example.rxjavaandrxandroid.utils.Constants
import com.example.rxjavaandrxandroid.utils.LogUtil
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version FakeApi, v 0.1 Thu 6/22/2023 9:25 AM by Houwen Lie
 */
class FakeApi @Inject constructor() {

    fun getUserIds(): Observable<List<Int>> {
        return Observable.fromCallable {
            LogUtil.log("getUserIds")
            listOf(1,2,3,4,5)
        }.delay(Constants.MOCK_DELAY_SECONDS, TimeUnit.SECONDS)
    }

    fun getUser(id: Int): Observable<FakeUser> {
        return Observable.fromCallable {
            LogUtil.log("getUser with id = $id")
            FakeUser(id = id, name = "User #$id")
        }
    }
}