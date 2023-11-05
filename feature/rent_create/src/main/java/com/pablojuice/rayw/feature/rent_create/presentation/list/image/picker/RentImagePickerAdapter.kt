package com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.ItemDragAndDropHelper
import com.pablojuice.core.presentation.view.list.ItemDragAndDropHelper.DragDirection.HORIZONTAL
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.feature.rent_create.R
import jp.wasabeef.recyclerview.animators.FadeInAnimator

class RentImagePickerAdapter(private val listener: Listener) : ListAdapter(
    itemAnimator = FadeInAnimator(),
    touchHelper = ItemDragAndDropHelper(listener::onDragAndDrop, HORIZONTAL)
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_rent_image_picker_attach ->
            RentImagePickerAttachViewHolder(listener::onAttachClick, parent)

        R.layout.item_rent_image_picker_image ->
            RentImagePickerImageViewHolder(listener::onImageClick, parent)

        else -> TODO()
    }

    interface Listener {
        fun onAttachClick()

        fun onImageClick(item: RentImagePickerImageViewHolder)

        fun onDragAndDrop(previousPosition: Int, targetPosition: Int)
    }
}