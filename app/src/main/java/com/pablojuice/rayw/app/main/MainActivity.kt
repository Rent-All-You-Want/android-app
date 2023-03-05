package com.pablojuice.rayw.app.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.pablojuice.rayw.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        showSplashScreen()
        setContentView(R.layout.activity_main)
    }

    private fun setupScreen() {
        viewModel.fetchData()
        lifecycleScope.launchWhenCreated {
            viewModel.navigationGraphId.collect(::onNavGraphChanged)
        }
    }

    private fun showSplashScreen() {
        installSplashScreen().run {
            setKeepOnScreenCondition { viewModel.isFetchingData.value }
        }
    }

    private fun onNavGraphChanged(navGraphId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph: NavGraph = inflater.inflate(navGraphId)
        navHostFragment.navController.setGraph(graph, intent.extras)
    }
}