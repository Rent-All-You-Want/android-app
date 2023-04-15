package com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker

import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pablojuice.core.presentation.view.list.ItemDragAndDropHelper
import com.pablojuice.core.presentation.view.list.ItemDragAndDropHelper.DragDirection.HORIZONTAL
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R
import jp.wasabeef.recyclerview.animators.FadeInAnimator

class RentImagePickerAdapter(private val listener: Listener) :
    ListAdapter(itemAnimator = FadeInAnimator()) {

    private val helper: ItemTouchHelper = ItemDragAndDropHelper(listener::onDragAndDrop, HORIZONTAL)

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

    override fun onAttachedToRecyclerView(recycler: RecyclerView) {
        super.onAttachedToRecyclerView(recycler)
        helper.attachToRecyclerView(recycler)
    }

    interface Listener {
        fun onAttachClick()

        fun onImageClick(item: RentImagePickerImageViewHolder)

        fun onDragAndDrop(previousPosition: Int, targetPosition: Int)
    }
}