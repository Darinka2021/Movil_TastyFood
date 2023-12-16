package pe.com.supertasty

import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import pe.com.supertasty.Adapter.CategoriaComboAdapter
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
    private var registroproducto: List<ProductoEntity>? = null
    private var registrocategoria: List<CategoriaEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallepedido)

        registrocategoria = ArrayList()
        categoriaService = Api.categoriaService

        cargarComboCategoria()
    }

    private fun cargarComboCategoria() {
        val call = categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?> {
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if (response.isSuccessful) {
                    registrocategoria = response.body()
                    val spinnerCategoria = findViewById<Spinner>(R.id.cboCategoria)
                    spinnerCategoria.adapter =
                        CategoriaComboAdapter(applicationContext, registrocategoria)
                }
            }

            override fun onFailure(call: Call<List<CategoriaEntity>?>, t: Throwable) {
                Log.e("error: ", t.toString())
            }
        })
    }
}
