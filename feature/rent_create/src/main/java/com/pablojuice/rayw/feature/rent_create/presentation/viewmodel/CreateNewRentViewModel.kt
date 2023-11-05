package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel

import com.pablojuice.core.presentation.viewmodel.CombinedViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCategoriesScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCharacteristicsScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentDeliveryScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPledgeScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPriceScreen
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.CreateNewRentAttributesStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.CreateNewRentAttributesViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.CreateNewRentImageStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.CreateNewRentImageViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge.CreateNewRentPledgeStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge.CreateNewRentPledgeViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.CreateNewRentPricingStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.CreateNewRentPricingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateNewRentViewModel @Inject constructor(
    private val imageViewModel: CreateNewRentImageViewModel,
    private val attributesViewModel: CreateNewRentAttributesViewModel,
    private val pricingViewModel: CreateNewRentPricingViewModel,
    private val pledgeViewModel: CreateNewRentPledgeViewModel,
) : CombinedViewModel(), CreateNewRentImageStrategy by imageViewModel,
    CreateNewRentAttributesStrategy by attributesViewModel,
    CreateNewRentPricingStrategy by pricingViewModel,
    CreateNewRentPledgeStrategy by pledgeViewModel {

    init {
        combineNavigationEvents(
            imageViewModel,
            attributesViewModel,
            pricingViewModel,
            pledgeViewModel
        )
    }

    fun openCategories() = submitNavigationEvent(ToChooseRentCategoriesScreen())

    fun openCharacteristics() = submitNavigationEvent(ToChooseRentCharacteristicsScreen())

    fun openPricing() = submitNavigationEvent(ToChooseRentPriceScreen())

    fun openPledge() = submitNavigationEvent(ToChooseRentPledgeScreen())

    fun openDelivery() = submitNavigationEvent(ToChooseRentDeliveryScreen())
}