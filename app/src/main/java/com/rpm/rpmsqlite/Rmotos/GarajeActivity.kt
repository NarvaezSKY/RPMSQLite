package com.rpm.rpmsqlite.Rmotos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.databinding.ActivityGarajeBinding

class GarajeActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityGarajeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGarajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {

            val marca = binding.editText1.text.toString()
            val modelo = binding.editText2.text.toString()
            val cilindraje = binding.editText3.text.toString()
            val placa = binding.editText4.text.toString()





            if(marca.isEmpty() || modelo.isEmpty() || cilindraje.isEmpty() || placa.isEmpty()){
                Toast.makeText(this,"El campo no puede estar vacio",Toast.LENGTH_SHORT).show()
            }else{
                val manager = ManagerDb(this)
                manager.inserDataRmotos(marca,modelo.toInt(),cilindraje.toInt(),placa)
                Toast.makeText(this,"Moto Registrada", Toast.LENGTH_SHORT).show()
            }
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this,ShowGarageActivity::class.java)
            startActivity(intent)
        }



    }
}