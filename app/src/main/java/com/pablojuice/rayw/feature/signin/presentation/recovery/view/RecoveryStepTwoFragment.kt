package com.pablojuice.rayw.feature.signin.presentation.recovery.view

import android.view.View
import androidx.core.view.children
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRecoveryStepTwoBinding
import com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel.RecoveryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoveryStepTwoFragment : BasicFragment<FragmentRecoveryStepTwoBinding, RecoveryViewModel>() {

    override val viewModel: RecoveryViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentRecoveryStepTwoBinding::class.java

    override fun setupScreen() {
        binding.signupToolBar.setNavigationClickListener(::navigateBack)
        binding.didntReceiveCodeButton.setClickListener(::navigateBack)
        binding.recoverPasswordButton.setClickListener(viewModel::proceedToThirdStep)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.recoveryIcon.setVisible(!isKeyboardVisible)
            if (isKeyboardVisible) keepFocusAfterAnimation()
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