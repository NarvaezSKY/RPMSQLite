package com.rpm.rpmsqlite.Rmotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpm.rpmsqlite.Model.ManagerDb
import com.rpm.rpmsqlite.R
import com.rpm.rpmsqlite.databinding.ActivityGarajeBinding

class GarajeActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityGarajeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGarajeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val manager = ManagerDb(this)



    }
}