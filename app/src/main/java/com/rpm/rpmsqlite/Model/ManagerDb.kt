package com.rpm.rpmsqlite.Model

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

    fun insertUserData(){
        openBdWr()
    }

}