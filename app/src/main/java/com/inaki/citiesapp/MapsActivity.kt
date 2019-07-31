package com.inaki.citiesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var lon: String
    private lateinit var lat: String
    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lon = intent.getStringExtra("lon")
        lat = intent.getStringExtra("lat")
        city = intent.getStringExtra("city")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val coord = LatLng(lat.toDouble(), lon.toDouble())
        mMap.addMarker(MarkerOptions().position(coord).title("Marker in $city"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord))
    }
}
