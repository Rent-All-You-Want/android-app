package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.list.ListItem
import java.util.Collections
import javax.inject.Inject

class DragAndDropImageUseCase @Inject constructor() {

    operator fun invoke(
        previousPosition: Int, targetPosition: Int,
        items: MutableList<ListItem>
    ): MutableList<ListItem> {
        Collections.swap(items, previousPosition, targetPosition)
        if (previousPosition == 0 || targetPosition == 0) items.updateMainItem()
        return items
    }
}