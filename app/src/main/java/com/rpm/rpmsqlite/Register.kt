package com.rpm.rpmsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val manager=ManagerDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.BtnRegister.setOnClickListener {
            val nombre= binding.ETName.text.toString()
            val apellido= binding.ETLastName.text.toString()
            val email= binding.ETEmail.text.toString()
            val password= binding.ETPassword.text.toString()

           manager.insertUserData(nombre, apellido, email, password)
        }
    }
}