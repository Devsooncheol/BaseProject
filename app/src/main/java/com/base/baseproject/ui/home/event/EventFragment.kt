package com.base.baseproject.ui.home.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.baseproject.R
import com.base.baseproject.databinding.FragmentEventBinding
import com.base.baseproject.ui.home.hilt.HiltViewModel
import com.base.module.base.BaseFragment
import com.base.module.base.listener.OnOneClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : BaseFragment() {

    //override val viewModel: EventViewModel by viewModels()
    override val viewModel by hiltNavGraphViewModels<EventViewModel>(R.id.nav_graph_main)

    override fun getViewBinding(): ViewBinding {
        return FragmentEventBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (binding as FragmentEventBinding).apply {
            btnClick(btnEventType1){ EventFragmentDirections.actionEventFragmentToLiveDataFragment() }
            btnClick(btnEventType2){ EventFragmentDirections.actionEventFragmentToFlowFragment() }
            btnClick(btnEventType3){ EventFragmentDirections.actionEventFragmentToStateFlowFragment() }
            btnClick(btnEventType4){ EventFragmentDirections.actionEventFragmentToEventStep4Fragment() }
            btnClick(btnEventType5){ EventFragmentDirections.actionEventFragmentToEventStep5Fragment() }
            btnClick(btnEventType6){ EventFragmentDirections.actionEventFragmentToEventStep6Fragment() }
            btnClick(btnEventType7){ EventFragmentDirections.actionEventFragmentToEventStep7Fragment() }
        }
    }

    private fun btnClick(button: View, directionProvider: () -> NavDirections) {
        button.setOnClickListener(object : OnOneClickListener() {
            override fun onOneClick(v: View?) {
                findNavController().navigate(directionProvider())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}