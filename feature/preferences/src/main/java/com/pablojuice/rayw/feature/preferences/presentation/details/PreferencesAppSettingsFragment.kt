package com.pablojuice.rayw.feature.preferences.presentation.details

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.text.setMenuItemClickListener
import com.pablojuice.core.presentation.view.text.setMenuItems
import com.pablojuice.core.presentation.view.text.setMenuSelectedItem
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
        binding.languageInput.editText?.setMenuItems(viewModel.getAvailableLanguages())
        binding.themeInput.editText?.setMenuItems(viewModel.getAvailableThemes())
        binding.languageInput.editText?.setMenuItemClickListener(viewModel::setAppLanguage)
        binding.themeInput.editText?.setMenuItemClickListener(viewModel::setAppTheme)
        viewModel.appLanguage.observe { binding.languageInput.editText?.setMenuSelectedItem(it.displayName) }
        viewModel.appTheme.observe { binding.themeInput.editText?.setMenuSelectedItem(it.displayName) }
    }
}