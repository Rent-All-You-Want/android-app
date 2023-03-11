package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentSignupStepTwoBinding
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSuccessSignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStepTwoFragment : BasicFragment<FragmentSignupStepTwoBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupStepTwoBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recoveryProceed.setOnClickListener {
            navigate(ToSuccessSignUpScreen())
        }
    }
}