package com.base.baseproject.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentLoginBinding
import com.base.baseproject.ui.launcher.LauncherActivity
import com.base.baseproject.ui.launcher.LauncherFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: ViewBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        moveMainActivity()
        return binding.root
    }

    private fun moveMainActivity() {
        lifecycleScope.launch {
            delay(1000)

            (requireActivity() as LauncherActivity).moveToHomeActivity()
        }
    }
}