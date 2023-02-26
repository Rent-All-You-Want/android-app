package com.pablojuice.rayw.feature.signin.presentation.onboarding.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.base.list.Adapter
import com.pablojuice.core.presentation.base.list.ViewHolder
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.rayw.databinding.ItemOnboardingBinding
import com.pablojuice.rayw.feature.signin.domain.usecase.OnBoardingListItem

class OnBoardingAdapter(items: List<OnBoardingListItem>) : Adapter<OnBoardingListItem>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnBoardingViewHolder(
        ItemOnboardingBinding.inflate(parent.layoutInflater, parent, false)
    )
}

class OnBoardingViewHolder(binding: ItemOnboardingBinding) :
    ViewHolder<OnBoardingListItem, ItemOnboardingBinding>(binding) {

    override fun bind(item: OnBoardingListItem) {
        binding.icon.setImageResource(item.icon)
        binding.title.setLabel(item.title)
        binding.description.setLabel(item.description)
    }
}