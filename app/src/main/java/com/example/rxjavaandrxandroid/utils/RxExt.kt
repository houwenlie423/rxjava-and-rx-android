package com.example.rxjavaandrxandroid.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider


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

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
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
                LogUtil.log("[${T::class.simpleName}] Success = $it")
                onSuccess(it)
            },
            onError = {
                LogUtil.log("[${T::class.simpleName}] Error = $it")
                onError(it)
            },
            onComplete = {}
        )
}

fun <T> Observable<T>.toSingleObservable(): Observable<T> = this.take(1)

fun <T> Observable<T>.subscribeByAutoDispose(
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onComplete: () -> Unit
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

//fun <T> Observable<T>.subscribeByAutoDispose(onNext: (T) -> Unit, onErr) = this.subscribeByAutoDispose(onNext, {}, {})
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