package com.pablojuice.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    lateinit var binding: VB
    abstract val viewModel: VM
    val navController: NavController by lazy { Navigation.findNavController(requireView()) }

    protected abstract fun bindLayout(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = bindLayout(inflater, container)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    protected open fun navigate(destination: NavDirections) {
        navController.currentDestination?.getAction(destination.actionId)
            ?.also { navController.navigate(destination) }
    }

    protected open fun navigate(@IdRes resId: Int) {
        navController.currentDestination?.getAction(resId)
            ?.also { navController.navigate(it.destinationId) }
    }
}