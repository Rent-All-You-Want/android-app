package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.text.label.setErrorLabel
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupStepOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStepOneFragment : BasicFragment<FragmentSignupStepOneBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupStepOneBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupStateListener()
    }

    private fun setupListeners() {
        binding.signupToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.signupIcon.setVisible(!isKeyboardVisible)
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
        viewModel.state.observeCleanable { state ->
            binding.emailInputLayout.setErrorLabel(state.emailError)
            binding.passwordInputLayout.setErrorLabel(state.passwordError)
            binding.acceptRulesError.setLabel(state.acceptedRulesError)
        }
    }

    override fun onDestroyView() {
        hideKeyboardIfOpened()
        super.onDestroyView()
    }
}