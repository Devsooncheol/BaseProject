package com.base.baseproject.ui.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentLauncherBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherFragment : Fragment() {

    private var _binding: ViewBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveLoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLauncherBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun moveLoginFragment() {
        lifecycleScope.launch {
            delay(1000)

            val action = LauncherFragmentDirections.actionLauncherFragmentToLoginGraph()
            findNavController().navigate(action)
        }
    }
}


