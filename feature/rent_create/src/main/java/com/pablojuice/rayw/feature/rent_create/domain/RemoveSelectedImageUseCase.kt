package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.RentImagePreviewItem
import javax.inject.Inject

class RemoveSelectedImageUseCase @Inject constructor() {

    operator fun invoke(
        image: RentImagePreviewItem,
        items: MutableList<ListItem>
    ): MutableList<ListItem> {
        items.remove(image)
        items.updateMainItem()
        return items
    }
}