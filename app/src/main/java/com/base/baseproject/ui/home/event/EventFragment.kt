package com.base.baseproject.ui.home.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventBinding
import com.base.baseproject.databinding.FragmentHiltBinding
import com.base.baseproject.ui.home.hilt.HiltViewModel
import com.base.baseproject.ui.home.hilt.sample.HiltConstructorInjectATypeClass
import com.base.baseproject.ui.home.hilt.sample.HiltConstructorInjectBTypeClass
import com.base.baseproject.ui.home.hilt.sample.HiltFiledInject
import com.base.baseproject.ui.home.hilt.sample.HiltModuleBindInterface
import com.base.baseproject.ui.home.hilt.sample.HiltModuleInjectATypeClass
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class EventFragment : BaseFragment() {

    override val viewModel: HiltViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}