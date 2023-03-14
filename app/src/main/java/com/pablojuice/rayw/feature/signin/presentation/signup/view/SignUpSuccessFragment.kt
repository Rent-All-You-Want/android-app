package com.pablojuice.rayw.feature.signin.presentation.signup.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpSuccessFragment : BasicFragment<FragmentSignupSuccessBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val layoutClass = FragmentSignupSuccessBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() =
        binding.successProceed.setClickListener(viewModel::backToHomeScreen)
}