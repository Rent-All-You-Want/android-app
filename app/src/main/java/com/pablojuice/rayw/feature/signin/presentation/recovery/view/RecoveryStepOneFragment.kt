package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import android.view.View
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.label.setErrorLabel
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRecoveryStepOneBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel.RecoveryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryStepOneFragment : BasicFragment<FragmentRecoveryStepOneBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by hiltNavGraphViewModels(R.id.signin_recovery_graph)

    override val layoutClass = FragmentRecoveryStepOneBinding::class.java

    override fun setupScreen() {
        setupStateListener()
        binding.signupToolBar.setNavigationClickListener(::navigateBack)
        binding.iRememberPasswordButton.setClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.recoveryIcon.setVisible(!isKeyboardVisible)
            if (isKeyboardVisible) keepFocusAfterAnimation()
        }
        binding.recoverPasswordButton.setOnClickListener {
            hideKeyboardIfOpened()
            viewModel.proceedToSecondStep()
        }
    }

    private fun setupStateListener() {
        binding.emailInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString().trim())
        }
        viewModel.state.observe { state ->
            binding.emailInputLayout.setErrorLabel(state.emailError)
        }
    }

    private fun keepFocusAfterAnimation() {
        var focusedView: View? = null
        binding.recoveryContainer.children.iterator().forEach { view ->
            if (view.hasFocus()) focusedView = view
        }
        binding.recoveryIcon.post { focusedView?.requestFocus() }
    }

    override fun onPause() {
        hideKeyboardIfOpened()
        super.onPause()
    }
}