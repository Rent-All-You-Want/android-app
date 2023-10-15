package com.pablojuice.rayw.feature.signin.data.local

import com.pablojuice.rayw.feature.signin.R

enum class OnBoarding(val icon: Int, val title: Int, val description: Int) {

    FIRST_SECTION(
        icon = R.drawable.ic_onboarding_1,
        title = R.string.first_onboarding_title,
        description = R.string.first_onboarding_description
    ),

    SECOND_SECTION(
        icon = R.drawable.ic_onboarding_2,
        title = R.string.second_onboarding_title,
        description = R.string.second_onboarding_description
    ),

    THIRD_SECTION(
        icon = R.drawable.ic_onboarding_3,
        title = R.string.third_onboarding_title,
        description = R.string.third_onboarding_description
    )
}