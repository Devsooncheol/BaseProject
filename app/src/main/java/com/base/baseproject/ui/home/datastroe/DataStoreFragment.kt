package com.base.baseproject.ui.home.datastroe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class DataStoreFragment : BaseFragment() {

    override val viewModel by hiltNavGraphViewModels<DataStoreViewModel>(R.id.nav_graph_main)

    override fun getViewBinding(): ViewBinding {
        return FragmentDataStroeBinding.inflate(layoutInflater)
    }

    private var stringBuilder = StringBuilder()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding as FragmentDataStroeBinding).apply {
            lifecycleScope.launchWhenStarted {
                viewModel.testValueFlow.collect{ value ->
                    txtValue.text = value.toString()
                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.settingsFlow.collect{ settingsTest ->
                    stringBuilder.clear()
                    stringBuilder.appendLine("intValue :: ${settingsTest.intValue}")
                    stringBuilder.appendLine("doubleValue :: ${settingsTest.doubleValue}")
                    stringBuilder.appendLine("floatValue :: ${settingsTest.floatValue}")
                    stringBuilder.appendLine("longValue :: ${settingsTest.longValue}")
                    stringBuilder.appendLine("isFlag :: ${settingsTest.isFlag}")
                    stringBuilder.appendLine("strMessage :: ${settingsTest.strMessage}")
                    txtValue2.text = stringBuilder.toString()
                }
            }

            btnClick.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.incrementTestValue()
                }
            })

            btnClick2.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateIntValue()
                }
            })

            btnClick3.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateDoubleValue()
                }
            })

            btnClick4.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateFloatValue()
                }
            })

            btnClick5.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateLongValue()
                }
            })

            btnClick6.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateBooleanValue()
                }
            })

            btnClick7.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.updateStringValue()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}