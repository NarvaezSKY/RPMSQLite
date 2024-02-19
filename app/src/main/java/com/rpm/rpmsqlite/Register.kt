package com.rpm.rpmsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val manager=ManagerDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)



        managerIU()
    }
    fun managerIU () {
        listenerevents()
    }

    fun listenerevents() {
        binding.BtnBack.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        binding.BtnRegister.setOnClickListener {
            val nombre= binding.ETName.text.toString()
            val apellido= binding.ETLastName.text.toString()
            val email= binding.ETEmail.text.toString()
            val password= binding.ETPassword.text.toString()

            manager.insertUserData(nombre, apellido, email, password)
            Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
        }
    }
}