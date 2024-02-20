package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ItemSwipeToRemoveHelper
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.rayw.feature.rent_create.R
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing

class PricingSelectionListAdapter(private val listener: Listener) : ListAdapter(
    listAnimator = ListAnimator.SlideInBottomListAnimator(),
    touchHelper = ItemSwipeToRemoveHelper(listener::removeOption)
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_rent_pricing_selection -> PricingSelectionViewHolder(
            listener::updateOption,
            listener::getAvailableOptions,
            parent
        )

        else -> TODO()
    }

    interface Listener {
        fun updateOption(
            option: PricingSelectionListItem,
            cost: Double? = null,
            pricingIndex: Int? = null
        )

        fun removeOption(position: Int)

        fun getAvailableOptions(current: RentPricing? = null): List<Label>
    }
}