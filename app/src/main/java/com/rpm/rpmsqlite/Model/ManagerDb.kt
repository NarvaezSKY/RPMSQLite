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


    fun insertRoute (nombreRuta:String, cordenadas:Int, detalleRuta:String):Long {

        openBdWr() // Abrir bd modo escritura

        //Creo contenedor de valores para insertar data
        val contenedor = ContentValues()
        contenedor.put("rutaN",nombreRuta)
        contenedor.put("cordenada",cordenadas)
        contenedor.put("detalle",detalleRuta)

        //Llamo el método insert
        val result = bd.insert("routes", null, contenedor )

        return result

    }

    fun getData ():ArrayList<Route>{
        openBdRd()

        val routeList = ArrayList<Route>()

        val cursor: Cursor? = bd.rawQuery(Constains.GETROUTES,null)

        if (cursor != null && cursor.moveToFirst()) {

            // Se verifica que si hay datos en la primera posición.

            do {
                //Almacenar en variable lo que contiene el cursor en la columna cod y nombre
                val nombreR = cursor.getColumnIndex("rutaN") // se almacena en las variables lo que tiene en el cursor en la fila cero
                val codCordenada = cursor.getColumnIndex("cordenada")
                val detalle = cursor.getColumnIndex("detalle")

                //Obtener valores condicionando a valores no null
                val vlrNombreR:String = cursor.getString(nombreR) ?: " "
                val vlrcodCordenada: Int = cursor.getInt(codCordenada)
                val vlrDetalle: String = cursor.getString(detalle) ?:" "

                //Crear instancia de ciudad y agregar a la lista

                val route = Route(vlrNombreR, vlrcodCordenada, vlrDetalle) // paso los valores obtenidos del cursor a mi objeto ciudad

                routeList.add(route) // Agrego mi objeto ciudad al array list

            } while (cursor.moveToNext()) // El ciclo se hace hasta que el cursor se mueva al la siguiente posicion

            cursor.close()
        }


        return routeList
    }

}