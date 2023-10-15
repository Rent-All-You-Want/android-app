package com.pablojuice.rayw.feature.preferences.presentation.details

import android.widget.EditText
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.preferences.databinding.FragmentPreferencesAppSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesAppSettingsFragment :
    BasicFragment<FragmentPreferencesAppSettingsBinding, PreferencesViewModel>() {

    override val viewModel: PreferencesViewModel by viewModels()

    override val layoutClass = FragmentPreferencesAppSettingsBinding::class.java

    override fun setupScreen() {
        binding.preferencesToolBar.setNavigationClickListener(::navigateBack)
        binding.languageInput.editText.setItems(viewModel.getAvailableLanguages())
        binding.themeInput.editText.setItems(viewModel.getAvailableThemes())
        binding.languageInput.editText.setOnItemClickListener(viewModel::setAppLanguage)
        binding.themeInput.editText.setOnItemClickListener(viewModel::setAppTheme)
        viewModel.appLanguage.observe { binding.languageInput.editText.setSelectedItem(it.displayName) }
        viewModel.appTheme.observe { binding.themeInput.editText.setSelectedItem(it.displayName) }
    }

    private fun EditText?.setItems(items: List<Label>) {
        val transformedItems = items.map { it.get(requireContext()) }.toTypedArray()
        (this as MaterialAutoCompleteTextView).setSimpleItems(transformedItems)
    }

    private fun EditText?.setOnItemClickListener(onClick: (Int) -> Unit) {
        (this as MaterialAutoCompleteTextView).setOnItemClickListener { _, _, position, _ ->
            onClick(position)
        }
    }

    private fun EditText?.setSelectedItem(item: Label) {
        (this as MaterialAutoCompleteTextView).setText(item.get(context), false)
    }
}