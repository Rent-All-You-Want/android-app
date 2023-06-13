package com.pablojuice.rayw.feature.preferences.presentation.details

import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.app.settings.language.AppLanguage
import com.pablojuice.core.app.settings.theme.AppTheme
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.presentation.navigation.context.alert.ShowSnackBarAlertEvent
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.preferences.R
import com.pablojuice.rayw.feature.preferences.domain.ChangeAppLanguage
import com.pablojuice.rayw.feature.preferences.domain.ChangeAppTheme
import com.pablojuice.rayw.feature.preferences.domain.ProvideAvailableLanguages
import com.pablojuice.rayw.feature.preferences.domain.ProvideAvailableThemeModes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val userPreferences: UserPreferences,
    private val provideLanguages: ProvideAvailableLanguages,
    private val provideThemes: ProvideAvailableThemeModes,
    private val changeLanguage: ChangeAppLanguage,
    private val changeTheme: ChangeAppTheme,
) : BasicViewModel() {

    private val _appLanguage = MutableStateFlow(getAppLanguage())
    val appLanguage: StateFlow<AppLanguage> = _appLanguage

    private val _appTheme = MutableStateFlow(getAppTheme())
    val appTheme: StateFlow<AppTheme> = _appTheme

    fun getAvailableLanguages(): List<Label> = provideLanguages()

    fun getAvailableThemes(): List<Label> = provideThemes()

    fun setAppLanguage(languageNumber: Int) {
        val lang = AppLanguage.values()[languageNumber]
        changeLanguage(lang)
        _appLanguage.value = getAppLanguage()
        submitNavigationEvent(ShowSnackBarAlertEvent(R.string.preference_app_language_changed.asLabel()))
    }

    fun setAppTheme(themeNumber: Int) {
        val theme = AppTheme.values()[themeNumber]
        changeTheme(theme)
        _appTheme.value = getAppTheme()
        submitNavigationEvent(ShowSnackBarAlertEvent(R.string.preference_app_theme_changed.asLabel()))
    }

    fun getBuildTitle(): String = appConfig.info.appName

    fun getBuildDescription(): String =
        appConfig.info.run { "$packageName \n v$versionName($versionCode) \n by $creatorName" }

    private fun getAppLanguage(): AppLanguage =
        AppLanguage.fromCode(userPreferences.getUnsafe(UserPreference.APP_LANGUAGE))

    private fun getAppTheme(): AppTheme =
        AppTheme.fromCode(userPreferences.getUnsafe(UserPreference.APP_THEME))

}