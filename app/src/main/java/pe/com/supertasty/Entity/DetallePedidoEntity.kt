package pe.com.supertasty.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class DetallePedidoEntity {
    @SerializedName("codigo")
    @Expose
    var codigo: Long = 0

    @SerializedName("cantidad")
    @Expose
    var cantidad: Int = 0

    @SerializedName("precio")
    @Expose
    var precio: Double = 0.0

    @SerializedName("stotal")
    @Expose
    var stotal:Double=0.0

    @SerializedName("producto")
    @Expose
    var productos:ProductoEntity = ProductoEntity()

    @SerializedName("pedido")
    @Expose
    var pedido:PedidoEntity = PedidoEntity()

    constructor()
    constructor(
        codigo: Long,
        cantidad: Int,
        precio: Double,
        stotal: Double,
        productos: ProductoEntity,
        pedido: PedidoEntity
    ) {
        this.codigo = codigo
        this.cantidad = cantidad
        this.precio = precio
        this.stotal = stotal
        this.productos = productos
        this.pedido = pedido
    }


}