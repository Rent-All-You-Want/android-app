package com.pablojuice.core.presentation.view.maps

//import android.app.Activity
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.fragment.app.Fragment
//import org.osmdroid.config.Configuration
//import org.osmdroid.events.MapEventsReceiver
//import org.osmdroid.util.GeoPoint
//import org.osmdroid.views.MapView
//import org.osmdroid.views.overlay.ItemizedIconOverlay
//import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
//import org.osmdroid.views.overlay.MapEventsOverlay
//import org.osmdroid.views.overlay.OverlayItem
//import org.osmdroid.views.overlay.compass.CompassOverlay
//import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
//import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
//import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
//import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

//private const val MAP_CACHE_KEY = "osm_cache"
//
//fun Activity.getMapCache(): SharedPreferences =
//    getSharedPreferences(MAP_CACHE_KEY, Context.MODE_PRIVATE)
//
//fun Fragment.loadMapConfiguration() {
//    Configuration.getInstance().load(
//        requireContext(),
//        requireActivity().getMapCache()
//    )
//}
//
//fun MapView.enableUserLocation() {
//    val mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this)
//    mLocationOverlay.enableMyLocation()
//    overlays.add(mLocationOverlay)
//}
//
//fun MapView.enableCompassOverlay() {
//    val compassOverlay =
//        CompassOverlay(context, InternalCompassOrientationProvider(context), this)
//    compassOverlay.enableCompass()
//    overlays.add(compassOverlay)
//}
//
//fun MapView.enableRotationGestures() {
//    val rotationGestureOverlay = RotationGestureOverlay(this)
//    rotationGestureOverlay.isEnabled = true
//    setMultiTouchControls(true)
//    overlays.add(rotationGestureOverlay)
//}
//
//fun MapView.setMapEventListener(
//    onSingleTap: (GeoPoint) -> Unit = {},
//    onLongPress: (GeoPoint) -> Unit = {}
//) {
//    val tapOverlay = MapEventsOverlay(object : MapEventsReceiver {
//        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
//            p?.let(onSingleTap)
//            return p != null
//        }
//
//        override fun longPressHelper(p: GeoPoint?): Boolean {
//            p?.let(onLongPress)
//            return p != null
//        }
//    })
//    overlays.add(tapOverlay)
//}

//
//        val dm = requireContext().resources.displayMetrics
//        val scaleBarOverlay = ScaleBarOverlay(binding.mapView)
//        scaleBarOverlay.setCentred(true)
//        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10)
//        binding.mapView.overlays.add(scaleBarOverlay)
//
//        val minimapOverlay = MinimapOverlay(context,  binding.mapView.tileRequestCompleteHandler)
//        minimapOverlay.width = dm.widthPixels / 5
//        minimapOverlay.height = dm.heightPixels / 5
//
//        binding.mapView.overlays.add(minimapOverlay)