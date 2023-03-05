package com.pablojuice.core.app.dev

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pablojuice.core.databinding.FragmentDevOptionsBinding
import com.pablojuice.core.presentation.base.screen.BaseFragment

class DevOptionsFragment : BaseFragment<FragmentDevOptionsBinding>() {

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean,
    ) = FragmentDevOptionsBinding.inflate(inflater, container, attachToParent)
}
