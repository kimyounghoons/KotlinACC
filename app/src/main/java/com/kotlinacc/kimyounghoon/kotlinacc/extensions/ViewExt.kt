package com.kotlinacc.kimyounghoon.kotlinacc.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AlertDialog
import android.view.View
import com.kotlinacc.kimyounghoon.kotlinacc.R
import com.kotlinacc.kimyounghoon.kotlinacc.models.NetworkState

fun View.setNetworkErrorDialog(
        lifecycleOwner: LifecycleOwner,
        networkStateLiveData: MutableLiveData<NetworkState>
) {
    networkStateLiveData.observe(lifecycleOwner, Observer {
        it?.msg?.apply {
            AlertDialog.Builder(context)
                    .setMessage(this)
                    .setPositiveButton(R.string.confirm) { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .show()
        }
    })
}