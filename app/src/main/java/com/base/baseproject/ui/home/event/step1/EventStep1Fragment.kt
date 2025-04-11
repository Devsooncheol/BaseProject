package com.base.baseproject.ui.home.event.step1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventStep1Binding
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventStep1Fragment : BaseFragment() {
    override val viewModel: EventStep1ViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventStep1Binding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}