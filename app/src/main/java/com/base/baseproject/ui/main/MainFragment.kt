package com.base.baseproject.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentLauncherBinding
import com.base.baseproject.databinding.FragmentLoginBinding
import com.base.baseproject.databinding.FragmentMainBinding
import com.base.baseproject.ui.launcher.LauncherActivity
import com.base.baseproject.ui.launcher.LauncherFragmentDirections
import com.base.module.base.BaseFragment
import com.base.module.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {

    override val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}