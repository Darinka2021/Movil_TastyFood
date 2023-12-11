package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoriaEntity {

    @SerializedName("codigo")
    @Expose
    var codigo :Long =0
    @SerializedName("nombre")
    @Expose
    var nombre:String=""
    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor()
    constructor(codigo: Long, nombre: String, estado: Boolean) {
        this.codigo = codigo
        this.nombre = nombre
        this.estado = estado
    }

    fun nombreCategoria():String{
        return this.nombre
    }

}