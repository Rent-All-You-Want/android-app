package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image

import android.net.Uri
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.domain.*
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToImageIsAlreadyLoadedSnackBar
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToRentImagePreviewScreen
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.picker.RentImagePickerImageViewHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val MAX_MEDIA = 8
private const val MIN_MEDIA = 1

class CreateNewRentImageViewModel @Inject constructor(
    private val convertToImageList: ConvertUrisToImageListUseCase,
    private val makeImageMain: MakeImageMainUseCase,
    private val removeSelectedImage: RemoveSelectedImageUseCase,
    private val dragAndDropImage: DragAndDropImageUseCase
) : BasicViewModel(), CreateNewRentImageStrategy {

    private lateinit var imagePickerListener: () -> Unit

    private val _imageList = MutableStateFlow(emptyList<ListItem>())
    override val imageList: Flow<List<ListItem>> = _imageList

    private val _currentSelectedImage = MutableStateFlow<RentImagePreviewItem?>(null)
    override val currentSelectedImage: StateFlow<RentImagePreviewItem?> = _currentSelectedImage

    override fun setCurrentSelectedImage(position: Int) {
        _currentSelectedImage.value = _imageList.value[position] as? RentImagePreviewItem
    }

    override fun makeCurrentSelectedImageMain() {
        currentSelectedImage.value?.let {
            _imageList.value = makeImageMain(it, _imageList.value.toMutableList())
        }
    }

    override fun removeCurrentSelectedImage() {
        currentSelectedImage.value?.let {
            val currentItemList = removeSelectedImage(it, _imageList.value.toMutableList())
            if (currentItemList.size < 1) submitNavigationEvent(NavigationEvents.Back)
            _imageList.value = currentItemList
        }
    }

    override fun onMediaSelected(uriList: List<Uri>) {
        val currentItemList = _imageList.value.toMutableList()
        currentItemList.addAll(
            filterImages(currentItemList, convertToImageList(uriList))
        )
        currentItemList.updateMainItem()
        _imageList.value = currentItemList
    }

    override fun getMaxMedia(): Int = MAX_MEDIA

    override fun setImagePickerListener(listener: () -> Unit) {
        imagePickerListener = listener
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

    override fun onAttachClick() = imagePickerListener()

    override fun onImageClick(item: RentImagePickerImageViewHolder) {
        _currentSelectedImage.value = item.currentItem
        submitNavigationEvent(
            ToRentImagePreviewScreen(
                FragmentNavigatorExtras(item.getSharedView() to item.currentItem?.imageUri.toString())
            )
        )
    }

    override fun onDragAndDrop(previous: Int, target: Int) {
        _imageList.value = dragAndDropImage(previous, target, _imageList.value.toMutableList())
    }
}