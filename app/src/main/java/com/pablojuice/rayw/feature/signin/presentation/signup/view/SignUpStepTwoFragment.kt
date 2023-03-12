package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.pablojuice.core.presentation.utils.setOnKeyboardVisibilityChangedListener
import com.pablojuice.core.presentation.view.dialog.showDialog
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.fragment.hideKeyboardIfOpened
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupStepTwoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SignUpStepTwoFragment : BasicFragment<FragmentSignupStepTwoBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    private val dateDialog by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("title")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build().also { dialog ->
                dialog.addOnPositiveButtonClickListener {
                    val date = Date(it)
                    Timber.e("fuck $it $date")
                }
            }
    }

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupStepTwoBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.signupToolBar.setIconClickListener(::navigateBack)
        binding.setOnKeyboardVisibilityChangedListener { isKeyboardVisible ->
            binding.signupIcon.setVisible(!isKeyboardVisible)
        }
        binding.dateInputText.setOnClickListener { showDialog(dateDialog) }

        binding.proceedButton.setOnClickListener { viewModel.proceedToSuccessStep() }
    }

    override fun onDestroyView() {
        hideKeyboardIfOpened()
        super.onDestroyView()
    }
}