package com.pablojuice.rayw.feature.signin.data.local

import com.pablojuice.rayw.R

enum class OnBoardingItems(val icon: Int, val title: Int, val description: Int) {

    FIRST_ITEM(
        icon = R.drawable.ic_onboarding_1,
        title = R.string.first_onboarding_title,
        description = R.string.first_onboarding_description
    ),

    SECOND_ITEM(
        icon = R.drawable.ic_onboarding_2,
        title = R.string.second_onboarding_title,
        description = R.string.second_onboarding_description
    ),

    THIRD_ITEM(
        icon = R.drawable.ic_onboarding_3,
        title = R.string.third_onboarding_title,
        description = R.string.third_onboarding_description
    )
}