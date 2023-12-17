package pe.com.supertasty.Service

import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Entity.DetallePedidoEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DetallePedidoService {

    @GET("detallepedido")
    fun findAll(): Call<List<DetallePedidoEntity>?>?
    @GET("detallepedido/idcliente") //no se encuentra en el servicio
    fun findAllCustom(): Call<List<DetallePedidoEntity>?>?

    @POST("detallepedido")
    fun add(@Body c:DetallePedidoEntity):Call<DetallePedidoEntity?>?

}