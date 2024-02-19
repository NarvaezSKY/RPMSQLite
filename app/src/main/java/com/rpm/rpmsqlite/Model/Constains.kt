package com.rpm.rpmsqlite.Model

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb = 4
        const val USERS= "CREATE TABLE users(id int, name text , lastname text)"
        const val ROUTES= "CREATE TABLE routes(rutaN string, cordenada int , detalle string)"

        const val GETROUTES = "Select * from routes"

    }
}