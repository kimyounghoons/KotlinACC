package com.kotlinacc.kimyounghoon.kotlinacc.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import com.kotlinacc.kimyounghoon.kotlinacc.fragments.ProgressDialogFragment

fun AppCompatActivity.setupProgressDialog(lifecycleOwner: LifecycleOwner,
                                          progressLiveEvent: MutableLiveData<Boolean>) {
    progressLiveEvent.observe(lifecycleOwner, Observer { it ->
        it?.let {
            if (it) {
                showProgressDialog()
            } else {
                dismissProgressDialog()
            }
        }
    })
}

fun AppCompatActivity.showProgressDialog() {
    val progressDialogFragment = ProgressDialogFragment()
    progressDialogFragment.show(supportFragmentManager, "progress")
}

fun AppCompatActivity.dismissProgressDialog() {
    (supportFragmentManager.findFragmentByTag("progress") as ProgressDialogFragment?)?.dismiss()
}