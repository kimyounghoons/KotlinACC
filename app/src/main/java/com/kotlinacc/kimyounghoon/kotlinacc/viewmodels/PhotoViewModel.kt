package com.kotlinacc.kimyounghoon.kotlinacc.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kotlinacc.kimyounghoon.kotlinacc.PhotoDataFactory
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.disposables.CompositeDisposable


class PhotoViewModel : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var photoLiveData: LiveData<PagedList<Photo>>? = null
        private set

    init {
        init()
    }

    private fun init() {
        val photoDataFactory = PhotoDataFactory(compositeDisposable)

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
}