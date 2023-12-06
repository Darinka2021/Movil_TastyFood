package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class DetallePedidoEntity {
    @SerializedName("codigo")
    @Expose
    var codigo :Long =0

    @SerializedName("fecha_registro")
    @Expose
    var fecha_registro: Date = Date()

    @SerializedName("cantidad")
    @Expose
    var cantidad:Long=0

    @SerializedName("precio")
    @Expose
    var precio:Double=0.0

    @SerializedName("preciototal")
    @Expose
    var preciototal:Double=0.0

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false
}