package com.base.baseproject.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentSettingsBinding
import com.base.baseproject.ui.main.MainActivity
import com.base.baseproject.ui.main.MainViewModel
import com.base.module.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsFragment : BaseFragment() {

    private val viewModelActivity: MainViewModel by activityViewModels()
    override val viewModel: SettingsViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 샘플 (아래 코드 삭제 예정)
        lifecycleScope.launch{
            delay(1000)
            viewModelActivity.isWithOutLauncherFragment = true
            (requireActivity() as MainActivity).moveToFirstActivity()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}