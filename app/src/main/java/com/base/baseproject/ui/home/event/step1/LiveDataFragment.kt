package com.base.baseproject.ui.home.event.step1

import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentEventLiveDataBinding
import com.base.module.base.BaseFragment
import com.base.module.base.listener.OnOneClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class LiveDataFragment : BaseFragment() {
    override val viewModel by hiltNavGraphViewModels<LiveDataViewModel>(R.id.nav_graph_main)

    override fun getViewBinding(): ViewBinding {
        return FragmentEventLiveDataBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding as FragmentEventLiveDataBinding).apply {
            btnClick.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.viewModelScope.launch {
                        viewModel.incrementCountForSetValue()
                    }
                }
            })

            // Crash
            btnClick2.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.incrementCountForSetValue()
                        }
                    }
                }
            })

            btnClick3.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.viewModelScope.launch {
                        viewModel.incrementCountForPostValue()
                    }
                }
            })

            btnClick4.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    viewModel.viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.incrementCountForPostValue()
                        }
                    }
                }
            })


            viewModel.count.observe(viewLifecycleOwner) { newCount ->
                Timber.i("LiveDataObserver count 업데이트: $newCount")
                txtNumber.text = "카운트: $newCount"
            }
        }
    }
}