package com.example.rxjavaandrxandroid

import android.content.Intent
import com.example.rxjavaandrxandroid.base.RxViewBindingActivity
import com.example.rxjavaandrxandroid.databinding.ActivitySecondaryBinding


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SecondaryActivity, v 0.1 Tue 6/27/2023 10:11 PM by Houwen Lie
 */
class SecondaryActivity : RxViewBindingActivity<ActivitySecondaryBinding>() {
    override fun inflateViewBinding() = ActivitySecondaryBinding.inflate(layoutInflater)

    override fun init() {
        binding.btnBack.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("codeValue", "42069")
                startActivity(this)
            }
        }
    }
}