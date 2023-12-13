package pe.com.supertasty

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


import pe.com.apptienditarest.util.Utilidad
import pe.com.supertasty.Adapter.CategoriaAdapter
import pe.com.supertasty.Adapter.CategoriaComboAdapter


import pe.com.supertasty.Adapter.ProductoAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ProductoService

import pe.com.supertasty.databinding.FragmentProductoBinding
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductoFragment : Fragment() {

    //creramos un objeto de la entidad
    private val objproducto= ProductoEntity()
    private val objcategoria = CategoriaEntity()

    //una variable para trabajar con el servicio
    private var productoService: ProductoService?=null
    private var categoriaService:CategoriaService? = null
    //lista de tipo CategoriaEntity
    private var registroproducto:List<ProductoEntity>?=null
    private var registrocategoria:List<CategoriaEntity>?=null
    //creamos un objeto de la clase Utilidad
    private val objutilidad= Utilidad()
    //declaramos variables
    private var cod=0L
    private var cant=0
    private var nom=""
    private var pre=0.0
    private var cat = 0L
    private var est=false
    private var fila= -1
    private var indice = -1
    //posicion en combocategoria
    private var pos=-1
    //creamos una varaiable para actualizar el fragmento
    var ft: FragmentTransaction?=null


    private var _binding: FragmentProductoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductoBinding.inflate(inflater, container, false)

        val context=requireContext()
        //inicializamos registroproducto
        registroproducto=ArrayList()
        registrocategoria=ArrayList()
        //implementamos el servicio
        productoService= Api.productoService
        categoriaService = Api.categoriaService
        //mostramos la categoria

        cargarComboCategoria(context)
        MostrarProducto(context)


        //evento boton registrar
        binding.btnRegistrarPro.setOnClickListener {
            if(binding.txtNomPro.text.toString().equals("")){
                objutilidad.MostrarAlerta(context,"Registro Producto","Ingrese el nombre",false)
                binding.txtNomPro.requestFocus()
            }
            else if(binding.cboCategoria.selectedItemPosition == -1){
            objutilidad.MostrarAlerta(context,"Registro Producto","Seleccione la categoria",false)
            binding.txtNomPro.requestFocus()
            }
            else{
                //capturando valores
                pos= binding.cboCategoria.selectedItemPosition
                cat= (registrocategoria as ArrayList<CategoriaEntity>).get(pos).codigo
                nom=binding.txtNomPro.text.toString()
                pre = binding.txtPrePro.text.toString().toDouble()
                cant = binding.txtCantPro.text.toString().toInt()
                est=if(binding.chkEstPro.isChecked){
                    true
                }else{
                    false
                }
                //enviamos los objetos a la clase
                objproducto.categoria.codigo = cat
                objproducto.nombre=nom
                objproducto.precio=pre
                objproducto.cantidad= cant.toLong()
                objproducto.estado=est
                //registramos la categoria
                RegistrarProducto(context,objproducto)
                objutilidad.MostrarAlerta(context,"Registro Producto","Se registro El Producto"
                    ,false)

                val fproducto = ProductoFragment()
                ActualizarFragmento(fproducto)
            }
        }

        binding.lstPro.setOnItemClickListener { adapterView, view, i, l ->
            fila = i
            binding.txtNomPro.setText("" + (registroproducto as ArrayList<ProductoEntity>).get(fila).codigo)
            binding.txtCantPro.setText("" + (registroproducto as ArrayList<ProductoEntity>).get(fila).cantidad)
            binding.txtPrePro.setText("" + (registroproducto as ArrayList<ProductoEntity>).get(fila).precio)
            binding.cboCategoria.setSelection(i)
            if ((registroproducto as ArrayList<ProductoEntity>).get(i).estado) {
                binding.chkEstPro.isChecked = true
            } else {
                binding.chkEstPro.isChecked = false
            }
        }
        return binding.root
    }


    fun cargarComboCategoria(context: Context){
        val call=categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?>{
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if(response.isSuccessful){
                    registrocategoria=response.body()
                    binding.cboCategoria.adapter= CategoriaComboAdapter(context,registrocategoria)
                }
            }

            override fun onFailure(call: Call<List<CategoriaEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }

    fun MostrarProducto(context: Context){
        val call=productoService!!.findAll()
        call!!.enqueue(object : Callback<List<ProductoEntity>?> {
            override fun onResponse(
                call: Call<List<ProductoEntity>?>,
                response: Response<List<ProductoEntity>?>
            ) {
                if(response.isSuccessful){
                    registroproducto=response.body()
                    binding.lstPro.adapter= ProductoAdapter(context,registroproducto)
                }
            }

            override fun onFailure(call: Call<List<ProductoEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }

    fun RegistrarProducto(context: Context, c: ProductoEntity){
        val call=productoService!!.add(c)
        call!!.enqueue(object : Callback<ProductoEntity?> {
                override fun onResponse(
                call: Call<ProductoEntity?>,
                response: Response<ProductoEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se registro")
                }
            }
            override fun onFailure(call: Call<ProductoEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    //funcion para actualizar el fragmento
    fun ActualizarFragmento(f:Fragment){
        ft=fragmentManager?.beginTransaction()
        ft!!.replace(R.id.contenedor,f,null)
        ft!!.addToBackStack(null)
        ft!!.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}