package com.pablojuice.rayw.feature.rent_list.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.list.StaggeredGridPagingScrollListener
import com.pablojuice.core.presentation.view.list.addOnScrollListener
import com.pablojuice.core.presentation.view.list.firstCompletelyVisibleItemPosition
import com.pablojuice.rayw.databinding.FragmentRentListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeChildFragment
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import com.pablojuice.rayw.feature.rent_list.presentation.list.list.RentListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentListFragment : HomeChildFragment<FragmentRentListBinding, RentListViewModel>() {

    override val viewModel: RentListViewModel by viewModels()

    override val layoutClass = FragmentRentListBinding::class.java

    override val homeListener: HomeListener get() = viewModel

    override fun setupScreen() {
        binding.recycler.adapter = RentListAdapter(viewModel)
        binding.recycler.addOnScrollListener(StaggeredGridPagingScrollListener(viewModel))
        binding.recyclerContainer.setOnRefreshListener {
            (binding.recycler.adapter as? RentListAdapter)?.clearItems()
            viewModel.reloadItems()
            binding.recyclerContainer.isRefreshing = false
        }
        binding.recycler.addOnScrollListener { recyclerView, _ ->
            binding.recyclerContainer.isEnabled =
                recyclerView.firstCompletelyVisibleItemPosition() == 0
        }
        viewModel.canLoadItems.observe { binding.recyclerContainer.isRefreshing = !it }
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.recycler.adapter as? RentListAdapter)?.addItems(items)
        }
        viewModel.reloadItems()
    }
}