package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductoEntity {

    @SerializedName("codigo")
    @Expose
    var codigo :Long =0

    @SerializedName("nombre")
    @Expose
    var nombre:String=""

    @SerializedName("precio")
    @Expose
    var precio:Double=0.0

    @SerializedName("cantidad")
    @Expose
    var cantidad:Long=0

    @SerializedName("categoria")
    @Expose
    var categoria:Long=0


    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor()
    constructor(codigo: Long, nombre: String, precio: Double, cantidad: Long, estado: Boolean) {
        this.codigo = codigo
        this.nombre = nombre
        this.precio = precio
        this.cantidad = cantidad
        this.estado = estado
    }
}