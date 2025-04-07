package com.base.baseproject.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentHomeBinding
import com.base.module.base.BaseFragment

class HomeFragment : BaseFragment() {

    override val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}