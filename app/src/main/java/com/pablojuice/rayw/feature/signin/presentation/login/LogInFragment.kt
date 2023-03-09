package com.pablojuice.rayw.feature.signin.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentLoginBinding
import com.pablojuice.rayw.feature.signin.presentation.navigation.ToRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.navigation.ToSignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BasicFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentLoginBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.setIconClickListener(::navigateBack)
        binding.recoverPasswordView.setOnClickListener { navigate(ToRecoveryScreen()) }
        binding.registerButton.setOnClickListener { navigate(ToSignUpScreen()) }
    }
}