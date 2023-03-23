package com.pablojuice.core.presentation.view.list

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.pablojuice.core.R

private const val STANDARD_DRAWABLE_ALPHA = 255 / 5

class ListItemDivider(
    context: Context,
    private val drawableId: Int = R.drawable.ic_list_item_divider,
    private val drawableAlpha: Int = STANDARD_DRAWABLE_ALPHA,
    private val drawForLastItem: Boolean = false
) : RecyclerView.ItemDecoration() {

    private var drawable: Drawable? = context.getDividerDrawable()

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
        } else {
            left = 0
            right = parent.width
        }

        drawable?.run {
            val listAdapter: ListAdapter = parent.adapter as ListAdapter
            parent.children.forEach { child ->
                val position = parent.getChildAdapterPosition(child)

                val item = listAdapter.getListItemSafe(position)
                val followingItem = listAdapter.getListItemSafe(position + 1)

                if (!canDrawDivider(item, followingItem)) return@forEach

                item?.run {
                    val top =
                        child.bottom + (child.layoutParams as RecyclerView.LayoutParams).bottomMargin
                    val offset = parent.context.resources.getOffsetForItem(this)
                    canvas.drawDivider(top, left + offset, right - offset)
                }
            }
        }
    }

    private fun canDrawDivider(vararg items: ListItem?) =
        items.none { it?.run { dividerType == Type.NONE } ?: !drawForLastItem }

    private fun Resources.getOffsetForItem(item: ListItem): Int = getDimension(
        when (item.dividerType) {
            Type.SMALL -> R.dimen.dimen_64
            Type.MEDIUM -> R.dimen.dimen_32
            Type.LARGE -> R.dimen.dimen_16
            Type.FULL -> R.dimen.dimen_0
            Type.NONE -> TODO()
        }
    ).toInt()

    private fun Canvas.drawDivider(top: Int, left: Int, right: Int) {
        drawable?.let { divider ->
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(this)
        }
    }

    private fun Context.getDividerDrawable() =
        AppCompatResources.getDrawable(this, drawableId)?.apply { alpha = drawableAlpha }

    enum class Type {
        NONE,
        SMALL,
        MEDIUM,
        LARGE,
        FULL
    }
}