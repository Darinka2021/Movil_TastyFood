package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClienteEntity {

    @SerializedName("codigo")
    @Expose
    var codigo :Long =0

    @SerializedName("nombre")
    @Expose
    var nombre:String=""

    @SerializedName("correo")
    @Expose
    var correo:String=""

    @SerializedName("nomusuario")
    @Expose
    var nomusuario:String=""

    @SerializedName("pasword")
    @Expose
    var pasword:String=""

    @SerializedName("estado")
    @Expose
    var estado:Boolean=false

    constructor()
    constructor(
        codigo: Long,
        nombre: String,
        correo: String,
        nomusuario: String,
        pasword: String,
        estado: Boolean
    ) {
        this.codigo = codigo
        this.nombre = nombre
        this.correo = correo
        this.nomusuario = nomusuario
        this.pasword = pasword
        this.estado = estado
    }

}