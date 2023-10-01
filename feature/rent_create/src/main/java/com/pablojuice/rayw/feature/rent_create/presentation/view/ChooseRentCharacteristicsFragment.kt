package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseCharacteristicsBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentCharacteristicsFragment :
    BasicFragment<FragmentRentCreateChooseCharacteristicsBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseCharacteristicsBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
    }
}