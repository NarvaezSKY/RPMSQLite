package com.rpm.rpmsqlite.Model

import android.widget.ImageView

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb = 8
        lateinit var URI: ImageView
        const val ROUTES= "CREATE TABLE routes(rutaN string, cordenadasInicio string ,cordenadasFinal string, detalle string, imagen string)"

        const val GETROUTES = "Select * from routes"
        const val User= "CREATE TABLE User(id integer primary key autoincrement, nombre text , apellido text, email text unique, password text )"

        const val REGISTERMOTOS = "CREATE TABLE rmotos (marca text, modelo int, cilindraje int, placa text)"
        const val GETRMOTOS = "Select * from rmotos"
    }
}