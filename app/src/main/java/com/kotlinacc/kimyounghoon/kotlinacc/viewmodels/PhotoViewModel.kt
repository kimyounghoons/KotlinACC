package com.kotlinacc.kimyounghoon.kotlinacc.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kotlinacc.kimyounghoon.kotlinacc.factories.PhotoDataFactory
import com.kotlinacc.kimyounghoon.kotlinacc.interfaces.PhotoViewModelImpl
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.disposables.CompositeDisposable


class PhotoViewModel : ViewModel(), PhotoViewModelImpl {

    private var compositeDisposable = CompositeDisposable()
    private val photoDataFactory = PhotoDataFactory(compositeDisposable)
    private var photoLiveData: LiveData<PagedList<Photo>>? = null

    init {
        init()
    }

    private fun init() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        photoLiveData = LivePagedListBuilder(photoDataFactory, pagedListConfig)
            .build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    override fun refresh() {
        photoDataFactory.getMutableLiveData().value?.invalidate()
    }

    override fun getProgressLiveData(): MutableLiveData<Boolean> = photoDataFactory.getProgressLiveData()

    override fun getRefreshLiveData(): MutableLiveData<Void> = photoDataFactory.getRefreshLiveData()

    override fun getPhotoLiveData(): LiveData<PagedList<Photo>>? = photoLiveData

}