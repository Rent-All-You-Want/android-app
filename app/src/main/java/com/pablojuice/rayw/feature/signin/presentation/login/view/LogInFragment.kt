package com.pablojuice.rayw.feature.signin.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.databinding.FragmentLoginBinding
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToLogInSuccessScreen
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToSignUpScreen
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
        setupListeners()
    }

    private fun setupListeners() {
        binding.logInButton.setOnClickListener {
            navigate(ToLogInSuccessScreen())
        }
        binding.loginToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.loginIcon.setVisible(!isKeyboardVisible)
        }
        binding.recoverPasswordView.setOnClickListener { navigate(ToRecoveryScreen()) }
        binding.signUpButton.setOnClickListener { navigate(ToSignUpScreen()) }
    }
}