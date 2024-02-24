package com.example.rxjavaandrxandroid

import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.usecases.LoopChain
import com.example.rxjavaandrxandroid.utils.LogUtil
import com.example.rxjavaandrxandroid.utils.RetryBackOffStrategy
import com.example.rxjavaandrxandroid.utils.retry
import com.example.rxjavaandrxandroid.utils.subscribeByLog
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
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

        val sourceSingle = Single.fromCallable {
            LogUtil.log("init single")
            123
        }
            .delay(1, TimeUnit.SECONDS)
            .flatMap {
                LogUtil.log("MAU ERROR")
                Single.error<Int>(AzureJancokException())
            }

        binding.btnExecute.setOnClickListener {
            sourceSingle
                .retry(
                    initialDelay = 2000L,
                    condition = { error -> error is AzureJancokException },
                    backOffStrategy = RetryBackOffStrategy.EXPONENTIAL
                )
                .subscribeByLog()
                .addTo(disposeBag)
        }
    }
}

class AzureJancokException : Exception("APA AJA BOLEH")
