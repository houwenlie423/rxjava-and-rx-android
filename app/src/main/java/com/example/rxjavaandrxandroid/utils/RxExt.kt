package com.example.rxjavaandrxandroid.utils

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Fri 6/16/2023 7:13 PM by Houwen Lie
 */

fun <T> Observable<T>.applySchedulers(
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
): Observable<T> = this.subscribeOn(threadExecutor).observeOn(postThreadExecutor)

fun <T> Observable<T>.subscribeByAutoDispose(
    onNext: (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    this.subscribe(object: DisposableObserver<T>() {
        override fun onNext(t: T) {
            onNext(t)
            dispose()
        }

        override fun onError(e: Throwable) {
            onError(e)
            dispose()
        }

        override fun onComplete() {
            onComplete()
            dispose()
        }
    })
}

inline fun <reified T> Observable<T>.execute(
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
) {
    this.applySchedulers(threadExecutor, postThreadExecutor)
        .subscribeByAutoDispose(
            onNext = {
                LogUtil.log("[${T::class.simpleName}] Success = $t")
                onSuccess(it)
            },
            onError = {
                LogUtil.log("[${T::class.simpleName}] Success = $t")
                onError(it)
            }
        )
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

fun <T> Single<T>.applySchedulers(
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
) = this.subscribeOn(threadExecutor).observeOn(postThreadExecutor)

inline fun <reified T> Single<T>.execute(
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
) {
    this.toObservable().execute(onSuccess, onError, threadExecutor, postThreadExecutor)
}

fun Completable.applySchedulers(
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
) = this.subscribeOn(threadExecutor).observeOn(postThreadExecutor)

fun executeInBackground(action: () -> Unit) {
    Completable.fromAction(action)
        .applySchedulers()
        .subscribe(object : DisposableCompletableObserver() {
            override fun onComplete() {
                LogUtil.log("[$action] Complete")
                dispose()
            }

            override fun onError(e: Throwable) {
                LogUtil.log("[$action] Complete")
                dispose()
            }
        })
}