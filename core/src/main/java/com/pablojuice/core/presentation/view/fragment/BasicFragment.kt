package com.pablojuice.core.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationHandler
import com.pablojuice.core.presentation.viewmodel.BasicViewModel

abstract class BasicFragment<VB : ViewBinding, VM : BasicViewModel> : BaseFragment<VB>(),
    NavigationHandler {
    abstract val viewModel: VM

    protected open val canNavigateBack = true

    private val navController: NavController by lazy { findNavController() }

    private lateinit var navigateBackCallback: OnBackPressedCallback

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
    }

    private fun setupNavigation() {
        navigateBackCallback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { navigateBack() }
        submitJob(viewModel.navigationEvents.observe(::navigate))
    }

    fun setNavigationBackEnabled(isEnabled: Boolean) {
        navigateBackCallback.isEnabled = isEnabled
    }

    override fun navigateBack() {
        if (canNavigateBack) navController.popBackStack()
    }

    override fun navigate(event: NavigationEvent) = event.handle(navController)
}