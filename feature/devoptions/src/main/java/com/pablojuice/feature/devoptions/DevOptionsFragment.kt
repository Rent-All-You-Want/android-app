package com.pablojuice.feature.devoptions

import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.pablojuice.core.presentation.view.fragment.BaseFragment
import com.pablojuice.rayw.feature.devoptions.databinding.FragmentDevOptionsBinding

class DevOptionsFragment : BaseFragment<FragmentDevOptionsBinding>() {

    override val layoutClass = FragmentDevOptionsBinding::class.java

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
