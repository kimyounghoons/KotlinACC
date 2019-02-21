package com.kotlinacc.kimyounghoon.kotlinacc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kotlinacc.kimyounghoon.kotlinacc.adapters.PhotoAdapter
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.ActivityMainBinding
import com.kotlinacc.kimyounghoon.kotlinacc.extensions.setupProgressDialog
import com.kotlinacc.kimyounghoon.kotlinacc.viewmodels.PhotoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        initAdapter()
        setupProgressDialog(this, photoViewModel.getProgressLiveData())
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = photoAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener { photoViewModel.refresh() }

        photoViewModel.photoLiveData?.observe(this, Observer {
            photoAdapter.submitList(it)
            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
