package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChoosePriceBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list.PricingSelectionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentPriceFragment :
    BasicFragment<FragmentRentCreateChoosePriceBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChoosePriceBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        binding.addPricingBtn.setClickListener(viewModel::addPricingOption)
        binding.recycler.adapter = PricingSelectionListAdapter(viewModel)
        viewModel.pricingOptions.observe { items ->
            (binding.recycler.adapter as? PricingSelectionListAdapter)?.setItems(items)
        }
        viewModel.canHaveMorePricingOptions.observe { canHave ->
            binding.addPricingBtn.setVisible(
                canHave
            )
        }
    }
}