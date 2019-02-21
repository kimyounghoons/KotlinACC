package com.kotlinacc.kimyounghoon.kotlinacc.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.kotlinacc.kimyounghoon.kotlinacc.R
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.FragmentProgressDialogBinding

class ProgressDialogFragment : BaseDialogFragment<FragmentProgressDialogBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_progress_dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dialog.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(0f)
        }
        isCancelable = false
        return binding.root
    }
}