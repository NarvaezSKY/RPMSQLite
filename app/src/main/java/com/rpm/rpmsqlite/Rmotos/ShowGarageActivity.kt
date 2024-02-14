package com.rpm.rpmsqlite.Rmotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpm.rpmsqlite.R
import com.rpm.rpmsqlite.databinding.ActivityShowGarageBinding

class ShowGarageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShowGarageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowGarageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}