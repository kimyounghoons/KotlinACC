package com.kotlinacc.kimyounghoon.kotlinacc.interfaces

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.kotlinacc.kimyounghoon.kotlinacc.models.NetworkState
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo

interface PhotoViewModelImpl {
    fun refresh()
    fun getRefreshLiveData(): MutableLiveData<Void>
    fun getPhotoLiveData(): LiveData<PagedList<Photo>>?
    fun getNetworkStateLiveData(): MutableLiveData<NetworkState>
}