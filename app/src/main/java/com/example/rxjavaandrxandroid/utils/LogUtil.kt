package com.example.rxjavaandrxandroid.utils

import android.util.Log


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version LogUtil, v 0.1 Thu 6/22/2023 9:27 AM by Houwen Lie
 */
object LogUtil {

    private const val TAG = "RxApplication"

    fun log(message: String, addLineBreak: Boolean = false) {
        Log.d(TAG, message)
        if (addLineBreak) {
            Log.d(TAG, "------------------------------------")
        }
    }
}