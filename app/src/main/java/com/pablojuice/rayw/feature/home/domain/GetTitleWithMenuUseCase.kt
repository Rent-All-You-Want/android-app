package com.pablojuice.rayw.feature.home.domain

import com.pablojuice.rayw.feature.home.data.HomeMenuData
import javax.inject.Inject

class GetTitleWithMenuUseCase @Inject constructor() {

    operator fun invoke(itemId: Int): HomeMenuData =
        HomeMenuData.values().firstOrNull { it.id == itemId } ?: HomeMenuData.EMPTY
}