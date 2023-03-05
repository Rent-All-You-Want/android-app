package com.pablojuice.rayw.feature.signin.domain.usecase

import com.pablojuice.rayw.feature.signin.data.local.OnBoardingItem
import javax.inject.Inject

class ProvideOnBoardingItemsUseCase @Inject constructor() {

    operator fun invoke(): List<OnBoardingItem> = listOf(
        OnBoardingItem.FirstOnBoardingItem(),
        OnBoardingItem.SecondOnBoardingItem(),
        OnBoardingItem.ThirdOnBoardingItem()
    )
}