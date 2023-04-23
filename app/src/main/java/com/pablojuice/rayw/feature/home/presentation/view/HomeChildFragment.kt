package com.pablojuice.rayw.feature.home.presentation.view

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.viewmodel.BasicViewModel

abstract class HomeChildFragment<VB : ViewBinding, VM : BasicViewModel> : BasicFragment<VB, VM>() {

    override val canNavigateBack: Boolean = false

    protected open val homeListener: HomeListener? = null

    protected val homeFragment: HomeFragment
        get() {
            var fragment = parentFragment
            while (fragment !is HomeFragment)
                fragment = fragment?.parentFragment
            return fragment
        }

    protected val homeNavigationController: NavController
        get() = homeFragment.findNavController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeFragment.viewModel.setMenuListener(homeListener)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun navigate(event: NavigationEvent) = event.handle(homeNavigationController)

    fun navigateInsideHome(event: NavigationEvent) = super.navigate(event)
}