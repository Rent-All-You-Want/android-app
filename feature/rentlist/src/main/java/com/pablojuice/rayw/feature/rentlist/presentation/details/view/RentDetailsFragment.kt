package com.pablojuice.rayw.feature.rentlist.presentation.details.view

import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.pablojuice.core.presentation.utils.getColorFromAttribute
import com.pablojuice.core.presentation.view.animation.list.AlphaPageTransformer
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.core.presentation.view.toolbar.setTitleLabel
import com.pablojuice.rayw.feature.rentlist.R
import com.pablojuice.core.presentation.R as CoreR
import com.pablojuice.rayw.feature.rentlist.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rentlist.databinding.FragmentRentDetailsBinding
import com.pablojuice.rayw.feature.rentlist.presentation.details.list.RentDetailsImage
import com.pablojuice.rayw.feature.rentlist.presentation.details.list.RentDetailsImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentDetailsFragment : BasicFragment<FragmentRentDetailsBinding, RentDetailsViewModel>() {

    override val viewModel: RentDetailsViewModel by viewModels()

    override val layoutClass = FragmentRentDetailsBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setNavigationClickListener(::navigateBack)
        setupListeners()
        viewModel.itemDetails.observe(::setItemDetails)
        arguments?.let { args ->
            viewModel.fetchDetailsForItem(RentDetailsFragmentArgs.fromBundle(args).rentId)
        }
    }

    private fun setupListeners() {
        binding.next.setOnClickListener {
            val currentPosition = binding.imagePager.currentItem
            if (currentPosition < binding.imagePager.adapter!!.itemCount - 1) {
                binding.imagePager.setCurrentItem(currentPosition + 1, true)
            }
        }
        binding.previous.setOnClickListener {
            val currentPosition = binding.imagePager.currentItem
            if (currentPosition > 0) {
                binding.imagePager.setCurrentItem(currentPosition - 1, true)
            }
        }
        binding.imagePager.setPageTransformer(AlphaPageTransformer())
        binding.imagePager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.previous.setVisible(position > 0)
                binding.next.setVisible(position < binding.imagePager.adapter!!.itemCount - 1)
            }
        })
    }

    private fun setItemDetails(item: RentDetailsItem?) {
        item?.run {
            binding.imagePager.adapter = RentDetailsImageAdapter(icon.map { RentDetailsImage(it) })
            binding.dateLabel.setLabel(timeAdded)
            binding.itemToolBar.setTitleLabel(title)
            binding.ratingLabel.setLabel(rating)
            binding.availabilityIcon.setColorFilter(
                requireContext().getColorFromAttribute(
                    if (isCurrentlyAvailable) CoreR.attr.colorPositive else CoreR.attr.colorNegative
                ),
                PorterDuff.Mode.SRC_OVER
            )
            binding.availablityLabel.setLabel((if (isCurrentlyAvailable) R.string.rent_details_rent_available else R.string.rent_details_rent_unavailable).asLabel())
            binding.lastRentLabel.setLabel(lastRentDescription)
            binding.priceLabel.setLabel(price)
            binding.currencyLabel.setLabel(priceCurrency)
            binding.priceDescriptionLabel.setLabel(priceDescription)
            binding.descriptionLabel.setLabel(description)
            binding.locationLabel.setLabel(location)
            binding.chatButton.setClickListener(viewModel::requestRent)
        }
    }
}