package com.kotlinacc.kimyounghoon.kotlinacc.factories

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.kotlinacc.kimyounghoon.kotlinacc.datasources.PhotoDataSource
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.disposables.CompositeDisposable

class PhotoDataFactory(private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Long, Photo>() {
    private var mutableLiveData: MutableLiveData<PhotoDataSource> = MutableLiveData()
    private var progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var photoDataSource: PhotoDataSource? = null

    override fun create(): DataSource<Long, Photo> {
        photoDataSource = PhotoDataSource(compositeDisposable, progressLiveData)
        mutableLiveData.postValue(photoDataSource)
        return photoDataSource as PhotoDataSource
    }

    fun getMutableLiveData(): MutableLiveData<PhotoDataSource> {
        return mutableLiveData
    }

    fun getProgressLiveData(): MutableLiveData<Boolean> {
        return progressLiveData
    }
}