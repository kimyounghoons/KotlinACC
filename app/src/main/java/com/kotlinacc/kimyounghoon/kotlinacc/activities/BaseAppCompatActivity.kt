package com.kotlinacc.kimyounghoon.kotlinacc.activities

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinacc.kimyounghoon.kotlinacc.R

abstract class BaseAppCompatActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

}