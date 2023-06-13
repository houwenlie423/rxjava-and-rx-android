package com.example.rxjavaandrxandroid.presentation

import androidx.viewbinding.ViewBinding

abstract class ViewBindingActivity<VB: ViewBinding> : AppComp() {

    abstract fun inflateViewBinding() : VB

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

}