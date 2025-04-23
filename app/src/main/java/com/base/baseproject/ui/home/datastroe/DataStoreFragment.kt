package com.base.baseproject.ui.home.datastroe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentDataStroeBinding
import com.base.baseproject.databinding.FragmentEventBinding
import com.base.baseproject.ui.home.HomeFragmentDirections
import com.base.module.base.BaseFragment
import com.base.module.base.listener.OnOneClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataStoreFragment : BaseFragment() {

    override val viewModel by hiltNavGraphViewModels<DataStoreViewModel>(R.id.nav_graph_main)

    override fun getViewBinding(): ViewBinding {
        return FragmentDataStroeBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding as FragmentDataStroeBinding).apply {
            lifecycleScope.launchWhenStarted {
                viewModel.testValueFlow.collect{ value ->
                    txtValue.text = value.toString()
                }
            }

            btnClick.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.incrementTestValue()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}