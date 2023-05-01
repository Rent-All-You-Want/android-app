package com.pablojuice.rayw.app.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.pablojuice.core.utils.NumberUtils.UNDEFINED
import com.pablojuice.rayw.R
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.R as CoreR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        showSplashScreen()
        setContentView(R.layout.activity_main)
    }

    private fun setupScreen() {
        lifecycleScope.launchWhenCreated {
            viewModel.navigationGraphId.collect(::onNavGraphChanged)
        }
        viewModel.fetchData(applicationContext)
    }

    private fun showSplashScreen() {
        installSplashScreen().run {
            setKeepOnScreenCondition { viewModel.isFetchingData.value }
        }
    }

    private fun onNavGraphChanged(navGraphId: Int) {
        if (navGraphId == UNDEFINED) return

        val navHostFragment =
            supportFragmentManager.findFragmentById(CoreR.id.app_fragment_container) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph: NavGraph = inflater.inflate(navGraphId)
        navHostFragment.navController.setGraph(graph, intent.extras)
    }
}