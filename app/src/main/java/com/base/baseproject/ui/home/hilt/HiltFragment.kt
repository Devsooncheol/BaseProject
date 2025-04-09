package com.base.baseproject.ui.home.hilt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentHiltBinding
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HiltFragment : BaseFragment() {

    override val viewModel: HiltViewModel by viewModels()

    @Inject lateinit var hiltFiledInject: HiltFiledInject

    @Inject lateinit var hiltConstructorInjectATypeClass: HiltConstructorInjectATypeClass

    @Inject lateinit var HiltConstructorInjectBTypeClass: HiltConstructorInjectBTypeClass

    @Inject lateinit var hiltModuleInjectATypeClass: HiltModuleInjectATypeClass


    override fun getViewBinding(): ViewBinding {
        return FragmentHiltBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hiltFiledInject.showTestLog()

        hiltConstructorInjectATypeClass.showTestLog()
        HiltConstructorInjectBTypeClass.showTestLog()

        Timber.i(hiltModuleInjectATypeClass.showTestLog())
        Timber.i(hiltModuleInjectATypeClass.showTestLog2())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}