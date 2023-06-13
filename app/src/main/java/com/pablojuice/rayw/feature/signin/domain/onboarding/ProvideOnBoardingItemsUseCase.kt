package com.pablojuice.rayw.feature.signin.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.signin.data.local.OnBoarding
import javax.inject.Inject

class ProvideOnBoardingItemsUseCase @Inject constructor() {

    operator fun invoke(): List<OnBoardingListItem> = OnBoarding.values()
        .map {
            OnBoardingListItem(
                icon = it.icon,
                title = it.title.asLabel(),
                description = it.description.asLabel()
            )
        }
}

data class OnBoardingListItem(
    val icon: Int, val title: Label, val description: Label
) : ListItem(R.layout.item_onboarding)