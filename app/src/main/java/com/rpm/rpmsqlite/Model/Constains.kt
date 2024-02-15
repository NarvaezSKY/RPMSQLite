package com.rpm.rpmsqlite.Model

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb= 2
        const val USERS= "CREATE TABLE users(id int, name text , lastname text)"
        const val ROUTES= "CREATE TABLE routes(rutaN string, cordenada double , detalle string)"

    }
}