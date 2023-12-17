package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Date

class PedidoEntity {
    @SerializedName("codigo")
    @Expose
    var codigo: Long = 0

    @SerializedName("estado")
    @Expose
    var estado: Boolean = false

    @SerializedName("fecha_registro")
    @Expose
    var fechaRegistro: Date? = null

    @SerializedName("total")
    @Expose
    var total: Double = 0.0

    @SerializedName("codcliente")
    @Expose
    var codCliente: Long = 0

    constructor()

    constructor(codigo: Long, estado: Boolean, fechaRegistro: Date?, total: Double, codCliente: Long) {
        this.codigo = codigo
        this.estado = estado
        this.fechaRegistro = fechaRegistro
        this.total = total
        this.codCliente = codCliente
    }
}
