package com.pablojuice.core.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup

inline val ViewGroup.layoutInflater: LayoutInflater
    inline get() = LayoutInflater.from(context)