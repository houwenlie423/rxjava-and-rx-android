package com.example.rxjavaandrxandroid.shared

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Fri 6/16/2023 7:13 PM by Houwen Lie
 */

fun <T> Observable<T>.applySchedulers() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())