package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseAttributesBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list.AttributesSelectionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentAttributesFragment :
    BasicFragment<FragmentRentCreateChooseAttributesBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseAttributesBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        binding.addAttributeBtn.setClickListener(viewModel::addAttribute)
        binding.recycler.adapter = AttributesSelectionListAdapter(viewModel)
        viewModel.attributes.observe { items ->
            (binding.recycler.adapter as? AttributesSelectionListAdapter)?.setItems(items)
        }
        viewModel.canHaveMoreAttributes.observe { canHave ->
            binding.addAttributeBtn.setVisible(
                canHave
            )
        }
    }
}