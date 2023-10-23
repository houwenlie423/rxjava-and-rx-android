package com.example.rxjavaandrxandroid

import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.usecases.LoopChain
import com.example.rxjavaandrxandroid.utils.applySchedulers
import com.example.rxjavaandrxandroid.utils.subscribeByAutoDispose
import com.example.rxjavaandrxandroid.utils.subscribeByLog
import com.example.rxjavaandrxandroid.utils.toSingleObservable
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var loopChain: LoopChain

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun init() {

        val sourceObservable = Observable.create { emitter -> emitter.onNext(1) }

        binding.btnExecute.setOnClickListener {
            sourceObservable
                .timeout(3L, TimeUnit.SECONDS)
                .applySchedulers()
                .subscribeByLog()
                .addTo(disposeBag)
        }
        println("fake commit ammend 123")
    }
}
