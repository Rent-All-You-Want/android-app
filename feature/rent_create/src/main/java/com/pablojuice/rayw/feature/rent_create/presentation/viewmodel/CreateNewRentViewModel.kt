package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel

import android.net.Uri
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.domain.ConvertUrisToImageListUseCase
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePickerAttachItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerImageViewHolder
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToImageIsAlreadyLoadedSnackBar
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToRentImagePreviewScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Collections
import javax.inject.Inject

private const val MAX_MEDIA = 8

@HiltViewModel
class CreateNewRentViewModel @Inject constructor(
    private val convertToImageList: ConvertUrisToImageListUseCase
) : BasicViewModel(), RentImagePickerAdapter.Listener {

    private lateinit var imagePickerListener: () -> Unit

    private val _imageList = MutableStateFlow(listOf<ListItem>(RentImagePickerAttachItem()))
    val imageList: Flow<List<ListItem>> = _imageList

    private val _currentSelectedImage = MutableStateFlow<RentImagePreviewItem?>(null)
    val currentSelectedImage: StateFlow<RentImagePreviewItem?> = _currentSelectedImage

    fun setCurrentSelectedImage(position: Int) {
        _currentSelectedImage.value = _imageList.value[position] as? RentImagePreviewItem
    }

    fun getMaxMedia(): Int = MAX_MEDIA

    fun setImagePickerListener(listener: () -> Unit) {
        imagePickerListener = listener
    }

    fun makeCurrentSelectedImageMain() {
        currentSelectedImage.value?.let {
            val currentItemList = _imageList.value.toMutableList()
            currentItemList.remove(it)
            currentItemList.add(0, it)
            currentItemList.updateMainItem()
            _imageList.value = currentItemList
        }
    }

    fun removeCurrentSelectedImage() {
        currentSelectedImage.value?.let {
            val currentItemList = _imageList.value.toMutableList()
            currentItemList.remove(it)
            currentItemList.updateImageAttachItem()
            currentItemList.updateMainItem()
            if (currentItemList.size <= 1) submitNavigationEvent(NavigationEvents.BackNavigationEvent)
            _imageList.value = currentItemList
        }
    }

    fun onMediaSelected(uriList: List<Uri>) {
        val currentItemList = _imageList.value.toMutableList()
        val indexToAdd = currentItemList.indexOfFirst { it is RentImagePickerAttachItem }
        currentItemList.addAll(
            indexToAdd,
            filterImages(currentItemList, convertToImageList(uriList))
        )
        currentItemList.updateImageAttachItem()
        currentItemList.updateMainItem()
        _imageList.value = currentItemList
    }

    override fun onAttachClick() = imagePickerListener()

    override fun onImageClick(item: RentImagePickerImageViewHolder) {
        _currentSelectedImage.value = item.currentItem
        submitNavigationEvent(
            ToRentImagePreviewScreen(
                FragmentNavigatorExtras(item.getSharedView() to item.currentItem?.imageUri.toString())
            )
        )
    }

    override fun onDragAndDrop(previousPosition: Int, targetPosition: Int) {
        val currentItemList = _imageList.value.toMutableList()
        Collections.swap(currentItemList, previousPosition, targetPosition)
        if (previousPosition == 0 || targetPosition == 0) currentItemList.updateMainItem()
        _imageList.value = currentItemList
    }

    private fun List<ListItem>.updateImageAttachItem() {
        val listIsNotEmpty = size > 1
        forEach { item ->
            if (item is RentImagePickerAttachItem) item.alreadyContainsImages = listIsNotEmpty
        }
    }

    private fun List<ListItem>.updateMainItem() {
        forEach { item -> if (item is RentImagePreviewItem) item.isMainImage = false }
        firstOrNull()?.let { if (it is RentImagePreviewItem) it.isMainImage = true }
    }

    private fun filterImages(
        sourceList: List<ListItem>,
        listToAdd: List<RentImagePreviewItem>
    ): List<ListItem> {
        val filteredList = mutableListOf<ListItem>()
        listToAdd.forEach { item -> if (!sourceList.contains(item)) filteredList.add(item) }
        if (filteredList.size != listToAdd.size) {
            submitNavigationEvent(ToImageIsAlreadyLoadedSnackBar())
        }
        return filteredList
    }
}