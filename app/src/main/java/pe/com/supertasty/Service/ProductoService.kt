package pe.com.supertasty.Service


import pe.com.supertasty.Entity.ProductoEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductoService {
    @GET("producto")
    fun findAll(): Call<List<ProductoEntity>?>?
    @GET("producto/custom")
    fun findAllCustom(): Call<List<ProductoEntity>?>?
    @POST("producto")
    fun add(@Body c: ProductoEntity): Call<ProductoEntity?>?
    @PUT("producto/{id}")
    fun update(@Path("id") id:Long, @Body c: ProductoEntity): Call<ProductoEntity?>?
    @DELETE("producto/{id}")
    fun delete(@Path("id") id:Long): Call<ProductoEntity?>?
}