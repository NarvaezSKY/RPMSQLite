package com.rpm.rpmsqlite.Model

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb = 6
        //const val USERS= "CREATE TABLE users(id int, name text , lastname text)"
        const val ROUTES= "CREATE TABLE routes(rutaN string, cordenada int , detalle string)"

        const val GETROUTES = "Select * from routes"
        const val User= "CREATE TABLE User(id integer primary key autoincrement, nombre text , apellido text, email text unique, password text )"

        //const val USERS= "CREATE TABLE users(id int, name text , lastname text)"
        const val REGISTERMOTOS = "CREATE TABLE rmotos (marca text, modelo int, cilindraje int, placa text)"
        const val GETRMOTOS = "Select * from rmotos"
    }
}