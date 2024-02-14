package com.rpm.rpmsqlite.Model

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb=1
        const val USERS= "CREATE TABLE users(id int, name text , lastname text)"
        const val REGISTERMOTOS = "CREATE TABLE rmotos (marca text, modelo int, cilindraje int, placa text)"
        const val GETRMOTOS = "Select * from rmotos"
    }
}