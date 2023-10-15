package com.pablojuice.rayw.feature.rent_create.presentation.list.image

import android.net.Uri
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.R

class RentImagePickerAttachItem(var alreadyContainsImages: Boolean = false) :
    ListItem(R.layout.item_rent_image_picker_attach)

data class RentImagePreviewItem(
    val imageUri: Uri, var isMainImage: Boolean
) : ListItem(layoutId = R.layout.item_rent_image_picker_image, id = imageUri) {

    override fun equals(other: Any?) = imageUri == (other as? RentImagePreviewItem)?.imageUri

    override fun hashCode() = imageUri.hashCode()

}