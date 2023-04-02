package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.view.View
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.alert.showDialog
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.label.setErrorLabel
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupStepTwoBinding
import com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStepTwoFragment : BasicFragment<FragmentSignupStepTwoBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentSignupStepTwoBinding::class.java

    private val dateDialog by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.signin_birth_date))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }

    override fun setupScreen() {
        setupStateListener()
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.signupIcon.setVisible(!isKeyboardVisible)
            if (isVisible) keepFocusAfterAnimation()
        }
        binding.signupToolBar.setNavigationClickListener(::navigateBack)
        binding.proceedButton.setOnClickListener {
            hideKeyboardIfOpened()
            viewModel.proceedToSuccessStep()
        }
    }

    private fun setupStateListener() {
        dateDialog.addOnPositiveButtonClickListener(viewModel::setBirthDate)
        binding.dateInputText.setOnClickListener { showDialog(dateDialog) }
        binding.nameInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setName(text.toString())
        }
        viewModel.state.observe { state ->
            binding.nameInputLayout.setErrorLabel(state.nameError)
            binding.dateInputLayout.editText?.setText(state.birthDate)
            binding.dateInputLayout.setErrorLabel(state.birthDateError)
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