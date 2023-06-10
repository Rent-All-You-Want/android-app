package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import com.pablojuice.core.presentation.navigation.context.alert.ShowSnackBarAlertEvent
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.rent_create.R

class ToImageIsAlreadyLoadedSnackBar :
    ShowSnackBarAlertEvent(R.string.rent_create_image_is_already_loaded.asLabel())