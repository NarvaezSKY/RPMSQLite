package com.rpm.rpmsqlite.Routes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.rpm.rpmsqlite.R
import com.rpm.rpmsqlite.databinding.ActivityMapsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    //Para el mapa
    private lateinit var map: GoogleMap
    private lateinit var btnCalculate: Button
    private var start: String = ""
    private var end: String = ""
    var poly: Polyline? = null
    //.



    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)




        btnCalculate = binding.btnCalculateRoute
        btnCalculate.setOnClickListener {
            start = ""
            end = ""
            poly?.remove()
            poly= null
            Toast.makeText(this,"Selecciona punto de origen y final", Toast.LENGTH_SHORT).show()
            map?.clear()
            binding.buttonContainer.removeAllViews()

            if (::map.isInitialized) {
                map.setOnMapClickListener {
                    if (start.isEmpty()) {
                        start = "${it.longitude},${it.latitude}"

                        Toast.makeText(this, "Primer Punto Seleccionado", Toast.LENGTH_SHORT).show()

                    } else if (end.isEmpty()) {
                        end = "${it.longitude},${it.latitude}"
                        Toast.makeText(this, "Segundo Punto Seleccionado", Toast.LENGTH_SHORT).show()
                        createRoute()
                    }
                }
            }
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }


















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
        map = googleMap
        val zoom =13.0f
        // Add a marker in Sydney and move the camera
        val popayan = LatLng(2.44184386299662, -76.60638743215519)

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(popayan,zoom))
    }

    private fun createRoute() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getRoute("5b3ce3597851110001cf6248c50b947f1222418498fae123bb1a6114", start, end)
            if (call.isSuccessful) {
                drawRoute(call.body())
            } else {
                Log.i("aris", "OK")
            }
        }
    }
//    private fun drawRoute(routeResponse: RouteResponse?) {
//        val polyLineOptions = PolylineOptions()
//        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {
//            polyLineOptions.add(LatLng(it[1], it[0]))
//        }
//        runOnUiThread {
//            poly =  map.addPolyline(polyLineOptions)
//        }
//    }

    private fun drawRoute(routeResponse: RouteResponse?) {



        routeResponse?.features?.firstOrNull()?.geometry?.coordinates?.let { coordinates ->
            val polyLineOptions = PolylineOptions().apply {
                width(11f)
                color(Color.parseColor("#ffc800"))
                coordinates.forEach { coordinate ->
                    add(LatLng(coordinate[1], coordinate[0]))
                }
            }

            runOnUiThread {
                map?.addPolyline(polyLineOptions)
            }

            // Agregar marcador al inicio
            val startPoint = LatLng(coordinates.first()[1], coordinates.first()[0])
            val startMarkerOptions = MarkerOptions().position(startPoint).title("Inicio")

            runOnUiThread {
                map?.addMarker(startMarkerOptions)
            }

            // Agregar marcador al final
            val endPoint = LatLng(coordinates.last()[1], coordinates.last()[0])
            val endMarkerOptions = MarkerOptions().position(endPoint).title("Fin")

            runOnUiThread {
                map?.addMarker(endMarkerOptions)


                //un nuevo boton para agregar la ruta
                val newButton = Button(this@MapsActivity).apply {
                    text = "Guardar Ruta"
                    setBackgroundColor(Color.parseColor("#ffc800"))


                    setTextColor(Color.WHITE)
                    setOnClickListener {
                        val intent= Intent(this.context, saveRutasActivity::class.java)
                        //pasamos las cordenadas
                        intent.putExtra("cordenadasInicio", start.toString())
                        intent.putExtra("cordenadasFinal", end.toString())



                        startActivity(intent)
                    }
                }
                binding.buttonContainer.addView(newButton)

            }
        }
    }







    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}