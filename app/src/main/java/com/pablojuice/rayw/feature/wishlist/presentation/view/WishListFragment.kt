package com.pablojuice.rayw.feature.wishlist.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pablojuice.core.presentation.view.list.addOnScrollListener
import com.pablojuice.core.presentation.view.list.firstCompletelyVisibleItemPosition
import com.pablojuice.rayw.feature.home.presentation.view.HomeChildFragment
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import com.pablojuice.rayw.feature.wishlist.data.local.NoWishesListItem
import com.pablojuice.rayw.feature.wishlist.databinding.FragmentWishListBinding
import com.pablojuice.rayw.feature.wishlist.presentation.list.WishListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : HomeChildFragment<FragmentWishListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override val layoutClass = FragmentWishListBinding::class.java

    override val homeListener: HomeListener get() = viewModel

    override fun setupScreen() {
        binding.recycler.adapter = WishListAdapter(viewModel)
        binding.recyclerContainer.setOnRefreshListener {
            (binding.recycler.adapter as? WishListAdapter)?.clearItems()
            viewModel.fetchWishList()
            binding.recyclerContainer.isRefreshing = false
        }
        binding.recycler.addOnScrollListener { recyclerView, _ ->
            binding.recyclerContainer.isEnabled =
                recyclerView.firstCompletelyVisibleItemPosition() == 0
        }
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.recycler.adapter as? WishListAdapter)?.setItems(items)
            (binding.recycler.layoutManager as? StaggeredGridLayoutManager)?.spanCount =
                if (items.first() is NoWishesListItem) 1 else 2
        }
        viewModel.fetchWishList()
    }
}