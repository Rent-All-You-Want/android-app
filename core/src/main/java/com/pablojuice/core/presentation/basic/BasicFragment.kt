package com.pablojuice.core.presentation.basic

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.base.screen.BaseFragment
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationHandler
import kotlinx.coroutines.flow.Flow

abstract class BasicFragment<VB : ViewBinding, VM : BasicViewModel> : BaseFragment<VB>(),
    NavigationHandler {
    abstract val viewModel: VM

    protected open val canNavigateBack = true

    protected val navController: NavController
        get() = findNavController()

    private lateinit var navigateBackCallback: OnBackPressedCallback

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
    }

    private fun setupNavigation() {
        navigateBackCallback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { navigateBack() }
        viewModel.navigationEvents.observeSafely(::navigate)
    }

    fun setNavigationBackEnabled(isEnabled: Boolean) {
        navigateBackCallback.isEnabled = isEnabled
    }

    override fun navigateBack() {
        if (canNavigateBack) navController.popBackStack()
    }

    override fun navigate(event: NavigationEvent) {
        event.destination?.let { destination ->
            navController.currentDestination?.getAction(destination.actionId)
                ?.also { navController.navigate(destination, event.options) }
        }
    }

    fun <T> LiveData<T>.observeSafely(block: (T) -> Unit) =
        observe(viewLifecycleOwner) { it?.let(block) }

    fun <T> Flow<T>.observeSafely(block: (T) -> Unit) =
        lifecycleScope.launchWhenStarted { collect(block) }
}