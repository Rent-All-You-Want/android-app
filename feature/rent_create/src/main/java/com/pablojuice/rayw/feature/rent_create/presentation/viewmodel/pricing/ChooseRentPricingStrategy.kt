package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing

import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricingOption
import kotlinx.coroutines.flow.StateFlow

interface ChooseRentPricingStrategy {
    val pricingOptions: StateFlow<List<RentPricingOption>>
    val freeRent: StateFlow<Boolean>
    val savePricingOptionsAvailable: StateFlow<Boolean>
    fun setFreeRent(isFree: Boolean)
    fun updatePricingOption(priceString: String, pricing: RentPricing)
    fun setupTemporaryPricing()
    fun savePricingOptions()
}

//TODO ADVANCED VERSION
//binding.addPricingBtn.setClickListener(viewModel::addPricingOption)
//binding.recycler.adapter = PricingSelectionListAdapter(viewModel)
//viewModel.pricingOptions.observe { items ->
//    (binding.recycler.adapter as? PricingSelectionListAdapter)?.setItems(items)
//}
//viewModel.canHaveMorePricingOptions.observe { canHave ->
//    binding.addPricingBtn.setVisible(canHave)
//}

//interface ChooseRentPricingStrategy : PricingSelectionListAdapter.Listener {
//    val pricingOptions: StateFlow<List<PricingSelectionListItem>>
//    val canHaveMorePricingOptions: Flow<Boolean>
//    fun addPricingOption()
//}

//private val _pricingOptions =
//    MutableStateFlow(listOf(PricingSelectionListItem(removable = false)))
//override val pricingOptions: StateFlow<List<PricingSelectionListItem>> = _pricingOptions
//
//override val canHaveMorePricingOptions: Flow<Boolean> =
//    pricingOptions.map { getAvailableOptions().isNotEmpty() }
//
//override fun addPricingOption() {
//    _pricingOptions.update {
//        it.toMutableList()
//            .apply { add(PricingSelectionListItem(getAvailableOptionsList().firstOrNull())) }
//    }
//}
//
//override fun updateOption(option: PricingSelectionListItem, cost: Double?, pricingIndex: Int?) {
//    if (cost != null) {
//        option.cost = RentPrice(cost)
//    } else if (pricingIndex != null) {
//        option.pricing = getAvailableOptionsList(option.pricing)[pricingIndex]
//    } else {
//        _pricingOptions.update { list -> list.filter { it.pricing != option.pricing } }
//    }
//}
//
//override fun removeOption(position: Int) {
//    _pricingOptions.update { list -> list.toMutableList().apply { removeAt(position) } }
//}
//
//private fun getAvailableOptionsList(current: RentPricing? = null): List<RentPricing> {
//    val unavailableOptions =
//        _pricingOptions.value.mapNotNull { if (it.pricing != current) it.pricing else null }
//    return RentPricing.values().filterNot { unavailableOptions.contains(it) }
//}
//
//override fun getAvailableOptions(current: RentPricing?) =
//    getAvailableOptionsList(current).map { it.displayName }