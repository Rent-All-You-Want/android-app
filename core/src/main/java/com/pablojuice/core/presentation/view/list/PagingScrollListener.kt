package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

@Suppress("UNCHECKED_CAST")
abstract class PagingScrollListener<LM : LayoutManager>(
    private val listener: PagingListener
) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (canLoadMoreItems(recyclerView.layoutManager as LM) && listener.canLoadMore())
                listener.loadMoreItems()
        }
    }

    abstract fun canLoadMoreItems(layoutManager: LM): Boolean

    interface PagingListener {
        fun canLoadMore(): Boolean
        fun loadMoreItems()
    }
}

class LinearPagingScrollListener(listener: PagingListener) :
    PagingScrollListener<LinearLayoutManager>(listener) {

    override fun canLoadMoreItems(layoutManager: LinearLayoutManager) = layoutManager.run {
        findLastCompletelyVisibleItemPosition() + childCount >= itemCount
    }
}

class StaggeredGridPagingScrollListener(listener: PagingListener) :
    PagingScrollListener<StaggeredGridLayoutManager>(listener) {

    override fun canLoadMoreItems(layoutManager: StaggeredGridLayoutManager) = layoutManager.run {
        findLastCompletelyVisibleItemPositions(IntArray(spanCount))[spanCount - 1] + childCount >= itemCount
    }
}