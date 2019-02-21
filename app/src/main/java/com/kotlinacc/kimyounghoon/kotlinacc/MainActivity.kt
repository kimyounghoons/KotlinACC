package com.kotlinacc.kimyounghoon.kotlinacc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kotlinacc.kimyounghoon.kotlinacc.activities.BaseAppCompatActivity
import com.kotlinacc.kimyounghoon.kotlinacc.adapters.PhotoAdapter
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.ActivityMainBinding
import com.kotlinacc.kimyounghoon.kotlinacc.extensions.setupProgressDialog
import com.kotlinacc.kimyounghoon.kotlinacc.interfaces.PhotoViewModelImpl
import com.kotlinacc.kimyounghoon.kotlinacc.viewmodels.PhotoViewModel

class MainActivity : BaseAppCompatActivity<ActivityMainBinding>() {

    private lateinit var photoViewModelImpl: PhotoViewModelImpl
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModelImpl = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        initAdapter()
        setRefreshListener()
        observeLiveData()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

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
        photoViewModelImpl.apply {
            setupProgressDialog(this@MainActivity, photoViewModelImpl.getProgressLiveData())

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