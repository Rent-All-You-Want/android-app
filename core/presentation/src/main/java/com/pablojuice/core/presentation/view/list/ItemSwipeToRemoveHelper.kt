package com.pablojuice.core.presentation.view.list

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.utils.getColorFromAttribute
import com.pablojuice.core.presentation.utils.getDrawableCompat
import com.pablojuice.core.utils.logging.Timber


class ItemSwipeToRemoveHelper(
    private val onItemRemovedCallback: (Int) -> Unit,
    private val swipeDirection: SwipeDirection = SwipeDirection.HORIZONTAL,
    private val backgroundColorAttr: Int = com.google.android.material.R.attr.colorError,
    private val drawableId: Int = R.drawable.ic_delete_medium,
    private val drawableTintColorAttr: Int = com.google.android.material.R.attr.colorOnError,
    private val callback: ItemSwipeToRemoveHelperCallback = ItemSwipeToRemoveHelperCallback(
        swipeDirs = swipeDirection.direction,
        onItemRemovedCallback = onItemRemovedCallback
    )
) : ItemTouchHelper(callback) {

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.context?.run {
            val drawable = getDrawableCompat(drawableId)
            drawable?.let {
                drawable.setTint(getColorFromAttribute(drawableTintColorAttr))
                callback.initializeDrawable(drawable, getColorFromAttribute(backgroundColorAttr))
            }
        }
        super.attachToRecyclerView(recyclerView)
    }

    fun interface RemovableBySwipe {
        fun canBeRemoved(): Boolean
    }

    enum class SwipeDirection(val direction: Int) {
        VERTICAL(UP), HORIZONTAL(LEFT)
    }
}

class ItemSwipeToRemoveHelperCallback(
    swipeDirs: Int,
    private val onItemRemovedCallback: (Int) -> Unit,
) : ItemTouchHelper.SimpleCallback(0, swipeDirs) {

    //    private val cleanPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
    private lateinit var backgroundDrawable: Drawable
    private lateinit var iconDrawable: Drawable

    fun initializeDrawable(drawable: Drawable, backgroundColor: Int) {
        iconDrawable = drawable
        backgroundDrawable = ColorDrawable().apply { color = backgroundColor }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        previous: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) = if (viewHolder.canBeRemoved()) super.getSwipeDirs(recyclerView, viewHolder) else 0

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        onItemRemovedCallback(viewHolder.adapterPosition)

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.height

        val isCancelled = dX == 0f || !isCurrentlyActive
        Timber.e("FUCK dx=$dX isActive=$isCurrentlyActive")
        if (isCancelled) {
//            c.drawRect(
//                itemView.right + dX,
//                itemView.top.toFloat(),
//                itemView.right.toFloat(),
//                itemView.bottom.toFloat(),
//                cleanPaint
//            )

        } else {
//            backgroundDrawable.setAlphaFloat(1f)
            backgroundDrawable.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            backgroundDrawable.draw(c)

            val iconTop = itemView.top + (itemHeight - iconDrawable.intrinsicHeight) / 2
            val iconMargin = (itemHeight - iconDrawable.intrinsicHeight) / 2
            val iconLeft = itemView.right - iconMargin - iconDrawable.intrinsicWidth
            val iconRight = itemView.right - iconMargin
            val iconBottom = iconTop + iconDrawable.intrinsicHeight

            iconDrawable.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            iconDrawable.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}

private fun RecyclerView.ViewHolder.canBeRemoved() =
    this is ItemSwipeToRemoveHelper.RemovableBySwipe && this.canBeRemoved()