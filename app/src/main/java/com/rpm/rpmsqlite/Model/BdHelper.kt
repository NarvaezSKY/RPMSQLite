package com.rpm.rpmsqlite.Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BdHelper(context:Context):SQLiteOpenHelper(
    context, Constains.nomDb,null, Constains.versionDb
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constains.User)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User")



        onCreate(db)
    }

}