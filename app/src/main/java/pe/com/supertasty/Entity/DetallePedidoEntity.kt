package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetallePedidoEntity {@SerializedName("codigo")
@Expose
var codigo: Long = 0

    @SerializedName("precio")
    @Expose
    var precio: Double = 0.0

    @SerializedName("cantidad")
    @Expose
    var cantidad: Long = 0

    @SerializedName("categoria")
    @Expose
    var categoria: CategoriaEntity = CategoriaEntity()

    @SerializedName("producto")
    @Expose
    var producto: ProductoEntity = ProductoEntity()

    constructor()

    constructor(
        codigo: Long,
        precio: Double,
        cantidad: Long,
        categoria: CategoriaEntity,
        producto: ProductoEntity
    ) {
        this.codigo = codigo
        this.precio = precio
        this.cantidad = cantidad
        this.categoria = categoria
        this.producto = producto
    }
}
