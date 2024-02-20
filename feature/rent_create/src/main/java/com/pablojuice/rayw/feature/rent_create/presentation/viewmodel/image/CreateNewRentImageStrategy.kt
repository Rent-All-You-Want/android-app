package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image

import android.net.Uri
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.picker.RentImagePickerAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CreateNewRentImageStrategy : RentImagePickerAdapter.Listener {

    val imageList: Flow<List<ListItem>>
    val currentSelectedImage: StateFlow<RentImagePreviewItem?>

    fun setCurrentSelectedImage(position: Int)
    fun makeCurrentSelectedImageMain()
    fun removeCurrentSelectedImage()

    fun onAttachClick()
    fun onMediaSelected(uriList: List<Uri>)
    fun getMaxMedia(): Int

    fun setImagePickerListener(listener: () -> Unit)
}

