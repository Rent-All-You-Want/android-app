package com.pablojuice.rayw.feature.devoptions

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.devoptions.databinding.FragmentUnimplementedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnimplementedFeatureFragment : BasicFragment<FragmentUnimplementedBinding, BasicViewModel>() {

    override val viewModel: BasicViewModel by viewModels()

    override val layoutClass = FragmentUnimplementedBinding::class.java

    override fun setupScreen() {
        binding.unimplementedToolBar.setNavigationClickListener(::navigateBack)
    }
}