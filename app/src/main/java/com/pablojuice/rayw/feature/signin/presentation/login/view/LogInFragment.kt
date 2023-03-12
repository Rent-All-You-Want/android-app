package com.pablojuice.rayw.feature.signin.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentLoginBinding
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToSignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BasicFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by hiltNavGraphViewModels(R.id.signin_graph)

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
//        binding.container.layoutTransition.disableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
        binding.logInButton.setOnClickListener { viewModel.logIn() }
        binding.loginToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.loginIcon.setVisible(!isKeyboardVisible)
        }
        binding.recoverPasswordView.setOnClickListener { navigate(ToRecoveryScreen()) }
        binding.signUpButton.setOnClickListener { navigate(ToSignUpScreen()) }
    }

    override fun onDestroyView() {
        hideKeyboardIfOpened()
        super.onDestroyView()
    }
}