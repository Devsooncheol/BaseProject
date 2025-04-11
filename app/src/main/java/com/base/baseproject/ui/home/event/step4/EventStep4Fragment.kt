package com.base.baseproject.ui.home.event.step4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventStep4Binding
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventStep4Fragment : BaseFragment() {
    override val viewModel: EventStep4ViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventStep4Binding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}