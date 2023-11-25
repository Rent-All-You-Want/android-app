package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ItemSwipeToRemoveHelper
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.feature.rent_create.R

class AttributesSelectionListAdapter(private val listener: Listener) : ListAdapter(
    listAnimator = ListAnimator.SlideInBottomListAnimator(),
    touchHelper = ItemSwipeToRemoveHelper(listener::removeAttribute)
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_rent_attributes_selection -> AttributesSelectionViewHolder(
            listener::updateAttribute,
            parent
        )

        else -> TODO()
    }

    interface Listener {
        fun updateAttribute(
            option: AttributesSelectionListItem,
            name: String? = null,
            value: String? = null
        )

        fun removeAttribute(position: Int)
    }
}