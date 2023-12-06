package pe.com.supertasty.Service

import pe.com.supertasty.Entity.ClienteEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClienteService {
    @GET("cliente")
    fun findAll(): Call<List<ClienteEntity>?>?
    @GET("cliente/custom")
    fun findAllCustom(): Call<List<ClienteEntity>?>?
    @POST("cliente")
    fun add(@Body c: ClienteEntity): Call<ClienteEntity?>?
    @PUT("cliente/{id}")
    fun update(@Path("id") id:Long, @Body c: ClienteEntity): Call<ClienteEntity?>?
    @DELETE("cliente/{id}")
    fun delete(@Path("id") id:Long): Call<ClienteEntity?>?
}