package com.pablojuice.rayw.feature.dev

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pablojuice.core.presentation.base.screen.BaseFragment
import com.pablojuice.rayw.databinding.FragmentDevOptionsBinding

class DevOptionsFragment : BaseFragment<FragmentDevOptionsBinding>() {

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean,
    ) = FragmentDevOptionsBinding.inflate(inflater, container, attachToParent)
}
