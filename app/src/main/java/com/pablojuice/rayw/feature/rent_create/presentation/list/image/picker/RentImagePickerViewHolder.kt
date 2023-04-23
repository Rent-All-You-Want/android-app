package com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.view.updateLayoutParams
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ItemDragAndDropHelper
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.databinding.ItemRentImagePickerAttachBinding
import com.pablojuice.rayw.databinding.ItemRentImagePickerImageBinding
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePickerAttachItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem

class RentImagePickerAttachViewHolder(onClick: () -> Unit, parent: ViewGroup) :
    ViewHolder<RentImagePickerAttachItem, ItemRentImagePickerAttachBinding>(
        ItemRentImagePickerAttachBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    init {
        binding.container.setClickListener(onClick)
    }

    override fun bind(item: RentImagePickerAttachItem) {
        super.bind(item)
        binding.container.post() {
            binding.container.updateLayoutParams<ViewGroup.LayoutParams> {
                width =
                    if (item.alreadyContainsImages) binding.container.height else MATCH_PARENT
            }
        }
    }
}

class RentImagePickerImageViewHolder(
    private val onClick: (RentImagePickerImageViewHolder) -> Unit,
    parent: ViewGroup
) : ViewHolder<RentImagePreviewItem, ItemRentImagePickerImageBinding>(
    ItemRentImagePickerImageBinding.inflate(parent.layoutInflater, parent, false)
), ItemDragAndDropHelper.DragAndDroppable {

    override fun bind(item: RentImagePreviewItem) {
        super.bind(item)
        binding.image.transitionName = item.imageUri.toString()
        binding.image.setImageURI(item.imageUri)
        val strokeWidth =
            if (item.isMainImage) binding.root.resources.getDimension(com.pablojuice.core.R.dimen.dimen_2) else 0f
        binding.image.strokeWidth = strokeWidth
        binding.container.setOnClickListener { onClick(this) }
    }

    fun getSharedView(): View = binding.image

    override fun canBeDragged() = true
}