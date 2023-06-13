package com.example.rxjavaandrxandroid.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ViewBindingActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */
abstract class ViewBindingActivity<VB : ViewBinding> : ComponentActivity() {

    abstract fun inflateViewBinding(): VB

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

    protected val disposeBag by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        init(savedInstanceState, persistentState)
        init()
    }

    open fun init() {
        // no implementation on base
    }

    open fun init(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        // no implementation on base
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }

}