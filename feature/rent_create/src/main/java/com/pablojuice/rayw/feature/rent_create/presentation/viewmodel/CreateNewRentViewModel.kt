package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel

import com.pablojuice.core.presentation.viewmodel.CombinedViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCategoriesScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCharacteristicsScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentDeliveryScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPledgeScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPriceScreen
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.imege.CreateNewRentImageLogic
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.imege.CreateNewRentImageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CreateNewRentViewModel @Inject constructor(
    private val imageViewModel: CreateNewRentImageViewModel
) : CombinedViewModel(), CreateNewRentImageLogic by imageViewModel {

    init {
        combineNavigationEvents(imageViewModel)
    }

    private val _selectedPricingOptions = MutableStateFlow(listOf<RentPricing>())
    val selectedPricingOptions: StateFlow<List<RentPricing>> = _selectedPricingOptions

    val availablePricingOptions: StateFlow<List<RentPricing>> =
        MutableStateFlow(listOf(RentPricing.HOURLY))

    fun setSelectedPriceOptions(options: List<Int>) {
        _selectedPricingOptions.value = options.map(RentPricing::fromId)
    }

    fun openCategories() = submitNavigationEvent(ToChooseRentCategoriesScreen())

    fun openCharacteristics() = submitNavigationEvent(ToChooseRentCharacteristicsScreen())

    fun openPricing() = submitNavigationEvent(ToChooseRentPriceScreen())

    fun openPledge() = submitNavigationEvent(ToChooseRentPledgeScreen())

    fun openDelivery() = submitNavigationEvent(ToChooseRentDeliveryScreen())
}