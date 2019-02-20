package com.kotlinacc.kimyounghoon.kotlinacc

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.disposables.CompositeDisposable

class PhotoDataFactory(val compositeDisposable: CompositeDisposable) : DataSource.Factory<Long, Photo>() {
    private var mutableLiveData: MutableLiveData<PhotoDataSource> = MutableLiveData()
    private var photoDataSource: PhotoDataSource? = null

    override fun create(): DataSource<Long, Photo> {
        photoDataSource = PhotoDataSource(compositeDisposable)
        mutableLiveData.postValue(photoDataSource)
        return photoDataSource as PhotoDataSource
    }

    fun getMutableLiveData(): MutableLiveData<PhotoDataSource> {
        return mutableLiveData
    }
}