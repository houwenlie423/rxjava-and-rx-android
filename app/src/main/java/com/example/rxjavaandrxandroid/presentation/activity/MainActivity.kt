package com.example.rxjavaandrxandroid.presentation.activity

import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.presentation.base.RxViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun init() {
        Observable.interval(0L, 1L, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    binding.tvTitle.text = it.toString()
                }
            )
            .addTo(disposeBag)
    }
}
