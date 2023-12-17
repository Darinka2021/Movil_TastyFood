package pe.com.supertasty.Service
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.PedidoEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PedidoService {
    @POST("/pedido")
    fun addPedido(@Body pedido: PedidoEntity): Call<PedidoEntity>
    @POST("pedido")
    fun add(@Body c: PedidoEntity):Call<PedidoEntity?>?
}
