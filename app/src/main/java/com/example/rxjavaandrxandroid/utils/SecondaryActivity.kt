package com.example.rxjavaandrxandroid.utils

import android.content.Intent
import com.example.rxjavaandrxandroid.MainActivity
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivitySecondaryBinding


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SecondaryActivity, v 0.1 Fri 8/4/2023 10:42 PM by Houwen Lie
 */
class SecondaryActivity : RxViewBindingActivity<ActivitySecondaryBinding>() {

    override fun inflateViewBinding() = ActivitySecondaryBinding.inflate(layoutInflater)

    override fun init() {
        binding.btnBack.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                putExtra("AC_CODE_VALUE", "123")
                startActivity(this)
            }
        }
    }
}