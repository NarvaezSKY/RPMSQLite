package com.rpm.rpmsqlite.Model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun insertUserData( nombre: String, apellido: String, email: String, password: String): Long {
        openBdWr()

        val userContenedor= ContentValues()


        userContenedor.put("nombre", nombre)
        userContenedor.put("apellido", apellido)
        userContenedor.put("email", email)
        userContenedor.put("password", password)

        return bd.insert("User", null, userContenedor)
    }

    fun getUserByEmail(email: String): User? {
        openBdRd()
        val query = "SELECT * FROM User WHERE email = ?"
        val cursor: Cursor? = bd.rawQuery(query, arrayOf(email))
        var user: User? = null
        if (cursor != null && cursor.moveToFirst()) {
            val idxemail = cursor.getColumnIndex("email")
            val idxpassword = cursor.getColumnIndex("password")
            val emailFromCursor: String = cursor.getString(idxemail)
            val passwordFromCursor: String = cursor.getString(idxpassword)
            user = User(emailFromCursor, passwordFromCursor)
        }
        cursor?.close()
        return user
    }

}