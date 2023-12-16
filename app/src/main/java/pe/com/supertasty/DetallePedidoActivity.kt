package pe.com.supertasty

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pe.com.supertasty.Adapter.CategoriaComboAdapter
import pe.com.supertasty.Adapter.ProductoComboAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ProductoService
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePedidoActivity : AppCompatActivity() {

    private var productoService: ProductoService? = null
    private var categoriaService: CategoriaService? = null
    private var listado_productos: List<ProductoEntity>? = null
    private var registrocategoria: List<CategoriaEntity>? = null
    private var registropXc: ArrayList<ProductoEntity> = ArrayList()
    //variable
    private var cat = 1L
    //posicion en combocategoria
    private var pos=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallepedido)

        registropXc= ArrayList()
        listado_productos = ArrayList()
        registrocategoria = ArrayList()
        categoriaService = Api.categoriaService
        productoService = Api.productoService

        cargarComboCategoria(this)


    }

    private fun cargarComboCategoria(contexto:Context) {
        val call = categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?> {
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if (response.isSuccessful) {
                    registrocategoria = response.body()
                    Log.e("RESPONSE", "onResponse: SI HAY DATA", )

                    val spinnerCategoria = findViewById<Spinner>(R.id.cboCategoria)
                    spinnerCategoria.adapter =
                        CategoriaComboAdapter(contexto, registrocategoria)
                }
            }
            override fun onFailure(call: Call<List<CategoriaEntity>?>, t: Throwable) {
                Log.e("error: ", t.toString())
            }
        })
    }


    fun registrosProducto(context: Context,idcat:Long){
        val call=productoService!!.findAll()
        call!!.enqueue(object : Callback<List<ProductoEntity>?> {
            override fun onResponse(
                call: Call<List<ProductoEntity>?>,
                response: Response<List<ProductoEntity>?>
            ) {
                if(response.isSuccessful){
                    listado_productos=response.body()
                    lista_productos_x_categoria(listado_productos as ArrayList<ProductoEntity>?,idcat)
                }
            }
            override fun onFailure(call: Call<List<ProductoEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }
        })
    }


    fun lista_productos_x_categoria(listado:ArrayList<ProductoEntity>?, idCategoria:Long): ArrayList<ProductoEntity> {
        for(p:ProductoEntity in listado!!){
            if(p.categoria.codigo.toString().trim().toLong()== idCategoria.toString().trim().toLong()){
                registropXc.add(p)
                Log.e("PXC", "HAY PRODUCTO POR LA CATEGORIA", )
            }
        }
        val spinnerProducto = findViewById<Spinner>(R.id.cboProducto)
        spinnerProducto.adapter = ProductoComboAdapter(this,registropXc)
        return registropXc
    }

}
