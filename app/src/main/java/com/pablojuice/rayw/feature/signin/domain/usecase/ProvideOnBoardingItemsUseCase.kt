package com.pablojuice.rayw.feature.signin.domain.usecase

import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.signin.data.local.OnBoardingItems
import javax.inject.Inject

class ProvideOnBoardingItemsUseCase @Inject constructor() {

    operator fun invoke(): List<OnBoardingListItem> = OnBoardingItems.values()
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