package com.rpm.rpmsqlite.Routes

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.Model.Route
import com.rpm.rpmsqlite.databinding.ActivityListarRutasBinding

class ListarRutasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarRutasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListarRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = ManagerDb(this)
        val arrayRoute = manager.getData()

        val listRoute = binding.listaRutas
        val arrayAdapter = ArrayAdapter<Route>(this,android.R.layout.simple_list_item_1,arrayRoute)

        listRoute.adapter = arrayAdapter
        Toast.makeText(this, "Rutas listadas", Toast.LENGTH_SHORT).show()


    }
}