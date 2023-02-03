package com.pablojuice.core.presentation.basic

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.base.BaseFragment
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationHandler

abstract class BasicFragment<VB : ViewBinding, VM : BasicViewModel> : BaseFragment<VB>(),
    NavigationHandler {
    abstract val viewModel: VM

    private val navController: NavController
        get() = Navigation.findNavController(requireView())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigationEvents.observeSafely(::navigate)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigate(event: NavigationEvent) {
        navController.currentDestination?.getAction(event.destination.actionId)
            ?.also { navController.navigate(event.destination) }
    }

    fun <T> LiveData<T>.observeSafely(block: (T) -> Unit) =
        observe(viewLifecycleOwner) { it?.let(block) }
}