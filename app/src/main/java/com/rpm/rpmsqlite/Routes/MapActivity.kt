package com.rpm.rpmsqlite.Routes

import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rpm.rpmsqlite.R
import com.rpm.rpmsqlite.databinding.ActivityMapBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@Suppress("DEPRECATION")
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding:ActivityMapBinding
    private lateinit var map: GoogleMap
    private lateinit var btnCalculate: Button
    private var start: String = ""
    private var end: String = ""
    var poly: Polyline? = null

    //vaarivle para abuscador
    private lateinit var mapSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btnCalculate = binding.btnCalculateRoute


        mapSearchView=binding.mapSearch
        mapSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val location = query ?: return false
                var addressList: List<Address>? = null



                if (location.isNotBlank()) {
                    val geocoder = Geocoder(this@MapActivity)

                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        // Manejo de errores más detallado aquí (por ejemplo, mostrar un mensaje de error)
                        e.printStackTrace()
                    }

                    if (!addressList.isNullOrEmpty()) {
                        val address: Address = addressList[0]
                        val latLng = LatLng(address.latitude, address.longitude)

                        map.clear()
                        map.addMarker(MarkerOptions().position(latLng).title(location))
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13F))
                    }

                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Acción a realizar cuando cambia el texto de búsqueda
                return false
            }
        })








        //Puntos y tarazar Ruta

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

        //desplegable
        val bottomSheet = findViewById<FrameLayout>(R.id.desp)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight=650
            this.state=BottomSheetBehavior.STATE_COLLAPSED
        }
        val button = binding.icon
        button.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.btnCalculateRoute.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }






    }





    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val zoom = 13.0f
        // Add a marker in Popayán and move the camera
        val popayan = LatLng(2.44184386299662, -76.60638743215519)

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(popayan, zoom))
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
    private fun drawRoute(routeResponse: RouteResponse?) {



        routeResponse?.features?.firstOrNull()?.geometry?.coordinates?.let { coordinates ->
            val polyLineOptions = PolylineOptions().apply {
                width(9f)
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
                val newButton = Button(this).apply {
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

