package com.pablojuice.rayw.feature.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.navigation.NavigationAnimation
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentHomeBinding
import com.pablojuice.rayw.feature.home.presentation.navigation.ToCreateNewItemScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BasicFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val canNavigateBack: Boolean = false

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentHomeBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationListener()
    }

    private fun setupNavigationListener() {
        val controller =
            binding.homeFragmentContainer.getFragment<NavHostFragment>().findNavController()

        binding.homeBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                binding.homeBottomNavigation.selectedItemId -> false
                R.id.new_item -> navigate(ToCreateNewItemScreen()).let { false }
                else -> controller
                    .navigate(item.itemId, null, NavigationAnimation.Fade().options).let { true }
            }
        }
    }

    abstract class HomeChildFragment<VB : ViewBinding, VM : BasicViewModel> :
        BasicFragment<VB, VM>() {

        override fun navigate(event: NavigationEvent) =
            navigateIfDestinationExists(event, homeNavigationController)

        fun navigateInsideHome(event: NavigationEvent) = super.navigate(event)

        protected val homeNavigationController: NavController
            get() {
                var fragment = parentFragment
                while (fragment !is HomeFragment)
                    fragment = fragment?.parentFragment
                return fragment.findNavController()
            }
    }
}