package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.view.View
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.text.label.setErrorLabel
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupStepOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStepOneFragment : BasicFragment<FragmentSignupStepOneBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentSignupStepOneBinding::class.java

    override fun setupScreen() {
        setupStateListener()
        binding.signupToolBar.setNavigationClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.signupIcon.setVisible(!isKeyboardVisible)
            if (isVisible) keepFocusAfterAnimation()
        }
        binding.proceedButton.setClickListener(viewModel::proceedToSecondStep)
        binding.returnToLoginButton.setClickListener(::navigateBack)
    }

    private fun setupStateListener() {
        binding.emailInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString())
        }
        binding.passwordInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString())
        }
        binding.acceptRulesSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setAcceptRules(isChecked)
        }
        viewModel.state.observe { state ->
            binding.emailInputLayout.setErrorLabel(state.emailError)
            binding.passwordInputLayout.setErrorLabel(state.passwordError)
            binding.acceptRulesError.setLabel(state.acceptedRulesError)
        }
    }

    private fun keepFocusAfterAnimation() {
        var focusedView: View? = null
        binding.signupContainer.children.iterator().forEach { view ->
            if (view.hasFocus()) focusedView = view
        }
        binding.signupIcon.post { focusedView?.requestFocus() }
    }

    override fun onPause() {
        hideKeyboardIfOpened()
        super.onPause()
    }
}