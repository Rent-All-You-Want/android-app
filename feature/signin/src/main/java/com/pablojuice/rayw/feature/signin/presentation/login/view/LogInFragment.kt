package com.pablojuice.rayw.feature.signin.presentation.login.view

import android.view.View
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.text.setErrorLabel
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.signin.databinding.FragmentLoginBinding
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToSignUpScreen
import com.pablojuice.rayw.feature.signin.presentation.login.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.presentation.R as CoreR

@AndroidEntryPoint
class LogInFragment : BasicFragment<FragmentLoginBinding, LogInViewModel>() {

    override val viewModel: LogInViewModel by hiltNavGraphViewModels(CoreR.id.signin_graph)

    override val layoutClass = FragmentLoginBinding::class.java

    override fun setupScreen() {
        setupStateListener()
        binding.logInButton.setOnClickListener {
            hideKeyboardIfOpened()
            viewModel.logIn()
        }
        binding.loginToolBar.setNavigationClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.loginIcon.setVisible(!isKeyboardVisible)
            if (isKeyboardVisible) keepFocusAfterAnimation()
        }
        binding.recoverPasswordView.setOnClickListener { navigate(ToRecoveryScreen()) }
        binding.signUpButton.setOnClickListener { navigate(ToSignUpScreen()) }
    }

    private fun setupStateListener() {
        binding.emailInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString().trim())
        }
        binding.passwordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString().trim())
        }
        viewModel.state.observe { state ->
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