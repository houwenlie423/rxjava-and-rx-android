package com.example.rxjavaandrxandroid.presentation

import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version asd, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */
abstract class ViewBindingActivity<VB: ViewBinding> : ComponentActivity() {

    abstract fun inflateViewBinding() : VB

    protected val binding: VB by lazy(LazyThreadSafetyMode.NONE) { inflateViewBinding() }

}