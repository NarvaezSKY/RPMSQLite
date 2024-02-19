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

        binding.btnGuardarRoute.setOnClickListener { guardarRuta() }
    }

    fun guardarRuta() {

        var nombreRuta = binding.nombreRuta.text.toString()
        var cordenadas = binding.cordenadasRuta.text.toString()
        var detalleRuta = binding.detallesRuta.text.toString()

        //Instanciamos la clase bd Helper
        val manager = ManagerDb(this)

        manager.insertRoute(nombreRuta, cordenadas.toInt(), detalleRuta)


        Toast.makeText(
            this,
            "La ruta se guardó correctamente", Toast.LENGTH_SHORT).show()

    }
}


