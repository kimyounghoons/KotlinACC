package com.kotlinacc.kimyounghoon.kotlinacc.datasources

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.kotlinacc.kimyounghoon.kotlinacc.Constants
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import com.kotlinacc.kimyounghoon.kotlinacc.networks.PhotoApi
import com.kotlinacc.kimyounghoon.kotlinacc.networks.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PhotoDataSource(private val compositeDisposable: CompositeDisposable, private val progressLiveData: MutableLiveData<Boolean>) : PageKeyedDataSource<Long, Photo>() {
    private val TAG = PhotoDataSource::class.java.simpleName
    private val photosApi: PhotoApi = RetrofitClient.getInstance().create(PhotoApi::class.java)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Photo>) {
        showProgress()
        compositeDisposable.add(
                photosApi.getPhotos(Constants.DEFAULT_PAGE, Constants.PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            callback.onResult(it, Constants.DEFAULT_PAGE, Constants.DEFAULT_PAGE + 1)
                            hideProgress()
                        }, {
                            Log.d(TAG, it.message)
                            hideProgress()
                        })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Photo>) {
        showProgress()
        compositeDisposable.add(
                photosApi.getPhotos(params.key, Constants.PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            val nextKey: Long? = if (it.isEmpty()) {
                                null
                            } else {
                                params.key + 1
                            }
                            callback.onResult(it, nextKey)
                            hideProgress()
                        }, {
                            Log.d(TAG, it.message)
                            hideProgress()
                        })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Photo>) {

    }

    private fun showProgress() {
        progressLiveData.postValue(true)
    }

    private fun hideProgress() {
        progressLiveData.postValue(false)
    }

}