package com.kotlinacc.kimyounghoon.kotlinacc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kotlinacc.kimyounghoon.kotlinacc.adapters.PhotoAdapter
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.ActivityMainBinding
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
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter()

        binding.recyclerView.apply {
            layoutManager =  LinearLayoutManager(this@MainActivity)
            adapter = photoAdapter
        }

        photoViewModel.photoLiveData?.observe(this, Observer {
            photoAdapter.submitList(it)
        })
    }

}
