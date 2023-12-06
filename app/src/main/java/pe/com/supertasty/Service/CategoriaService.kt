package pe.com.supertasty.Service


import pe.com.supertasty.Entity.CategoriaEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoriaService {
    @GET("categoria")
    fun findAll():Call<List<CategoriaEntity>?>?
    @GET("categoria/custom")
    fun findAllCustom():Call<List<CategoriaEntity>?>?
    @POST("categoria")
    fun add(@Body c:CategoriaEntity):Call<CategoriaEntity?>?
    @PUT("categoria/{id}")
    fun update(@Path("id") id: kotlin.Long, @Body c:CategoriaEntity):Call<CategoriaEntity?>?
    @DELETE("categoria/{id}")
    fun delete(@Path("id") id: Long):Call<CategoriaEntity?>?
}