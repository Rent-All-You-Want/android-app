package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.DiffUtil

// TODO Fix list flickering
class ListAdapterDiffUtilCallback(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].isItemTheSame(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].isContentTheSame(newList[newItemPosition])
}