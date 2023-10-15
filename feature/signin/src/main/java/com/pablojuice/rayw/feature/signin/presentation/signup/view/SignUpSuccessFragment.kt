package com.pablojuice.rayw.feature.signin.presentation.signup.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.feature.signin.databinding.FragmentSignupSuccessBinding
import com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.presentation.R as CoreR

@AndroidEntryPoint
class SignUpSuccessFragment : BasicFragment<FragmentSignupSuccessBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(CoreR.id.signin_signup_graph)

    override val layoutClass = FragmentSignupSuccessBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() =
        binding.successProceed.setClickListener(viewModel::backToHomeScreen)
}