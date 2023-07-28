package com.pablojuice.rayw.feature.signin.presentation.login.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.feature.signin.databinding.FragmentLoginSuccessBinding
import com.pablojuice.rayw.feature.signin.presentation.login.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.presentation.R as CoreR

@AndroidEntryPoint
class LogInSuccessFragment : BasicFragment<FragmentLoginSuccessBinding, LogInViewModel>() {

    override val viewModel: LogInViewModel by hiltNavGraphViewModels(CoreR.id.signin_graph)

    override val layoutClass = FragmentLoginSuccessBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() =
        binding.successProceed.setOnClickListener { viewModel.backToHome() }
}