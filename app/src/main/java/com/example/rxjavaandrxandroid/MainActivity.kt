package com.example.rxjavaandrxandroid

import android.content.Intent
import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.utils.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun init() {
        binding.btnExecute.setOnClickListener {
            getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    LogUtil.log("final result thread = ${Thread.currentThread().name}")
                    LogUtil.log("final result -- $result", addLineBreak = true)
                }
                .addTo(disposeBag)
        }
    }

    private fun getValue(): Observable<Int> {
        return Observable.timer(3L, TimeUnit.SECONDS)
            .map {
                LogUtil.log("getValue thread = ${Thread.currentThread().name}")
                1
            }
    }

    private fun getObservable(): Observable<String> {
        return Observable.create { emitter ->
            getValue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { num ->
                    LogUtil.log("getValue subscribe thread = ${Thread.currentThread().name}")
                    emitter.onNext("Value = $num")
                }
                .addTo(disposeBag)
        }
    }
}
