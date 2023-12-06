package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PedidoEntity {
    @SerializedName("codigo")
    @Expose
    var codigo :Long =0
    @SerializedName("precioBebida")
    @Expose
    var precioBebida:Double=0.0
    @SerializedName("cantidadBebida")
    @Expose
    var cantidadBebida:Double=0.0
    @SerializedName("precioPlato")
    @Expose
    var precioPlato:Double=0.0
    @SerializedName("cantidadPlato")
    @Expose
    var cantidadPlato:Double=0.0

    @SerializedName("cliemte")
    @Expose
    var cliemte:Long =0

    @SerializedName("empleado")
    @Expose
    var empleado:Long =0

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor()
    constructor(
        codigo: Long,
        precioBebida: Double,
        cantidadBebida: Double,
        precioPlato: Double,
        cantidadPlato: Double,
        cliemte: Long,
        empleado: Long,
        estado: Boolean
    ) {
        this.codigo = codigo
        this.precioBebida = precioBebida
        this.cantidadBebida = cantidadBebida
        this.precioPlato = precioPlato
        this.cantidadPlato = cantidadPlato
        this.cliemte = cliemte
        this.empleado = empleado
        this.estado = estado
    }

}