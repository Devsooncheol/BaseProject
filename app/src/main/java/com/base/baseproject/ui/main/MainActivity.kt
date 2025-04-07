package com.base.baseproject.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.base.baseproject.R
import com.base.baseproject.base.UiScenario
import com.base.baseproject.databinding.ActivityMainBinding
import com.base.module.base.BaseActivity
import com.base.module.base.scenario.BaseScenario

class MainActivity : BaseActivity<MainViewModel>() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    override val viewModel: MainViewModel by viewModels()

    override fun getUiScenario(): BaseScenario {
        return UiScenario
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}