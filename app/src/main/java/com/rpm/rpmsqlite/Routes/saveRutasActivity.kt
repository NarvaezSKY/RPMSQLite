package com.rpm.rpmsqlite.Routes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.databinding.ActivitySaveRutasBinding

class saveRutasActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveRutasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cordenadasInicio = intent.extras?.getString("cordenadasInicio").orEmpty()
        val cordenadasFinal = intent.extras?.getString("cordenadasFinal").orEmpty()



        binding.cordenadasRutaInicio.text=cordenadasInicio
        binding.cordenadasRutaFinal.text=cordenadasFinal

        binding.btnGuardarRoute.setOnClickListener {
            guardarRuta()
        }
    }

    fun guardarRuta() {
        val nombreRuta = binding.nombreRuta.text.toString()
        val detalleRuta = binding.detallesRuta.text.toString()

        val cordenadasInicio = intent.extras?.getString("cordenadasInicio").orEmpty()
        val cordenadasFinal = intent.extras?.getString("cordenadasFinal").orEmpty()





        val manager = ManagerDb(this)
        manager.insertRoute(nombreRuta, cordenadasInicio, cordenadasFinal, detalleRuta)

        Toast.makeText(this, "Ruta guardada: $nombreRuta", Toast.LENGTH_SHORT).show()
    }
}
