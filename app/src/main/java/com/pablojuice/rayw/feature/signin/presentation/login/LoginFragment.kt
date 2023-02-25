package com.pablojuice.rayw.feature.signin.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BasicFragment<FragmentHomeBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentHomeBinding.inflate(inflater, container, attachToParent)
}