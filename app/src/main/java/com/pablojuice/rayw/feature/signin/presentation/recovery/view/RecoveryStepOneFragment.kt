package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRecoveryStepOneBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToSecondRecoveryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryStepOneFragment : BasicFragment<FragmentRecoveryStepOneBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentRecoveryStepOneBinding::class.java

    override fun setupScreen() {
        binding.recoveryProceed.setClickListener { navigate(ToSecondRecoveryScreen()) }
    }
}