package com.pablojuice.core.presentation.view.list

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import jp.wasabeef.recyclerview.internal.ViewHelper
import java.util.Collections

abstract class ListAdapter(
    items: List<ListItem> = listOf(),
    private val addDividerDecoration: Boolean = false,
    private val itemAnimator: RecyclerView.ItemAnimator? = null, // FadeIn, ScaleIn, SlideIn, Overshoot
    private val listAnimator: ListAnimator? = null,
    private val touchHelper: ItemTouchHelper? = null
) : RecyclerView.Adapter<ViewHolder<ListItem, out ViewBinding>>() {

    protected val items: MutableList<ListItem> = items.toMutableList()

    private var lastAnimatedPosition: Int = -1

    open fun addItem(item: ListItem, position: Int = items.size) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    open fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun swipeItems(previousPosition: Int, targetPosition: Int) {
        Collections.swap(items, previousPosition, targetPosition)
        notifyItemMoved(previousPosition, targetPosition)
    }

    open fun setItems(newItems: List<ListItem>) {
        val diffResult = DiffUtil.calculateDiff(ListAdapterDiffUtilCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun addItems(newItems: List<ListItem>, position: Int = items.size) {
        items.addAll(position, newItems)
        notifyItemRangeInserted(position, newItems.size)
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recycler: RecyclerView) {
        if (addDividerDecoration) recycler.addItemDecoration(ListItemDivider(recycler.context))
        itemAnimator?.let(recycler::setItemAnimator)
        touchHelper?.attachToRecyclerView(recycler)
    }

    override fun onBindViewHolder(holder: ViewHolder<ListItem, out ViewBinding>, position: Int) {
        holder.bind(items[position])
        listAnimator?.run {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition > lastAnimatedPosition) {
                val anim = createAnimator(holder.itemView)
                anim.interpolator = interpolator
                anim.setDuration(duration).start()
                lastAnimatedPosition = adapterPosition
            } else ViewHelper.clear(holder.itemView)
        }
    }

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size

    fun getListItem(position: Int): ListItem = items[position]

    fun getListItemSafe(position: Int): ListItem? = items.getOrNull(position)
}

inline fun <reified T : ListAdapter> RecyclerView.getListAdapter(): T? = adapter as? T