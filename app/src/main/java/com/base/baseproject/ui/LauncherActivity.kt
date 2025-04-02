package com.base.baseproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.base.baseproject.R
import com.base.baseproject.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {
    private var _binding: ActivityLauncherBinding? = null
    val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_launcher)
        navController.graph = navGraph

    }
}
