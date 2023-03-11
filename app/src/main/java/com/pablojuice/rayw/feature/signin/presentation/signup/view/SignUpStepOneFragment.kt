package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.databinding.FragmentSignupStepOneBinding
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSecondSignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStepOneFragment : BasicFragment<FragmentSignupStepOneBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupStepOneBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.proceedButton.setOnClickListener { navigate(ToSecondSignUpScreen()) }
        binding.signupToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.signupIcon.setVisible(!isKeyboardVisible)
        }
        binding.returnToLoginButton.setOnClickListener { navigateBack() }
    }
}