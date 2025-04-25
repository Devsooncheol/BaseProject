package com.base.baseproject.ui.home.event.step2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentEventFlowBinding
import com.base.module.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class FlowFragment : BaseFragment() {
    override val viewModel: FlowViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentEventFlowBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (binding as FragmentEventFlowBinding).apply {

            /**
             * 잘못된 코드
             * **메모리 리소스 낭비 방지**: 백그라운드에서 Flow 수집 시 리소스 낭비를 방지해야 함
             * */
            lifecycleScope.launch {
                viewModel.testFlow.collect{
                    Timber.e("Resource leakage in the background .. $it")
                    txtValue.text = it.toString()
                }
            }

            /**
             * **asLiveData**로 Flow를 LiveData로 변환하여 UI가 화면에 표시될 때만 관찰하도록 함
             * LiveData는 자동으로 수집을 관리하고, Fragment가 비활성화되면 관찰을 중지함
             */
            viewModel.testFlow.asLiveData().observe(viewLifecycleOwner){
                Timber.e("Resource leakage in the background2 .. $it")
                txtValue2.text = it.toString()
            }

            /**
             * **repeatOnLifecycle**을 사용하여 Lifecycle 상태가 STARTED일 때만 Flow를 수집
             * 여러 Flow를 수집할 때 적합하며, 해당 상태에서만 코루틴이 활성화됨
             */
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.testFlow.collect{
                        Timber.e("Resource leakage in the background3 .. $it")
                        txtValue3.text = it.toString()
                    }
                }
            }

            /**
             * **flowWithLifecycle**을 사용하여 Flow가 Lifecycle 상태에 맞게 수집됨
             * Flow를 수집할 때 Lifecycle이 STARTED 상태일 때만 Flow를 수집
             * 하나의 Flow만 수집할 때 적합
             */
            lifecycleScope.launch {
                viewModel.testFlow
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect {
                        Timber.e("Resource leakage in the background4 .. $it")
                        txtValue4.text = it.toString()
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}