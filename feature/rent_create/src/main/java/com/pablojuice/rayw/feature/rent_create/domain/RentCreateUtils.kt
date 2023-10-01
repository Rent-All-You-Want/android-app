package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePickerAttachItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem

internal fun List<ListItem>.updateMainItem() {
    forEach { item -> if (item is RentImagePreviewItem) item.isMainImage = false }
    firstOrNull()?.let { if (it is RentImagePreviewItem) it.isMainImage = true }
}

internal fun List<ListItem>.updateImageAttachItem() {
    val listIsNotEmpty = size > 1
    forEach { item ->
        if (item is RentImagePickerAttachItem) item.alreadyContainsImages = listIsNotEmpty
    }
}