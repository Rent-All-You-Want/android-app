package com.pablojuice.rayw.feature.signin.data.local

import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.R

sealed class OnBoardingItem(
    val icon: Int, val title: Label, val description: Label
) : ListItem(R.layout.item_onboarding) {

    class FirstOnBoardingItem : OnBoardingItem(
        icon = R.drawable.ic_onboarding_1,
        title = R.string.first_onboarding_title.asLabel(),
        description = R.string.first_onboarding_description.asLabel()
    )

    class SecondOnBoardingItem : OnBoardingItem(
        icon = R.drawable.ic_onboarding_2,
        title = R.string.second_onboarding_title.asLabel(),
        description = R.string.second_onboarding_description.asLabel()
    )

    class ThirdOnBoardingItem : OnBoardingItem(
        icon = R.drawable.ic_onboarding_3,
        title = R.string.third_onboarding_title.asLabel(),
        description = R.string.third_onboarding_description.asLabel()
    )
}