package com.rpm.rpmsqlite.Model

import android.annotation.SuppressLint
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


    fun insertRoute (nombreRuta:String, cordenadasInicio:String,cordenadasFinal:String, detalleRuta:String):Long {

        openBdWr() // Abrir bd modo escritura

        //Creo contenedor de valores para insertar data
        val contenedor = ContentValues()
        contenedor.put("rutaN",nombreRuta)
        contenedor.put("cordenadasInicio",cordenadasInicio)
        contenedor.put("cordenadasFinal",cordenadasFinal)
        contenedor.put("detalle",detalleRuta)

        //Llamo el método insert
        val result = bd.insert("routes", null, contenedor )

        return result
    }

    fun insertUserData( nombre: String, apellido: String, email: String, password: String): Long {
        openBdWr()

        val userContenedor= ContentValues()


        userContenedor.put("nombre", nombre)
        userContenedor.put("apellido", apellido)
        userContenedor.put("email", email)
        userContenedor.put("password", password)

        return bd.insert("User", null, userContenedor)
    }

    fun getUserByEmail(email: String): User? {
        openBdRd()
        val query = "SELECT * FROM User WHERE email = ?"
        val cursor: Cursor? = bd.rawQuery(query, arrayOf(email))
        var user: User? = null
        if (cursor != null && cursor.moveToFirst()) {
            val idxemail = cursor.getColumnIndex("email")
            val idxpassword = cursor.getColumnIndex("password")
            val emailFromCursor: String = cursor.getString(idxemail)
            val passwordFromCursor: String = cursor.getString(idxpassword)
            user = User(emailFromCursor, passwordFromCursor)
        }
        cursor?.close()
        return user
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
                val cordenadasInicio = cursor.getColumnIndex("cordenadasInicio")
                val cordenadasFinal = cursor.getColumnIndex("cordenadasFinal")

                val detalle = cursor.getColumnIndex("detalle")

                //Obtener valores condicionando a valores no null
                val vlrNombreR:String = cursor.getString(nombreR)
                val vlrcodCordenadaInicio: String = cursor.getString(cordenadasInicio)
                val vlrcodCordenadaFinal: String = cursor.getString(cordenadasFinal)

                val vlrDetalle: String = cursor.getString(detalle)

                //Crear instancia de ciudad y agregar a la lista

                val route = Route(vlrNombreR, vlrcodCordenadaInicio,vlrcodCordenadaFinal, vlrDetalle) // paso los valores obtenidos del cursor a mi objeto ciudad

                routeList.add(route) // Agrego mi objeto ciudad al array list

            } while (cursor.moveToNext()) // El ciclo se hace hasta que el cursor se mueva al la siguiente posicion

            cursor.close()
        }


        return routeList
    }

    fun inserDataRmotos(marca:String, modelo:Int, cilindraje:Int, placa:String):Long{
        openBdWr()
        val content = ContentValues()
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