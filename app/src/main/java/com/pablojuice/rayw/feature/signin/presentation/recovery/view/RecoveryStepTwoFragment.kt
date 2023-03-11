package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentRecoveryStepTwoBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToThirdRecoveryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryStepTwoFragment : BasicFragment<FragmentRecoveryStepTwoBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentRecoveryStepTwoBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recoveryProceed.setOnClickListener {
            navigate(ToThirdRecoveryScreen())
        }
    }
}