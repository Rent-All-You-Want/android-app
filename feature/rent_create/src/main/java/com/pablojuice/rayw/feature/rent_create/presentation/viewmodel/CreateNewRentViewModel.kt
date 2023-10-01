package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel

import android.net.Uri
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.domain.ConvertUrisToImageListUseCase
import com.pablojuice.rayw.feature.rent_create.domain.DragAndDropImageUseCase
import com.pablojuice.rayw.feature.rent_create.domain.MakeImageMainUseCase
import com.pablojuice.rayw.feature.rent_create.domain.RemoveSelectedImageUseCase
import com.pablojuice.rayw.feature.rent_create.domain.updateImageAttachItem
import com.pablojuice.rayw.feature.rent_create.domain.updateMainItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePickerAttachItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerImageViewHolder
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCategoriesScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentCharacteristicsScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentDeliveryScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPledgeScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToChooseRentPriceScreen
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToImageIsAlreadyLoadedSnackBar
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.ToRentImagePreviewScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val MAX_MEDIA = 8

@HiltViewModel
class CreateNewRentViewModel @Inject constructor(
    private val convertToImageList: ConvertUrisToImageListUseCase,
    private val makeImageMain: MakeImageMainUseCase,
    private val removeSelectedImage: RemoveSelectedImageUseCase,
    private val dragAndDropImage: DragAndDropImageUseCase
) : BasicViewModel(), RentImagePickerAdapter.Listener {

    private lateinit var imagePickerListener: () -> Unit

    private val _imageList = MutableStateFlow(listOf<ListItem>(RentImagePickerAttachItem()))
    val imageList: Flow<List<ListItem>> = _imageList

    private val _currentSelectedImage = MutableStateFlow<RentImagePreviewItem?>(null)
    val currentSelectedImage: StateFlow<RentImagePreviewItem?> = _currentSelectedImage

    private val _selectedPricingOptions = MutableStateFlow(listOf(RentPricing.HOURLY))
    val selectedPricingOptions: StateFlow<List<RentPricing>> = _selectedPricingOptions

    fun setCurrentSelectedImage(position: Int) {
        _currentSelectedImage.value = _imageList.value[position] as? RentImagePreviewItem
    }

    fun getMaxMedia(): Int = MAX_MEDIA

    fun setImagePickerListener(listener: () -> Unit) {
        imagePickerListener = listener
    }

    fun makeCurrentSelectedImageMain() {
        currentSelectedImage.value?.let {
            _imageList.value = makeImageMain(it, _imageList.value.toMutableList())
        }
    }

    fun removeCurrentSelectedImage() {
        currentSelectedImage.value?.let {
            val currentItemList = removeSelectedImage(it, _imageList.value.toMutableList())
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

    override fun onDragAndDrop(previous: Int, target: Int) {
        _imageList.value = dragAndDropImage(previous, target, _imageList.value.toMutableList())
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

    fun setSelectedPriceOptions(options: List<Int>) {
        _selectedPricingOptions.value = options.map(RentPricing::fromId)
    }

    fun openCategories() {
        submitNavigationEvent(ToChooseRentCategoriesScreen())
    }

    fun openCharacteristics() {
        submitNavigationEvent(ToChooseRentCharacteristicsScreen())
    }

    fun openPricing() {
        submitNavigationEvent(ToChooseRentPriceScreen())
    }

    fun openPledge() {
        submitNavigationEvent(ToChooseRentPledgeScreen())
    }

    fun openDelivery() {
        submitNavigationEvent(ToChooseRentDeliveryScreen())
    }
}