package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list

import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ItemSwipeToRemoveHelper
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.feature.rent_create.R
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentAttributesSelectionBinding

data class AttributesSelectionListItem(
    var name: Label? = null,
    var value: Label? = null,
    val removable: Boolean = true
) : ListItem(R.layout.item_rent_attributes_selection)

class AttributesSelectionViewHolder(
    private val updateItem: (AttributesSelectionListItem, String?, String?) -> Unit,
    parent: ViewGroup
) : ViewHolder<AttributesSelectionListItem, ItemRentAttributesSelectionBinding>(
    ItemRentAttributesSelectionBinding.inflate(parent.layoutInflater, parent, false)
), ItemSwipeToRemoveHelper.RemovableBySwipe {

    init {
        binding.attributeNameField.editText?.doOnTextChanged { text, _, _, _ ->
            currentItem?.let { item -> updateItem(item, text.toString(), null) }
        }
        binding.attributeValueField.editText?.doOnTextChanged { text, _, _, _ ->
            currentItem?.let { item -> updateItem(item, null, text.toString()) }
        }
    }

    override fun bind(item: AttributesSelectionListItem) {
        super.bind(item)
        item.run {
            binding.attributeNameField.editText?.setLabel(name)
            binding.attributeValueField.editText?.setLabel(value)
        }
    }

    override fun canBeRemoved(): Boolean = currentItem?.removable == true
}