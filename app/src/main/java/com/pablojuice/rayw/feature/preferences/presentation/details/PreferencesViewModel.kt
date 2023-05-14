package com.pablojuice.rayw.feature.preferences.presentation.details

import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.app.settings.language.AppLanguage
import com.pablojuice.core.app.settings.theme.AppTheme
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
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
        Timber.e("setapplang ${lang.code}")
        changeLanguage(lang)
        _appLanguage.value = getAppLanguage()
    }

    fun setAppTheme(themeNumber: Int) {
        val theme = AppTheme.values()[themeNumber]
        Timber.e("setapptheme ${theme.code}")
        changeTheme(theme)
        _appTheme.value = getAppTheme()
    }

    fun getBuildTitle(): String = appConfig.info.appName

    fun getBuildDescription(): String =
        appConfig.info.run { "$packageName \n v$versionName($versionCode) \n by $creatorName" }

    private fun getAppLanguage(): AppLanguage =
        AppLanguage.fromCode(userPreferences.getUnsafe(UserPreference.APP_LANGUAGE))

    private fun getAppTheme(): AppTheme =
        AppTheme.fromCode(userPreferences.getUnsafe(UserPreference.APP_THEME))

}