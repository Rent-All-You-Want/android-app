package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import com.pablojuice.core.presentation.navigation.directional.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.rayw.feature.rent_create.presentation.view.category.ChooseRentCategoryFragmentDirections

class ToChooseRentSubCategoriesScreen : AnimatedNavigationEvent(
    destination = ChooseRentCategoryFragmentDirections.toSubCategory(),
    navigationAnimation = NavigationAnimation.SlideToRight()
)