package pe.com.supertasty

import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction




import pe.com.apptienditarest.util.Utilidad

import pe.com.supertasty.Adapter.CategoriaAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.databinding.FragmentCategoriaBinding
import pe.com.supertasty.remote.Api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CategoriaFragment : Fragment() {

    //creramos un objeto de la entidad
    private val objcategoria=CategoriaEntity()
    //una variable para trabajar con el servicio
    private var categoriaService:CategoriaService?=null
    //lista de tipo CategoriaEntity
    private var registrocategoria:List<CategoriaEntity>?=null
    //creamos un objeto de la clase Utilidad
    private val objutilidad=Utilidad()
    //declaramos variables
    var cod=0L
    var nom=""
    var est=false
    var fila=-1
    //creamos una varaiable para actualizar el fragmento
    var ft:FragmentTransaction?=null


    private var _binding: FragmentCategoriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriaBinding.inflate(inflater, container, false)
        val context=requireContext()

        //creamos el registro de categoria
        registrocategoria=ArrayList()
        //implementamos el servicio
        categoriaService= Api.categoriaService
        //mostramos la categoria
        MostrarCategoria(context)
        //evento boton registrar
        binding.btnRegistrarCat.setOnClickListener {
            if(binding.txtNomCat.text.toString().equals("")){
                objutilidad.MostrarAlerta(context,"Registro Categoria","Ingrese el nombre",false)
                binding.txtNomCat.requestFocus()
            }else{
                //capturando valores
                nom=binding.txtNomCat.text.toString()
                est=if(binding.chkEstCat.isChecked){
                    true
                }else{
                    false
                }
                //enviamos los objetos a la clase
                objcategoria.nombre=nom
                objcategoria.estado=est
                //registramos la categoria
                RegistrarCategoria(context,objcategoria)
                objutilidad.MostrarAlerta(context,"Registro Categoria","Se registro la categoria"
                    ,false)
                val fcategoria=CategoriaFragment()
                ActualizarFragmento(fcategoria)
            }


        }

        //para la seleccion de la lista
        binding.lstCat.setOnItemClickListener {
                parent, view, position, id ->
            fila=position
            binding.CodCat.setText(
                ""+(registrocategoria as ArrayList<CategoriaEntity>).get(fila)
                    .codigo
            )
            binding.txtNomCat.setText(
                ""+(registrocategoria as ArrayList<CategoriaEntity>).get(fila)
                    .nombre
            )
            if((registrocategoria as ArrayList<CategoriaEntity>).get(position)
                    .estado){
                binding.chkEstCat.isChecked=true
            }else{
                binding.chkEstCat.isChecked=false
            }
        }

        //evento del boton actualizar
        binding.btnActualizarCat.setOnClickListener {
            if(fila==-1){
                objutilidad.MostrarAlerta(context,"Actualizacion de datos",
                    "Por favor seleccione un valor de la lista",
                    false)
                binding.lstCat.requestFocus()

            }else{
                //capturando valores
                cod=binding.CodCat.text.toString().toLong()
                nom=binding.txtNomCat.text.toString()
                est=if(binding.chkEstCat.isChecked){
                    true
                }else{
                    false
                }
                //enviamos los objetos a la clase
                objcategoria.codigo=cod
                objcategoria.nombre=nom
                objcategoria.estado=est

                ActualizarCategoria(context,objcategoria,
                    cod)
                objutilidad.MostrarAlerta(context,"Actualizacion de Categoria",
                    "Se actualizo la categoria"
                    ,false)
                val fcategoria=CategoriaFragment()
                ActualizarFragmento(fcategoria)

            }



        }

        //evento del boton actualizar
        binding.btnEliminarCat.setOnClickListener {
            if(fila==-1){
                objutilidad.MostrarAlerta(context,"Eliminacion de Categoria",
                    "Por favor seleccione un valor de la lista",
                    false)
                binding.lstCat.requestFocus()

            }else{
                //capturando valores
                cod=binding.CodCat.text.toString().toLong()

                EliminarCategoria(context,cod)
                objutilidad.MostrarAlerta(context,"Eliminacion de Categoria",
                    "Se elimino la categoria"
                    ,false)
                val fcategoria=CategoriaFragment()
                ActualizarFragmento(fcategoria)

            }



        }

        return binding.root
    }

    fun MostrarCategoria(context: Context){
        val call=categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?>{
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if(response.isSuccessful){
                    registrocategoria=response.body()
                    binding.lstCat.adapter=CategoriaAdapter(context,registrocategoria)
                }
            }

            override fun onFailure(call: Call<List<CategoriaEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }

    fun RegistrarCategoria(context: Context,c:CategoriaEntity){
        val call=categoriaService!!.add(c)
        call!!.enqueue(object : Callback<CategoriaEntity?>{
            override fun onResponse(
                call: Call<CategoriaEntity?>,
                response: Response<CategoriaEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se registro")
                }
            }

            override fun onFailure(call: Call<CategoriaEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }

    fun ActualizarCategoria(context: Context,c:CategoriaEntity,id:Long){
        val call=categoriaService!!.update(id,c)
        call!!.enqueue(object : Callback<CategoriaEntity?>{
            override fun onResponse(
                call: Call<CategoriaEntity?>,
                response: Response<CategoriaEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se actualizo")
                }
            }

            override fun onFailure(call: Call<CategoriaEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }
    fun EliminarCategoria(context: Context,id:Long){
        val call=categoriaService!!.delete(id)
        call!!.enqueue(object : Callback<CategoriaEntity?>{
            override fun onResponse(
                call: Call<CategoriaEntity?>,
                response: Response<CategoriaEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se elimino")
                }
            }

            override fun onFailure(call: Call<CategoriaEntity?>, t: Throwable) {
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