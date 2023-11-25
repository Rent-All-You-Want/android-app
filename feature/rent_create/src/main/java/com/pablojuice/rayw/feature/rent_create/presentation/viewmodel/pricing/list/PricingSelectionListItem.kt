package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pricing.list

import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ItemSwipeToRemoveHelper
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.centerSuffixTextView
import com.pablojuice.core.presentation.view.text.setMenuItemClickListener
import com.pablojuice.core.presentation.view.text.setMenuItems
import com.pablojuice.core.presentation.view.text.setMenuSelectedItem
import com.pablojuice.rayw.feature.rent_create.R
import com.pablojuice.rayw.feature.rent_create.data.local.RentPrice
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentPricingSelectionBinding

data class PricingSelectionListItem(
    var pricing: RentPricing? = RentPricing.DAILY,
    var cost: RentPrice = RentPrice(0.0),
    val removable: Boolean = true
) : ListItem(R.layout.item_rent_pricing_selection)

class PricingSelectionViewHolder(
    private val updateItem: (PricingSelectionListItem, Double?, Int?) -> Unit,
    private val getAvailableOptions: (RentPricing?) -> List<Label>, parent: ViewGroup
) : ViewHolder<PricingSelectionListItem, ItemRentPricingSelectionBinding>(
    ItemRentPricingSelectionBinding.inflate(parent.layoutInflater, parent, false)
), ItemSwipeToRemoveHelper.RemovableBySwipe {

    init {
        binding.priceField.centerSuffixTextView()
        binding.priceField.editText?.doOnTextChanged { text, _, _, _ ->
            currentItem?.let { item ->
                updateItem(item, text.toString().toDoubleOrNull() ?: 0.0, null)
            }
        }
        binding.timingField.setOnTouchListener { v, event ->
            binding.timingField.editText?.setMenuItems(getAvailableOptions(currentItem?.pricing))
            v.onTouchEvent(event)
        }
        binding.timingField.editText?.setMenuItemClickListener { position ->
            currentItem?.let { item -> updateItem(item, null, position) }
            currentItem?.pricing?.run {
                binding.timingField.editText?.setMenuSelectedItem(displayName)
            }
        }
    }

    override fun bind(item: PricingSelectionListItem) {
        super.bind(item)
        item.run {
//            binding.removePricingButton.setVisible(removable)
            binding.timingField.editText?.setMenuItems(getAvailableOptions(currentItem?.pricing))
            pricing?.run { binding.timingField.editText?.setMenuSelectedItem(displayName) }
//            binding.priceField.suffixText = cost.currency
            binding.priceField.editText?.setText(cost.value.toString())
        }
    }

    override fun canBeRemoved(): Boolean = currentItem?.removable == true
}