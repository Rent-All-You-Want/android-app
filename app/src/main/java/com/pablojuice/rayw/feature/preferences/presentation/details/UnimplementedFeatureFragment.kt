package com.pablojuice.rayw.feature.preferences.presentation.details

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.databinding.FragmentUnimplementedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnimplementedFeatureFragment :
    BasicFragment<FragmentUnimplementedBinding, PreferencesViewModel>() {

    override val viewModel: PreferencesViewModel by viewModels()

    override val layoutClass = FragmentUnimplementedBinding::class.java

    override fun setupScreen() {
        binding.unimplementedToolBar.setNavigationClickListener(::navigateBack)
    }
}