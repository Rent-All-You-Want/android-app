package com.pablojuice.rayw.feature.signin.presentation.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryFragment : BasicFragment<FragmentHomeBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentHomeBinding.inflate(inflater, container, attachToParent)
}