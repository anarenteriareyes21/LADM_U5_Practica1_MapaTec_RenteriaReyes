package mx.edu.ittepic.ladm_u5_practica1_mapatec_renteriareyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity () : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var  ubiclat = 0.0
    var ubiclon = 0.0
    var nombreLugar = ""

    //var valores = Array<>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        var extras = intent.extras
        ubiclat = extras?.getDouble("ubi-latitud")!!.toDouble()
        ubiclon = extras?.getDouble("ubi-longitud")!!.toDouble()
        nombreLugar = extras?.getString("namePlace").toString()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val posicion = LatLng(ubiclat, ubiclon)
        mMap.addMarker(MarkerOptions().position(posicion).title("Aquí está: ${nombreLugar}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicion,20f))
        //controles del zoom
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.isMyLocationEnabled = true
    }
}
