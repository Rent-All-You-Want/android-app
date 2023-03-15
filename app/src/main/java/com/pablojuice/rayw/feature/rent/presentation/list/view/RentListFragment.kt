package com.pablojuice.rayw.feature.rent.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentRentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentListFragment : BasicFragment<FragmentRentListBinding, RentListViewModel>() {

    override val viewModel: RentListViewModel by viewModels()

    override val layoutClass = FragmentRentListBinding::class.java

    override val canNavigateBack: Boolean = false

}