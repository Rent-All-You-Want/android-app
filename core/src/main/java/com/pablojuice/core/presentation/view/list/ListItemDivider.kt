package com.pablojuice.core.presentation.view.list

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.pablojuice.core.R
import kotlin.math.roundToInt

class ListItemDivider(
    private val drawableId: Int = R.drawable.ic_list_item_divider
) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = null

    private val screenBounds = Rect()

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null) divider =
            AppCompatResources.getDrawable(parent.context, drawableId)?.apply { alpha = 25 }

        canvas.save()

        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        divider?.run {
            parent.children.iterator().forEach { child ->
                parent.getDecoratedBoundsWithMargins(child, screenBounds)
                val bottom = screenBounds.bottom + child.translationY.roundToInt()
                val top = bottom - intrinsicHeight
                setBounds(left, top, right, bottom)
                draw(canvas)
            }
        }
        canvas.restore()
    }

    enum class ListDividerType {
        NONE,
        SMALL,
        MEDIUM,
        LARGE,
        FULL
    }
}