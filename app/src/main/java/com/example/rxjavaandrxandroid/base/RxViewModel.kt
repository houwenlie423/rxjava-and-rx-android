package com.example.rxjavaandrxandroid.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version RxViewModel, v 0.1 Wed 6/14/2023 4:21 PM by Houwen Lie
 */
abstract class RxViewModel() : ViewModel() {

    protected val disposeBag by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}