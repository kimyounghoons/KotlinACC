package com.kotlinacc.kimyounghoon.kotlinacc.datasources

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.kotlinacc.kimyounghoon.kotlinacc.Constants
import com.kotlinacc.kimyounghoon.kotlinacc.NetworkState
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import com.kotlinacc.kimyounghoon.kotlinacc.networks.PhotoApi
import com.kotlinacc.kimyounghoon.kotlinacc.networks.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PhotoDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val refreshLiveData: MutableLiveData<Void>,
    private val networkStateLiveData: MutableLiveData<NetworkState>
) : PageKeyedDataSource<Long, Photo>() {
    private val photosApi: PhotoApi = RetrofitClient.getInstance().create(PhotoApi::class.java)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Photo>) {
        hideRefreshing()
        showProgress()
        compositeDisposable.add(
            photosApi.getPhotos(Constants.DEFAULT_PAGE, Constants.PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it, Constants.DEFAULT_PAGE, Constants.DEFAULT_PAGE + 1)
                    hideProgress()
                }, {
                    onFailedRequest(it.message)
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
                    onFailedRequest(it.message)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Photo>) {

    }

    private fun showProgress() {
        networkStateLiveData.postValue(NetworkState.LOADING)
    }

    private fun hideProgress() {
        networkStateLiveData.postValue(NetworkState.LOADED)
    }

    private fun onFailedRequest(msg: String?) {
        networkStateLiveData.postValue(NetworkState.error(msg))
    }

    private fun hideRefreshing() {
        refreshLiveData.postValue(null)
    }
}