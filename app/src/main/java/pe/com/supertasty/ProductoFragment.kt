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
    //creamos un objeto de la clase Utilidad
    private val objutilidad= Utilidad()
    //declaramos variables
    var cod=0
    var cant=1
    var nom=""
    var pre=0.0
    var cat = arrayListOf<CategoriaEntity>()
    var est=false
    //creamos una varaiable para actualizar el fragmento
    var ft: FragmentTransaction?=null


    /*-----------------llenando al spinner-------------*/

    //private var adaptador_categoria :ArrayAdapter<CategoriaEntity>? = null
    private var listado_categoria: MutableList<CategoriaEntity>? = null

    /*------------------------------------------*/




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



        //creamos el registro de categoria
        registroproducto=ArrayList()
        //implementamos el servicio
        productoService= Api.productoService
        //mostramos la categoria
        MostrarProducto(context)

        //evento boton registrar
        /*binding.btnRegistrarPro.setOnClickListener {
            if(binding.txtNomPro.text.toString().equals("")){
                objutilidad.MostrarAlerta(context,"Registro Categoria","Ingrese el nombre",false)
                binding.txtNomPro.requestFocus()
            }else{
                //capturando valores
                nom=binding.txtNomPro.text.toString()
                pre = binding.txtPrePro.text.toString().toDouble()
                cant = binding.txtCantPro.text.toString().toInt()
                est=if(binding.chkEstPro.isChecked){
                    true
                }else{
                    false
                }
                //enviamos los objetos a la clase
                objproducto.categoria= null
                objproducto.nombre=nom
                objproducto.precio=pre
                objproducto.cantidad= cant.toLong()
                objproducto.estado=est
                //registramos la categoria
                RegistrarProducto(context,objproducto)
                objutilidad.MostrarAlerta(context,"Registro Producto","Se registro El Producto"
                    ,false)
            }
            val fproducto=ProductoFragment()
            ActualizarFragmento(fproducto)
            }

            */


        /*{
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItemId = "custom_id_" + position;
                // Puedes usar selectedItemId según tus necesidades
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Aquí puedes manejar el caso en que no se haya seleccionado nada
            }
        });

    */
        return binding.root
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