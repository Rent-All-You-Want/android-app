package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRecoveryStepTwoBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToThirdRecoveryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryStepTwoFragment : BasicFragment<FragmentRecoveryStepTwoBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentRecoveryStepTwoBinding::class.java

    override fun setupScreen() {
        binding.recoveryProceed.setOnClickListener {
            navigate(ToThirdRecoveryScreen())
        }
    }
}