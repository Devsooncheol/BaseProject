package com.base.baseproject.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.databinding.FragmentHomeBinding
import com.base.module.base.BaseFragment
import com.base.module.base.listener.OnOneClickListener

class HomeFragment : BaseFragment() {

    override val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): ViewBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding as FragmentHomeBinding).apply {
            btnHilt.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    navControllerAction(HomeFragmentDirections.actionHomeFragmentToHiltFragment())
                }
            })

            btnEvent.setOnClickListener(object : OnOneClickListener() {
                override fun onOneClick(v: View?) {
                    navControllerAction(HomeFragmentDirections.actionHomeFragmentToEventFragment())
                }
            })


        }
    }

    fun navControllerAction(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}