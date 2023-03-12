package com.pablojuice.rayw.feature.signin.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentSignupSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpSuccessFragment : BasicFragment<FragmentSignupSuccessBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signin_graph)

    override val canNavigateBack: Boolean = false

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentSignupSuccessBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.successProceed.setOnClickListener { viewModel.backToHomeScreen() }
    }
}