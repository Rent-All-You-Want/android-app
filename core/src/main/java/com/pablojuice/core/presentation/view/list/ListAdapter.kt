package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ListAdapter(
    items: List<ListItem> = listOf(),
    private val addDividerDecoration: Boolean = false
) : RecyclerView.Adapter<ViewHolder<ListItem, out ViewBinding>>() {

    private val items: MutableList<ListItem> = items.toMutableList()

    fun addItem(item: ListItem, position: Int = items.size) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(newItems: List<ListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<ListItem>, position: Int = items.size) {
        items.addAll(position, newItems)
        notifyItemRangeInserted(position, newItems.size)
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onAttachedToRecyclerView(recycler: RecyclerView) {
        if (addDividerDecoration) recycler.addItemDecoration(ListItemDivider(recycler.context))
    }

    override fun onBindViewHolder(holder: ViewHolder<ListItem, out ViewBinding>, position: Int) =
        holder.bind(item = items[position])

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size

    fun getListItem(position: Int): ListItem = items[position]

    fun getListItemSafe(position: Int): ListItem? = items.getOrNull(position)
}

inline fun <reified T : ListAdapter> RecyclerView.getListAdapter(): T? = adapter as? T