package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel

import com.pablojuice.core.presentation.viewmodel.CombinedViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCategoriesScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentDeliveryScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPledgeScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPriceScreen
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.ChooseRentCategoryStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.ChooseRentCategoryViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.CreateNewRentImageStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.CreateNewRentImageViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge.ChooseRentPledgeStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge.ChooseRentPledgeViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.ChooseRentPricingStrategy
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.ChooseRentPricingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateNewRentViewModel @Inject constructor(
    private val imageViewModel: CreateNewRentImageViewModel,
    private val categoryViewModel: ChooseRentCategoryViewModel,
    private val pricingViewModel: ChooseRentPricingViewModel,
    private val pledgeViewModel: ChooseRentPledgeViewModel,
) : CombinedViewModel(), CreateNewRentImageStrategy by imageViewModel,
    ChooseRentCategoryStrategy by categoryViewModel,
    ChooseRentPricingStrategy by pricingViewModel,
    ChooseRentPledgeStrategy by pledgeViewModel {

    init {
        combineNavigationEvents(
            imageViewModel,
            categoryViewModel,
            pricingViewModel,
            pledgeViewModel
        )
    }

    fun openCategories() = submitNavigationEvent(ToChooseRentCategoriesScreen())

    fun openPricing() = submitNavigationEvent(ToChooseRentPriceScreen())

    fun openPledge() = submitNavigationEvent(ToChooseRentPledgeScreen())

    fun openDelivery() = submitNavigationEvent(ToChooseRentDeliveryScreen())
}