package com.pablojuice.core.presentation.view.list

abstract class ListItem(
    val layoutId: Int,
    val dividerType: ListItemDivider.Type = ListItemDivider.Type.NONE,
    open val id: Any? = null,
) {
    open fun isItemTheSame(item: ListItem): Boolean = (id != null && id == item.id) || this === item

    open fun isContentTheSame(item: ListItem): Boolean = false
}