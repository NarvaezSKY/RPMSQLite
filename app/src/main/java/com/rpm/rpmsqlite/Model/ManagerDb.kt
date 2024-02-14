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


    fun inserData() {
        openBdWr()


    }

    fun insertUserData(id: Int, nombre: String, apellido: String, email: String, contrasena: String): Long {
        openBdWr()

        val userContenedor= ContentValues()

        userContenedor.put("id", id)
        userContenedor.put("nombre", nombre)
        userContenedor.put("apellido", apellido)
        userContenedor.put("email", email)
        userContenedor.put("contraseña", contrasena)

        return bd.insert("User", null, userContenedor)
    }

}