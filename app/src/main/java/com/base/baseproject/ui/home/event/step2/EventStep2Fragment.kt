package com.base.baseproject.ui.home.event.step2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventStep2Binding
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventStep2Fragment : BaseFragment() {
    override val viewModel: EventStep2ViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventStep2Binding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}