package com.rpm.rpmsqlite.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

data class ManagerDb(val context: Context) {
    lateinit var bd: SQLiteDatabase
    val bdHelper = BdHelper(context)


    fun openBdWr() {
        bd = bdHelper.writableDatabase


    }

    fun openBdRd() {
        bd = bdHelper.readableDatabase
    }


    fun insertRoute (nombreRuta:String, cordenadas:Double, detalleRuta:String):Long {

        openBdWr() // Abrir bd modo escritura

        //Creo contenedor de valores para insertar data
        val contenedor = ContentValues()
        contenedor.put("rutaN",nombreRuta)
        contenedor.put("cordenada",cordenadas)
        contenedor.put("detalle",detalleRuta)

        //Llamo el método insert
        val result = bd.insert("routes", null, contenedor )

        return result

    }

}