package com.base.baseproject.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentLoginBinding
import com.base.baseproject.ui.launcher.LauncherActivity
import com.base.baseproject.ui.launcher.LauncherFragmentDirections
import com.base.module.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    override val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveMainActivity()
    }

    private fun moveMainActivity() {
        lifecycleScope.launch {
            delay(1000)

            (requireActivity() as LauncherActivity).moveToHomeActivity()
        }
    }
}