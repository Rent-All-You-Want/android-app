package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing

import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list.PricingSelectionListAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list.PricingSelectionListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CreateNewRentPricingStrategy : PricingSelectionListAdapter.Listener {
    val pricingOptions: StateFlow<List<PricingSelectionListItem>>
    val canHaveMorePricingOptions: Flow<Boolean>
    fun addPricingOption()
}