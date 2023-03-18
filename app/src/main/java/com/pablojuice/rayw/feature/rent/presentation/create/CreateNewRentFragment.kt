package com.pablojuice.rayw.feature.rent.presentation.create

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentRentCreateNewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentRentCreateNewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by viewModels()

    override val layoutClass = FragmentRentCreateNewBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setIconClickListener(::navigateBack)
    }
}