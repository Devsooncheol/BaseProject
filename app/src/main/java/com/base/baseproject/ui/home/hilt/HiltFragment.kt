package com.base.baseproject.ui.home.hilt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentHiltBinding
import com.base.module.base.BaseFragment

class HiltFragment : BaseFragment() {

    override val viewModel: HiltViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentHiltBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}