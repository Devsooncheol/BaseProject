package com.base.baseproject.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.base.baseproject.R
import com.base.baseproject.base.UiScenario
import com.base.baseproject.databinding.ActivityMainBinding
import com.base.module.base.BaseActivity
import com.base.module.base.scenario.BaseScenario
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    lateinit var navController: NavController
    override val viewModel: MainViewModel by viewModels()

    override fun getUiScenario(): BaseScenario {
        return UiScenario
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.apply {
            findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(this)
            addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.homeFragment -> {
                        setBottomBar(true)
                    }

                    R.id.settingsFragment -> {
                        setBottomBar(true)
                    }
                    else -> {
                        setBottomBar(false)
                    }
                }
            }
        }
    }

    private fun setBottomBar(isShowBottom: Boolean) {
        val visibility: Int
        val params = binding.navHostFragment.layoutParams as CoordinatorLayout.LayoutParams
        val paramsBottom = binding.bottomNavigation.layoutParams as CoordinatorLayout.LayoutParams

        if (isShowBottom) {
            visibility = View.VISIBLE
            params.behavior = AppBarLayout.ScrollingViewBehavior()
        } else {
            visibility = View.GONE
            params.behavior = null
        }

        binding.bottomNavigation.visibility = visibility
        binding.bottomNavigation.animate().translationY(0f)
    }
}