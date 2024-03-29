package com.pablojuice.rayw.feature.home.presentation.view


import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.forEach
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.OnCollapseStateChangedListener
import com.pablojuice.core.presentation.view.toolbar.setOnCollapseStateChangedListener
import com.pablojuice.core.presentation.view.toolbar.setTitleLabel
import com.pablojuice.rayw.feature.home.R
import com.pablojuice.rayw.feature.home.data.HomeMenuData
import com.pablojuice.rayw.feature.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max
import com.pablojuice.core.presentation.R as CoreR

private const val MENU_APPEAR_OFFSET = 0.5f
private const val CONTENT_APPEAR_OFFSET = 0.6f


@AndroidEntryPoint
class HomeFragment : BasicFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by activityViewModels()

    override val layoutClass = FragmentHomeBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() = setupNavigationListener()

    private fun setupNavigationListener() {
        val controller =
            binding.homeFragmentContainer.getFragment<NavHostFragment>().findNavController()
        viewModel.menuData.observe { item ->
            binding.homeBottomNavigation.selectedItemId = item.id
            controller.navigate(item.id, null, NavigationAnimation.Fade().options)
            setToolBarItems(item)
        }
        viewModel.menuListener.observe(::setToolBarListener)
        binding.homeBottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId != binding.homeBottomNavigation.selectedItemId) {
                hideKeyboardIfOpened()
                viewModel.selectMenuItem(item.itemId)
            } else false
        }
    }

    private fun setToolBarItems(data: HomeMenuData) = data.run {
        binding.homeToolBarLayout.setExpanded(true)
        binding.homeToolBar.setTitleLabel(title)
        binding.homeToolBarCollapsingLayout.setTitleLabel(title)
        binding.homeToolBar.menu.clear()
        menu?.let(binding.homeToolBar::inflateMenu)
        binding.homeToolBar.menu?.findItem(R.id.search_item).let { search ->
            binding.homeToolBarSearchLayout.setVisible(search != null)
        }
        binding.homeToolBar.menu?.findItem(R.id.filter_item).let { filter ->
            binding.homeToolBarFilterButton.setVisible(filter != null)
        }
    }

    private fun setToolBarListener(listener: HomeListener?) {
        if (listener is HomeListener.MenuItemClickListener) {
            binding.homeToolBar.setOnMenuItemClickListener(listener)
        }
        if (listener is HomeListener.SearchClickListener) {
            binding.homeToolBarCollapsingLayout.updateLayoutParams<ViewGroup.LayoutParams> {
                height = WRAP_CONTENT
            }
            binding.homeToolBarContentLayout.setVisible(true)
            binding.homeToolBarLayout.setOnCollapseStateChangedListener { state, scrollPercent ->
                val toolBarMenuVisible = scrollPercent > MENU_APPEAR_OFFSET
                binding.homeToolBar.menu.forEach { it.isVisible = toolBarMenuVisible }
                binding.homeToolBarContentLayout.alpha =
                    if (state == OnCollapseStateChangedListener.State.EXPANDED) 1f
                    else max(CONTENT_APPEAR_OFFSET - scrollPercent, 0f)
            }
        } else {
            binding.homeToolBarContentLayout.setVisible(false)
            binding.homeToolBarCollapsingLayout.updateLayoutParams<ViewGroup.LayoutParams> {
                height = resources.getDimensionPixelSize(CoreR.dimen.dimen_96)
            }
        }
    }

    fun updateBottomNavigationPosition(isVisible: Boolean) {
        binding.homeBottomNavigation.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            val currentBehavior = behavior
            if (currentBehavior is HideBottomViewOnScrollBehavior) {
                if (isVisible) {
                    currentBehavior.slideUp(binding.homeBottomNavigation)
                } else {
                    currentBehavior.slideDown(binding.homeBottomNavigation)
                }
            }
        }
    }
}