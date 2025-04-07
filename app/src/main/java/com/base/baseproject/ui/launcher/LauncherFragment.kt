package com.base.baseproject.ui.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentLauncherBinding
import com.base.module.base.BaseFragment
import com.base.module.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherFragment : BaseFragment() {

    override val viewModel: LauncherViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentLauncherBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveLoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun moveLoginFragment() {
        lifecycleScope.launch {
            delay(1000)

            val action = LauncherFragmentDirections.actionLauncherFragmentToLoginGraph()
            findNavController().navigate(action)
        }
    }
}


