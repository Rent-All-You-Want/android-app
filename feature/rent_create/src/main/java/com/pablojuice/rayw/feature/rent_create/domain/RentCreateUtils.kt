package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.RentImagePreviewItem

internal fun List<ListItem>.updateMainItem() {
    forEach { item -> if (item is RentImagePreviewItem) item.isMainImage = false }
    firstOrNull()?.let { if (it is RentImagePreviewItem) it.isMainImage = true }
}