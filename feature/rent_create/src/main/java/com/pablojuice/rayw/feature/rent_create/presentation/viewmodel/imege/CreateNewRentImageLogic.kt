package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.imege

import android.net.Uri
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CreateNewRentImageLogic : RentImagePickerAdapter.Listener {

    val imageList: Flow<List<ListItem>>
    val currentSelectedImage: StateFlow<RentImagePreviewItem?>

    fun setCurrentSelectedImage(position: Int)
    fun makeCurrentSelectedImageMain()
    fun removeCurrentSelectedImage()

    fun onMediaSelected(uriList: List<Uri>)
    fun getMaxMedia(): Int

    fun setImagePickerListener(listener: () -> Unit)
}

