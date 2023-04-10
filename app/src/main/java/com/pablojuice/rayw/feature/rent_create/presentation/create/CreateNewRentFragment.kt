package com.pablojuice.rayw.feature.rent_create.presentation.create

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRentCreateNewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentRentCreateNewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateNewBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setNavigationOnClickListener { navigateBack() }
    }
}