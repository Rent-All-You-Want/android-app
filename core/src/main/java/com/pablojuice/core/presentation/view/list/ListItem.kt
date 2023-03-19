package com.pablojuice.core.presentation.view.list

abstract class ListItem(
    val layoutId: Int,
    val dividerType: ListItemDivider.ListDividerType = ListItemDivider.ListDividerType.NONE
)