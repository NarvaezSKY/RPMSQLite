package com.rpm.rpmsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.rpm.rpmsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)



        home()

    }















    private fun home() {
        TODO("Not yet implemented")
    }
}