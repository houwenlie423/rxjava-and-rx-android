package com.example.rxjavaandrxandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ViewBindingActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */
abstract class RxViewBindingActivity<VB : ViewBinding> : ComponentActivity() {

    abstract fun inflateViewBinding(): VB

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

    protected val disposeBag by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init(savedInstanceState)
        init()
    }

    open fun init() {
        // no implementation on base
    }

    open fun init(savedInstanceState: Bundle?) {
        // no implementation on base
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }

}