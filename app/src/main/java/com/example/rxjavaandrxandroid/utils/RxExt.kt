package com.example.rxjavaandrxandroid.utils

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Fri 6/16/2023 7:13 PM by Houwen Lie
 */

fun <T> Observable<T>.applySchedulers(): Observable<T> = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

inline fun <reified T> Observable<T>.execute(
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
) {
    this.applySchedulers()
        .subscribe(object: DisposableObserver<T>() {
            override fun onNext(t: T) {
                LogUtil.log("[${T::class.simpleName}] Success = $t")
                onSuccess(t)
                dispose()
            }

            override fun onError(e: Throwable) {
                LogUtil.log("[${T::class.simpleName}] Error = ${e.message}")
                onError(e)
                dispose()
            }

            override fun onComplete() {
                LogUtil.log("[${T::class.simpleName}] Complete")
                dispose()
            }

        })
}

fun <T> Observable<T>.collectAsSingle(): Single<T> {
    return Single.create { emitter ->
        subscribe(object: DisposableObserver<T>() {
            override fun onNext(t: T) {
                emitter.onSuccess(t)
                dispose()
            }

            override fun onError(e: Throwable) {
                emitter.onError(e)
                dispose()
            }

            override fun onComplete() {
                dispose()
            }

        })
    }
}

fun <T> Single<T>.applySchedulers() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

inline fun <reified T> Single<T>.execute(
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
) {
    this.toObservable().execute(onSuccess, onError)
}