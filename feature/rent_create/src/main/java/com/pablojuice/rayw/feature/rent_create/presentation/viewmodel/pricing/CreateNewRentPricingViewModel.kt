package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPrice
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list.PricingSelectionListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CreateNewRentPricingViewModel @Inject constructor() : BasicViewModel(),
    CreateNewRentPricingStrategy {

    private val _pricingOptions =
        MutableStateFlow(listOf(PricingSelectionListItem(removable = false)))
    override val pricingOptions: StateFlow<List<PricingSelectionListItem>> = _pricingOptions

    override val canHaveMorePricingOptions: Flow<Boolean> =
        pricingOptions.map { getAvailableOptions().isNotEmpty() }

    override fun addPricingOption() {
        _pricingOptions.update {
            it.toMutableList()
                .apply { add(PricingSelectionListItem(getAvailableOptionsList().firstOrNull())) }
        }
    }

    override fun updateOption(option: PricingSelectionListItem, cost: Double?, pricingIndex: Int?) {
        if (cost != null) {
            option.cost = RentPrice(cost)
        } else if (pricingIndex != null) {
            option.pricing = getAvailableOptionsList(option.pricing)[pricingIndex]
        } else {
            _pricingOptions.update { list -> list.filter { it.pricing != option.pricing } }
        }
    }

    override fun removeOption(position: Int) {
        _pricingOptions.update { list -> list.toMutableList().apply { removeAt(position) } }
    }

    private fun getAvailableOptionsList(current: RentPricing? = null): List<RentPricing> {
        val unavailableOptions =
            _pricingOptions.value.mapNotNull { if (it.pricing != current) it.pricing else null }
        return RentPricing.values().filterNot { unavailableOptions.contains(it) }
    }

    override fun getAvailableOptions(current: RentPricing?) =
        getAvailableOptionsList(current).map { it.displayName }
}