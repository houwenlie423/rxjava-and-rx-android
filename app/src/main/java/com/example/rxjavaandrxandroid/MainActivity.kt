package com.example.rxjavaandrxandroid

import android.content.Intent
import androidx.activity.viewModels
import com.example.rxjavaandrxandroid.databinding.ActivityMainBinding
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.utils.LogUtil
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version MainActivity, v 0.1 Tue 6/13/2023 7:05 PM by Houwen Lie
 */

@AndroidEntryPoint
class MainActivity : RxViewBindingActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun init() {
        // Due to bug described below, activity gets re-created and #onCreate gets executed again
        LogUtil.log("onCreate : $intent")
        binding.btnNavigate.setOnClickListener {
            Intent(this, SecondaryActivity::class.java).also {
                startActivity(it)
            }
        }
        processExtraData()
    }

    override fun onNewIntent(intent: Intent?) {
        // #onNewIntent is not executed despite having singleTop launchMode for this activity, Intent sender needs to also addFlag Intent.FLAG_ACTIVITY_CLEAR_TOP
        // See : https://issuetracker.google.com/issues/36928971
        super.onNewIntent(intent)

        // Solution from : http://www.helloandroid.com/tutorials/communicating-between-running-activities
        setIntent(intent)
        LogUtil.log("onNewIntent : $intent")
    }

    private fun processExtraData() {
        intent?.let { intent ->
            val codeValue = intent.getStringExtra("codeValue").orEmpty()
            if (codeValue.isNotEmpty()) updateTextWithCodeValue(codeValue)
        }
    }

    private fun updateTextWithCodeValue(codeValue: String) {
        binding.tvTitle.text = codeValue
    }

}
