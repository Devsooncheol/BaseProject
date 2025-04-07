package com.base.baseproject.ui.launcher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.base.baseproject.R
import com.base.baseproject.base.UiScenario
import com.base.baseproject.databinding.ActivityLauncherBinding
import com.base.module.base.BaseActivity
import com.base.module.base.constants.BaseConstants
import com.base.module.base.scenario.BaseScenario

class LauncherActivity : BaseActivity<LauncherViewModel>() {
    private var _binding: ActivityLauncherBinding? = null
    val binding get() = _binding!!

    override val viewModel: LauncherViewModel by viewModels()

    private lateinit var navController: NavController

    override fun getUiScenario(): BaseScenario {
        return UiScenario
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_launcher)

        val startDest = if(!intent.getBooleanExtra(BaseConstants.INTENT_EXTRA_WITHOUT_LAUNCHER, false)) {
            R.id.launcherFragment
        } else {
            R.id.nav_graph_login
        }

        navGraph.setStartDestination(startDest)
        navController.graph = navGraph
    }
}
