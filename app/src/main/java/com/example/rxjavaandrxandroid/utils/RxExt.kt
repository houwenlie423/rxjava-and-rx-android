package com.example.rxjavaandrxandroid.utils

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxExt, v 0.1 Fri 6/16/2023 7:13 PM by Houwen Lie
 */

/*
   Observable<T> extensions
   ----------------------------------------------------------------------------------------
 */
fun <T> Observable<T>.applySchedulers(
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
): Observable<T> = this.subscribeOn(threadExecutor).observeOn(postThreadExecutor)

fun <T> Observable<T>.subscribeByLog(): Disposable {
    return this.subscribe(
        { LogUtil.log("onNext = $it") },
        { LogUtil.log("onError = $it") },
        { LogUtil.log("onComplete") },
    )
}

fun <T> Observable<T>.subscribeByAutoDispose(
    onNext: (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    this.subscribe(object : DisposableObserver<T>() {
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
/*
   Single<T> extensions
   ----------------------------------------------------------------------------------------
 */

fun <T> Single<T>.applySchedulers(
    threadExecutor: Scheduler = Schedulers.io(),
    postThreadExecutor: Scheduler = AndroidSchedulers.mainThread()
) = this.subscribeOn(threadExecutor).observeOn(postThreadExecutor)

fun <T> Single<T>.subscribeByLog(): Disposable {
    return this.subscribe(
        { LogUtil.log("onSuccess = $it") },
        { LogUtil.log("onError = $it") },
    )
}


/*
   Completable extensions
   ----------------------------------------------------------------------------------------
 */
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