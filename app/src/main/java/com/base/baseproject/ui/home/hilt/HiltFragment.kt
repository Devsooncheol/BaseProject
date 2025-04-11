package com.base.baseproject.ui.home.hilt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentHiltBinding
import com.base.baseproject.ui.home.event.EventViewModel
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
class HiltFragment : BaseFragment() {

    //override val viewModel: HiltViewModel by viewModels()
    override val viewModel by hiltNavGraphViewModels<HiltViewModel>(R.id.nav_graph_main)

    @Inject lateinit var hiltFiledInject: HiltFiledInject

    @Inject lateinit var hiltConstructorInjectATypeClass: HiltConstructorInjectATypeClass

    @Inject lateinit var hiltConstructorInjectBTypeClass: HiltConstructorInjectBTypeClass

    @Inject lateinit var hiltModuleInjectATypeClass: HiltModuleInjectATypeClass

    @Inject lateinit var hiltModuleBindInterface: HiltModuleBindInterface


    override fun getViewBinding(): ViewBinding {
        return FragmentHiltBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hiltFiledInject.showTestLog()

        hiltConstructorInjectATypeClass.showTestLog()
        hiltConstructorInjectBTypeClass.showTestLog()

        Timber.i(hiltModuleInjectATypeClass.showTestLog())
        Timber.i(hiltModuleInjectATypeClass.showTestLog2())
        Timber.i(hiltModuleBindInterface.showString())

        viewModel.test()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}