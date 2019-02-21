package com.kotlinacc.kimyounghoon.kotlinacc.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.kotlinacc.kimyounghoon.kotlinacc.NetworkState
import com.kotlinacc.kimyounghoon.kotlinacc.R

fun AppCompatActivity.setNetworkErrorDialog(
    lifecycleOwner: LifecycleOwner,
    toastLiveEvent: MutableLiveData<NetworkState>
) {
    toastLiveEvent.observe(lifecycleOwner, Observer {
        it?.msg?.apply {
            AlertDialog.Builder(this@setNetworkErrorDialog)
                .setMessage(this)
                .setPositiveButton(R.string.confirm) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .show()
        }
    })
}