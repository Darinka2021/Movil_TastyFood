package pe.com.supertasty

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.com.supertasty.Adapter.CategoriaComboAdapter
import pe.com.supertasty.Adapter.ProductoComboAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.Service.ProductoService
import pe.com.supertasty.databinding.ActivityDetallepedidoBinding
import pe.com.supertasty.databinding.ActivityLoginBinding
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePedidoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetallepedidoBinding
    //creramos un objeto de la entidad
    private var objcliente= ClienteEntity()
    //una variable para trabajar con el servicio
    private var clienteService: ClienteService?=null
    //lista de tipo ClienteEntity
    private var registrocliente:List<ClienteEntity>?=null
    //declaramos variables
    var cod=0L
    var nom=""


    private var productoService: ProductoService? = null
    private var categoriaService: CategoriaService? = null
    private var registroproductos: List<ProductoEntity>? = null
    private var registrocategoria: List<CategoriaEntity>? = null
    private var registroproductoxcategoria: ArrayList<ProductoEntity> = ArrayList()
    private var pdenCombo:ArrayList<ProductoEntity> = ArrayList()
    //variable
    private var cat = 1L
    private var pro = 1L
    //posicion en combocategoria
    private var pos=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallepedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = getApplicationContext()
        objcliente = ClienteEntity()
        registrocliente=ArrayList()
        registroproductoxcategoria= ArrayList()
        registroproductos = ArrayList()
        registrocategoria = ArrayList()
        categoriaService = Api.categoriaService
        productoService = Api.productoService
        clienteService= Api.clienteService



        val idcliente = intent.getStringExtra("idCliente").toString().toLong()
        Toast.makeText(this,"CLIENTE ACCEDIO: "+idcliente.toString(), Toast.LENGTH_LONG).show()
        MostrarCliente(this, idcliente)

        cargarComboCategoria(context)

        var ic = cargarComboProducto(context)
        registrosProducto(context,ic.toLong())



        binding.cboProducto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                pro = pdenCombo.get(position).codigo
                if(pro > 0){
                    var precio = pdenCombo.get(position).precio
                    binding.txtIdproducto.text = pro.toString()
                    binding.txtPrePed.text = precio.toString()
                }
                else{
                    binding.txtIdproducto.text = ""
                    binding.txtPrePed.text = ""
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>) {


            }
        }
    }

    private fun cargarComboCategoria(context:Context) {
        val call = categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?> {
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if (response.isSuccessful) {
                    registrocategoria = response.body()
                    var lista:ArrayList<CategoriaEntity> = ArrayList()
                    for(c:CategoriaEntity in registrocategoria!!){
                        if(c.estado == true){
                            lista.add(c)
                            binding.cboCategoria.adapter= CategoriaComboAdapter(context,lista)
                        }
                        else{

                        }
                    }
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
                    registroproductos=response.body()
                    pdenCombo=lista_productos_x_categoria(context,registroproductos as ArrayList<ProductoEntity>?,idcat)
                }
                else{
                }
            }
            override fun onFailure(call: Call<List<ProductoEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }
        })

    }


    fun lista_productos_x_categoria(context: Context,listado:ArrayList<ProductoEntity>?, idCategoria:Long): ArrayList<ProductoEntity> {
        var lista6 = ArrayList<ProductoEntity>()
        for(p:ProductoEntity in listado!!){
            if(p.categoria.codigo.toString().trim().toLong()== idCategoria.toString().trim().toLong()){
                if(p.estado == true){
                    lista6.add(p)
                    Log.e("PXC", "HAY PRODUCTO POR LA CATEGORIA", )
                    Log.e("PXC tamaño lista", lista6.size.toString(), )
                }
            }
        }
        binding.cboProducto.adapter = ProductoComboAdapter(context,lista6)
        return lista6
    }

    fun cargarComboProducto(context: Context):Long{
        binding.cboCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                cat = (registrocategoria as ArrayList<CategoriaEntity>).get(position).codigo
                binding.txtIdCategoria.text = cat.toString()
                registrosProducto(context,cat.toLong())
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                cat = 1
                registrosProducto(context,cat.toLong())
            }
        }
        return cat
    }
    fun MostrarCliente(context: Context,idCliente:Long){
        val call=clienteService!!.findAll()
        call!!.enqueue(object : Callback<List<ClienteEntity>?> {
            override fun onResponse(
                call: Call<List<ClienteEntity>?>,
                response: Response<List<ClienteEntity>?>
            ) {
                if(response.isSuccessful){
                    registrocliente=response.body()

                    for (c:ClienteEntity in registrocliente as ArrayList<ClienteEntity>){
                        if (c.codigo == idCliente){
                            objcliente = c
                            Log.e("recuperacion", "onResponse: recuperamos al cliente con el idcliente: "+idCliente, )

                            binding.txtClienteNombre.text=objcliente.correo
                            binding.txtClienteId.text=objcliente.codigo.toString()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ClienteEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }
}
