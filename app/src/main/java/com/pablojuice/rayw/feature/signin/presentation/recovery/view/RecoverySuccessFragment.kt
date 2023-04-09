package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRecoverySuccessBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel.RecoveryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverySuccessFragment : BasicFragment<FragmentRecoverySuccessBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by hiltNavGraphViewModels(R.id.signin_recovery_graph)

    override val layoutClass = FragmentRecoverySuccessBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() {
        binding.successProceed.setClickListener(viewModel::backToLoginScreen)
    }
}