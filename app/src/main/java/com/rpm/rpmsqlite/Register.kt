package com.rpm.rpmsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.Model.User
import android.widget.TextView

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

    private fun managerIU() {
        listenerevents()
    }

    private fun listenerevents() {
        binding.BtnBack.setOnClickListener {

        }

        binding.BtnRegister.setOnClickListener {
            val nombre = binding.ETname.text.toString()
            val apellido = binding.ETLastname.text.toString()
            val email = binding.ETemail.text.toString()
            val password = binding.ETPassword.text.toString()



            if (nombre.isNotEmpty() && apellido.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                if (isValidEmail(email) && isValidPassword(password)) {
                    manager.insertUserData(nombre, apellido, email, password)
                    Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(applicationContext, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        if (email.matches(emailRegex.toRegex())) {
            return true
        } else {
            Toast.makeText(applicationContext, "Por favor, ingresa un formato de correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length >= 6) {
            return true
        } else {
            Toast.makeText(applicationContext, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}