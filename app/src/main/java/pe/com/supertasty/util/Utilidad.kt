package pe.com.apptienditarest.util

import android.app.AlertDialog
import android.content.Context

class Utilidad {
    //creamos una variable para la alerta
    private var dialogo:AlertDialog.Builder?=null

    //creamos una funcion que muestre la alerta
    fun MostrarAlerta(context: Context, t:String,m:String,btnc:Boolean){
        dialogo=AlertDialog.Builder(context)
        dialogo!!.setTitle(t)
        dialogo!!.setMessage(m)
        dialogo!!.setCancelable(btnc)
        dialogo!!.setPositiveButton("Ok"){
                dialogo,which->
        }
        dialogo!!.show()
    }
}
