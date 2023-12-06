package pe.com.supertasty.remote



import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.Service.ProductoService

object Api {
    //en localhost colocar la ip
    //lo mismo en el xml network_security_config
    val API_URL="http://localhost:8099/supertasty/"
    val categoriaService:CategoriaService? get()=RetrofitClient.getConnection(API_URL)?.create(CategoriaService::class.java)
    val productoService:ProductoService? get()=RetrofitClient.getConnection(API_URL)?.create(ProductoService::class.java)
    val clienteService:ClienteService? get()=RetrofitClient.getConnection(API_URL)?.create(ClienteService::class.java)
}