package com.pablojuice.rayw.feature.rent.presentation.create

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentCreateNewRentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentCreateNewRentBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by viewModels()

    override val layoutClass = FragmentCreateNewRentBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setIconClickListener(::navigateBack)
    }
}