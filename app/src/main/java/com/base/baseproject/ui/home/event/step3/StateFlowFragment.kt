package com.base.baseproject.ui.home.event.step3

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventStateFlowBinding
import com.base.module.base.BaseFragment
import com.base.module.base.listener.OnOneClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class StateFlowFragment : BaseFragment() {
    override val viewModel: StateFlowViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventStateFlowBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testSelectStateFlow()

        (binding as FragmentEventStateFlowBinding).apply {

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {

                    // Flow
                    launch {
                        viewModel.testFlow.collect {
                            Timber.e("stateFlow Test // flow :: $it")
                            txtValue.text = it.toString()
                        }
                    }

                    // StateFlow
                    launch {
                        viewModel.testStateFlow.collect {
                            Timber.e("stateFlow Test //  stateflow  case1 :: $it")
                            txtValue2.text = it.toString()
                        }
                    }

                    /**
                     * initialValue emptyList로 설정하여 빈 값이 우선 내려오고 flow 갱신됨
                     * */
                    launch {
                        viewModel.testStateFlow2().collect {
                            Timber.e("stateFlow Test //  stateflow  case2 :: $it")
                            txtValue2.text = it.toString()
                        }
                    }
                }
            }

            btnEventType1.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    val value = editValue1.text.toString()
                    lifecycleScope.launch {
                        viewModel.insertStr(value)
                    }
                }
            })

            btnEventType2.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    val value = editValue2.text.toString()
                    lifecycleScope.launch {
                        viewModel.deleteStr(value)
                    }
                }
            })

            btnEventType3.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    val oldValue = editValue3.text.toString()
                    val newValue = editValue4.text.toString()
                    lifecycleScope.launch {
                        viewModel.updateStr(oldValue, newValue)
                    }
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}