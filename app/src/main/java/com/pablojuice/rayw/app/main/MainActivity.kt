package com.pablojuice.rayw.app.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pablojuice.rayw.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
        showSplashScreen()
        setContentView(R.layout.activity_main)
    }

    private fun showSplashScreen() {
        installSplashScreen().run {
            setKeepOnScreenCondition { viewModel.isFetchingData.value }
        }
    }
}