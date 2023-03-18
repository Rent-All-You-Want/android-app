package com.pablojuice.rayw.feature.rent.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.list.PagingScrollListener
import com.pablojuice.core.presentation.view.list.StaggeredGridPagingScrollListener
import com.pablojuice.rayw.databinding.FragmentRentListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import com.pablojuice.rayw.feature.rent.presentation.list.list.RentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentListFragment :
    HomeFragment.HomeChildFragment<FragmentRentListBinding, RentListViewModel>(),
    PagingScrollListener.PagingListener {

    override val viewModel: RentListViewModel by viewModels()

    override val layoutClass = FragmentRentListBinding::class.java

    override fun setupScreen() {
        binding.recycler.adapter = RentAdapter()
        binding.recycler.addOnScrollListener(StaggeredGridPagingScrollListener(this))
        binding.recyclerContainer.setOnRefreshListener {
            (binding.recycler.adapter as? RentAdapter)?.clearItems()
            viewModel.loadItems()
            binding.recyclerContainer.isRefreshing = false
        }
        viewModel.canLoadItems.observe {
            binding.recyclerContainer.isRefreshing = !it
        }
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.recycler.adapter as? RentAdapter)?.addItems(items)
        }
        viewModel.loadItems()
    }

    override fun canLoadMore() = viewModel.canLoadItems.value

    override fun loadMoreItems() = viewModel.loadItems()
}