package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing

import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPrice
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricingOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChooseRentPricingViewModel @Inject constructor(

) : BasicViewModel(), ChooseRentPricingStrategy {

    private val _pricingOptions = MutableStateFlow(listOf<RentPricingOption>())
    override val pricingOptions: StateFlow<List<RentPricingOption>> = _pricingOptions

    private val _freeRent = MutableStateFlow(false)
    override val freeRent: StateFlow<Boolean> = _freeRent

    private val _savePricingOptionsAvailable = MutableStateFlow(false)
    override val savePricingOptionsAvailable: StateFlow<Boolean> = _savePricingOptionsAvailable

    private var temporaryPricingOptions: MutableMap<RentPricing, Double> = mutableMapOf()

    override fun setupTemporaryPricing() {
        temporaryPricingOptions = pricingOptions.value.asTemporaryMap()
        _savePricingOptionsAvailable.value = false
    }

    override fun setFreeRent(isFree: Boolean) {
        _freeRent.value = isFree
        validateOptions()
    }

    override fun updatePricingOption(priceString: String, pricing: RentPricing) {
        val price = priceString.toDoubleOrNull() ?: 0.0
        temporaryPricingOptions[pricing] = price
        validateOptions()
    }

    private fun validateOptions() {
        _savePricingOptionsAvailable.value =
            temporaryPricingOptions.any { it.value > 0 } || freeRent.value
    }

    override fun savePricingOptions() {
        _pricingOptions.value = temporaryPricingOptions.toOptionsList()
        submitNavigationEvent(NavigationEvents.Back)
    }

    private fun List<RentPricingOption>.asTemporaryMap() =
        associateBy(keySelector = { it.pricing }, valueTransform = { it.price.value })
            .toMutableMap()

    private fun Map<RentPricing, Double>.toOptionsList(): List<RentPricingOption> {
        return map { option ->
            RentPricingOption(
                price = RentPrice(value = option.value), pricing = option.key
            )
        }
    }
}