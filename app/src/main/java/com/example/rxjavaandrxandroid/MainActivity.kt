package com.example.rxjavaandrxandroid

import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.utils.LogUtil
import com.example.rxjavaandrxandroid.utils.applySchedulers
import com.example.rxjavaandrxandroid.utils.subscribeByLogAutoDispose
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.rxkotlin.flatMapIterable
import kotlin.random.Random

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
            LogUtil.log("Starting")
//            INI BISA BROTHERS
            getTopServiceKeys()
                .flatMap { keys ->
                    Observable.zip(
                        keys.map { key ->
                            getService(key)
                                .onErrorReturnItem(Service(enable = false))
                        }
                    ) { results ->
                        results.map { it as Service }
                            .filter { it.enable }
                    }
                }
                .onErrorReturnItem(emptyList())
                .applySchedulers()
                .subscribeByLogAutoDispose(observableName = "Service List")
        }
    }

   private fun getTopServiceKeys(): Observable<List<String>> {
       return Observable.create { emitter -> emitter.onNext(listOf("request_payment", "product_catalog", "qris_business")) }
   }

    private fun getService(key: String): Observable<Service> {
//        return Observable.just(Service(id = Random.nextInt(), name = "Service - $key"))
        return Observable.create { emitter ->
            if(key == "product_catalog") {
                emitter.onError(NoSuchElementException("No such service exist"))
                return@create
            }
            emitter.onNext(Service(id = Random.nextInt().toString(), name = "Service - $key", enable = true))
        }
    }

}

data class Service(
    val id: String = "",
    val name: String = "",
    val enable: Boolean
)
