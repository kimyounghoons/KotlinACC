package com.kotlinacc.kimyounghoon.kotlinacc.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kotlinacc.kimyounghoon.kotlinacc.R
import com.kotlinacc.kimyounghoon.kotlinacc.adapters.PhotoAdapter
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.ActivityMainBinding
import com.kotlinacc.kimyounghoon.kotlinacc.extensions.setNetworkErrorDialog
import com.kotlinacc.kimyounghoon.kotlinacc.interfaces.PhotoViewModelImpl
import com.kotlinacc.kimyounghoon.kotlinacc.models.NetworkState
import com.kotlinacc.kimyounghoon.kotlinacc.viewmodels.PhotoViewModel

class MainActivity : BaseAppCompatActivity<ActivityMainBinding>() {

    private lateinit var photoViewModelImpl: PhotoViewModelImpl
    private lateinit var photoAdapter: PhotoAdapter

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getTitleId(): Int = R.string.activity_main_title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModelImpl = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        initAdapter()
        setRefreshListener()
        observeLiveData()
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = photoAdapter
        }
    }

    private fun setRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener { photoViewModelImpl.refresh() }
    }

    private fun observeLiveData() {
        binding.root.setNetworkErrorDialog(this@MainActivity, photoViewModelImpl.getNetworkStateLiveData())

        photoViewModelImpl.apply {
            photoViewModelImpl.getNetworkStateLiveData().observe(this@MainActivity, Observer {
                it?.apply {
                    if (this == NetworkState.LOADING) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })

            getPhotoLiveData()?.observe(this@MainActivity, Observer {
                photoAdapter.submitList(it)
            })

            getRefreshLiveData().observe(this@MainActivity, Observer {
                if (binding.swipeRefreshLayout.isRefreshing) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            })
        }
    }
}