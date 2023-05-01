package com.pablojuice.core.presentation.view.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

fun ConstraintLayout.LayoutParams.constraintWidth(widthRatio: Int, heightRatio: Int) =
    "w, $widthRatio:$heightRatio"

fun ConstraintLayout.LayoutParams.constraintHeight(widthRatio: Int, heightRatio: Int) =
    "h, $widthRatio:$heightRatio"

inline val ViewGroup.layoutInflater: LayoutInflater
    inline get() = LayoutInflater.from(context)