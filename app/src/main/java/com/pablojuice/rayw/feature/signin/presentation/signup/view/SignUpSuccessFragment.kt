package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentSignupSuccessBinding
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpSuccessFragment : BasicFragment<FragmentSignupSuccessBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupSuccessBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.successProceed.setOnClickListener {
            navigate(BackToHomeScreen())
        }
    }
}