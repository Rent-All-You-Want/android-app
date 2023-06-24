package com.pablojuice.rayw.feature.preferences.presentation.details

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.preferences.databinding.FragmentPreferencesAboutAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesAboutAppFragment :
    BasicFragment<FragmentPreferencesAboutAppBinding, PreferencesViewModel>() {

    override val viewModel: PreferencesViewModel by viewModels()

    override val layoutClass = FragmentPreferencesAboutAppBinding::class.java

    override fun setupScreen() {
        binding.preferencesToolBar.setNavigationClickListener(::navigateBack)
        binding.title.text = viewModel.getBuildTitle()
        binding.description.text = viewModel.getBuildDescription()
    }
}