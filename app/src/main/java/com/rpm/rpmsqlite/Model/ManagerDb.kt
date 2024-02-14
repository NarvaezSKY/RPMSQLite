package com.rpm.rpmsqlite.Model

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

    fun inserDataRmotos(id:Int, marca:String, modelo:Int, cilindraje:Int, placa:String):Long{
        openBdWr()
        val content = ContentValues()
        content.put("id",id)
        content.put("marca",marca)
        content.put("modelo",modelo)
        content.put("cilindraje",cilindraje)
        content.put("placa",placa)

        val resul = bd.insert("rmotos",null,content)

        return  resul
    }

    fun getDataRmotos():ArrayList<Garage>{
        openBdRd()
        val garageList = ArrayList<Garage>()

        val cursor:Cursor? = bd.rawQuery(Constains.GETRMOTOS,null)

        if (cursor != null && cursor.moveToFirst()){
            do {
                val brandGarajeIdx = cursor.getColumnIndex("marca")
                val modelGarajeIdx = cursor.getColumnIndex("modelo")
                val cylindercapacityGarajeIdx = cursor.getColumnIndex("cilindraje")
                val plateGarajeIdx = cursor.getColumnIndex("placa")

                val brandGaraje:String = cursor.getString(brandGarajeIdx)?:""
                val modelGaraje:Int = cursor.getInt(modelGarajeIdx)
                val cylindercapacityGaraje:Int = cursor.getInt(cylindercapacityGarajeIdx)
                val plateGaraje:String = cursor.getString(plateGarajeIdx)?:""

                val garage = Garage(brandGaraje,modelGaraje,cylindercapacityGaraje,plateGaraje)
                garageList.add(garage)

            }while (cursor.moveToNext())
            cursor?.close()
        }

        return garageList
    }

}