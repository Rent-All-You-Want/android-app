package com.pablojuice.core.presentation.view.alert

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.pablojuice.core.presentation.navigation.ContextNavigationEvent
import com.pablojuice.core.presentation.view.label.Label

class ShowToastAlertEvent(label: Label, length: Length = Length.SHORT) :
    ContextNavigationEvent({ context ->
        Toast.makeText(context, label.get(context), length.duration).show()
    }) {

    enum class Length(val duration: Int) {
        SHORT(LENGTH_SHORT), LONG(LENGTH_LONG)
    }
}