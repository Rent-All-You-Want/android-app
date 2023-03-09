package com.pablojuice.rayw.feature.signin.presentation.recovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentRecoveryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryFragment : BasicFragment<FragmentRecoveryBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentRecoveryBinding.inflate(inflater, container, attachToParent)
}