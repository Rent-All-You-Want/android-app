package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChoosePriceBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentPriceFragment :
    BasicFragment<FragmentRentCreateChoosePriceBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChoosePriceBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
    }

    //        binding.priceForHourField.centerSuffixTextView()
//        binding.priceForDayField.centerSuffixTextView()
//        binding.priceForMonthField.centerSuffixTextView()
//        viewModel.selectedPricingOptions.observe { rentOptions ->
//            binding.priceForHourField.setVisible(rentOptions.contains(RentPricing.HOURLY))
//            binding.priceForDayField.setVisible(rentOptions.contains(RentPricing.DAILY))
//            binding.priceForMonthField.setVisible(rentOptions.contains(RentPricing.MONTHLY))
//        }
//        binding.priceChips.setOnCheckedStateChangeListener { _, checkedIds ->
//            viewModel.setSelectedPriceOptions(checkedIds)
//        }
}