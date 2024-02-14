package com.rpm.rpmsqlite.Rmotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rpm.rpmsqlite.Model.Garage
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.R
import com.rpm.rpmsqlite.databinding.ActivityShowGarageBinding

class ShowGarageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShowGarageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowGarageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = ManagerDb(this)

        val arrayGarage = manager.getDataRmotos()

        val listGarage = binding.listView
        val arratAdapter = ArrayAdapter<Garage>(this,android.R.layout.simple_list_item_1,arrayGarage)

        listGarage.adapter = arratAdapter

        Toast.makeText(this,"Nueva Moto Registrada",Toast.LENGTH_SHORT).show()
    }
}