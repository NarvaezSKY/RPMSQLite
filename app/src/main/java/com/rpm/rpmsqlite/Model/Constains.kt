package com.rpm.rpmsqlite.Model

class Constains {

    companion object{
        const val nomDb="rpmdb"
        const val versionDb=2
        const val User= "CREATE TABLE User(id integer primary key autoincrement, nombre text , apellido text, email text unique, password text )"

    }
}