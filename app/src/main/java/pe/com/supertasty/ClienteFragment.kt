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
import pe.com.supertasty.Adapter.ClienteAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.databinding.FragmentCategoriaBinding
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClienteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ClienteFragment : Fragment() {

    //creramos un objeto de la entidad
    private val objcliente= ClienteEntity()
    //una variable para trabajar con el servicio
    private var clienteService: ClienteService?=null
    //lista de tipo CategoriaEntity
    private var registrocliente:List<ClienteEntity>?=null
    //creamos un objeto de la clase Utilidad
    private val objutilidad= Utilidad()
    //declaramos variables
    var cod=0
    var nom=""
    var est=false
    //creamos una varaiable para actualizar el fragmento
    var ft: FragmentTransaction?=null


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
        registrocliente=ArrayList()
        //implementamos el servicio
        clienteService= Api.clienteService
        //mostramos la categoria
        MostrarCliente(context)
        //evento boton registrar
       /* binding.btnRegistrarCat.setOnClickListener {
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
                objcliente.nombre=nom
                objcliente.estado=est
                //registramos la categoria
                RegistrarCliente(context,objcliente)
                objutilidad.MostrarAlerta(context,"Registro Categoria","Se registro la categoria"
                    ,false)
            }
            val fcategoria=CategoriaFragment()
            ActualizarFragmento(fcategoria)

        }*/



        return binding.root
    }

    fun MostrarCliente(context: Context){
        val call=clienteService!!.findAll()
        call!!.enqueue(object : Callback<List<ClienteEntity>?> {
            override fun onResponse(
                call: Call<List<ClienteEntity>?>,
                response: Response<List<ClienteEntity>?>
            ) {
                if(response.isSuccessful){
                    registrocliente=response.body()
                    binding.lstCat.adapter= ClienteAdapter(context,registrocliente
                    )
                }
            }

            override fun onFailure(call: Call<List<ClienteEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }

   /* fun RegistrarCliente(context: Context, c:ClienteEntity){
        val call=clienteService!!.add(c)
        call!!.enqueue(object : Callback<ClienteEntity?> {
            override fun onResponse(
                call: Call<ClienteEntity?>,
                response: Response<ClienteEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se registro")
                }
            }

            override fun onFailure(call: Call<CategoriaEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }*/





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