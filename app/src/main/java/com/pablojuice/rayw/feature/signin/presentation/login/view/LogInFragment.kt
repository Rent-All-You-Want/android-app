package com.pablojuice.rayw.feature.signin.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.text.label.setErrorLabel
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
class LogInFragment : BasicFragment<FragmentLoginBinding, LogInViewModel>() {

    override val viewModel: LogInViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentLoginBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupStateListener()
    }

    private fun setupListeners() {
        binding.logInButton.setOnClickListener { viewModel.logIn() }
        binding.loginToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.loginIcon.setVisible(!isKeyboardVisible)
            if (isKeyboardVisible) keepFocusAfterAnimation()
        }
        binding.recoverPasswordView.setOnClickListener { navigate(ToRecoveryScreen()) }
        binding.signUpButton.setOnClickListener { navigate(ToSignUpScreen()) }
    }

    private fun setupStateListener() {
        binding.emailInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString())
        }
        binding.passwordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString())
        }
        viewModel.state.observeCleanable { state ->
            binding.emailInputLayout.setErrorLabel(state.emailError)
            binding.passwordInputLayout.setErrorLabel(state.passwordError)
        }
    }

    private fun keepFocusAfterAnimation() {
        var focusedView: View? = null
        binding.logInContainer.children.iterator().forEach { view ->
            if (view.hasFocus()) focusedView = view
        }
        binding.loginIcon.post { focusedView?.requestFocus() }
    }

    override fun onPause() {
        hideKeyboardIfOpened()
        super.onPause()
    }
}