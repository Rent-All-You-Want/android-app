package com.pablojuice.rayw.feature.dev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import com.pablojuice.core.presentation.view.fragment.BaseFragment
import com.pablojuice.rayw.databinding.FragmentDevOptionsBinding

class DevOptionsFragment : BaseFragment<FragmentDevOptionsBinding>() {

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean,
    ) = FragmentDevOptionsBinding.inflate(inflater, container, attachToParent)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextFields()
        setupButtons()
    }

    private fun setupTextFields() {
        for (i in 0 until binding.textInputContainer.childCount) {
            val view = binding.textInputContainer.getChildAt(i)
            (view as? TextInputLayout)?.run {
                if (isErrorEnabled) {
                    error = "Error!!!"
                }
            }
        }
    }

    private fun setupButtons() {

    }
}
