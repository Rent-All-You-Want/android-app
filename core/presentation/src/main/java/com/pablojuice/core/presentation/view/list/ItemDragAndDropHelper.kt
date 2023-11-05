package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemDragAndDropHelper(
    val onItemDroppedCallback: (Int, Int) -> Unit,
    val dragDirection: DragDirection = DragDirection.VERTICAL
) : ItemTouchHelper(object : SimpleCallback(dragDirection.direction, 0) {
    override fun onMove(
        recyclerView: RecyclerView,
        previous: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return if (previous.canBeDragged() && target.canBeDragged()) {
            onItemDroppedCallback(previous.adapterPosition, target.adapterPosition)
            true
        } else false
    }

    override fun getDragDirs(recycler: RecyclerView, view: RecyclerView.ViewHolder) =
        if (view.canBeDragged()) super.getDragDirs(recycler, view) else 0

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit

    override fun isItemViewSwipeEnabled(): Boolean = false

}) {

    fun interface DragAndDroppable {
        fun canBeDragged(): Boolean
    }

    enum class DragDirection(val direction: Int) {
        VERTICAL(UP or DOWN), HORIZONTAL(LEFT or RIGHT)
    }
}

private fun RecyclerView.ViewHolder.canBeDragged() =
    this is ItemDragAndDropHelper.DragAndDroppable && this.canBeDragged()