package com.pablojuice.core.presentation.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import com.google.android.gms.maps.SupportMapFragment

object GoogleMapUtils {

    fun setupGoogleMapsInitializer(
        context: Context,
        renderer: MapsInitializer.Renderer = MapsInitializer.Renderer.LATEST,
        callback: OnMapsSdkInitializedCallback? = null
    ) = MapsInitializer.initialize(context, renderer, callback)

    fun Fragment.addMapFragmentToContainer(containerId: Int): SupportMapFragment {
        val options = GoogleMapOptions()
            .mapType(1) // "normal"
            .compassEnabled(false)
            .rotateGesturesEnabled(false)
            .scrollGesturesEnabled(true)
            .tiltGesturesEnabled(false)
            .zoomControlsEnabled(false)
            .zoomGesturesEnabled(true)

        val mapFragment = SupportMapFragment.newInstance(options)
        childFragmentManager
            .beginTransaction()
            .add(containerId, mapFragment)
            .commit()

        return mapFragment
    }

    fun Fragment.addMapFragmentToContainer(container: View) =
        addMapFragmentToContainer(container.id)

    fun GoogleMap.addScrollLockListener(onScrollLockChanged: (Boolean) -> Unit) {
        setOnCameraMoveStartedListener { onScrollLockChanged(true) }
        setOnCameraIdleListener { onScrollLockChanged(false) }
    }
}