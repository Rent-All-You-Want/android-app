package com.pablojuice.rayw.feature.rent_create.domain

import android.net.Uri
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.RentImagePreviewItem
import javax.inject.Inject

class ConvertUrisToImageListUseCase @Inject constructor() {

    operator fun invoke(uriList: List<Uri>): List<RentImagePreviewItem> =
        mutableListOf<RentImagePreviewItem>().apply {
            uriList.forEach { uri -> add(RentImagePreviewItem(uri, false)) }
        }
}