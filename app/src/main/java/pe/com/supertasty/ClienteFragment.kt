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
import pe.com.supertasty.Adapter.ClienteAdapter
import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.databinding.FragmentClienteBinding
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
    //lista de tipo ClienteEntity
    private var registrocliente:List<ClienteEntity>?=null
    //creamos un objeto de la clase Utilidad
    private val objutilidad= Utilidad()
    //declaramos variables
    var cod=0L
    var nom=""
    var est=false
    var fila=-1
    //creamos una varaiable para actualizar el fragmento
    var ft: FragmentTransaction?=null


    private var _binding: FragmentClienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentClienteBinding.inflate(inflater, container, false)
        val context=requireContext()

        //creamos el registro de cliente
        registrocliente=ArrayList()
        //implementamos el servicio
        clienteService= Api.clienteService
        //mostramos la cliente
        MostrarCliente(context)
        //evento boton registrar

        binding.btnRegistrarCli.setOnClickListener {
            val nombre = binding.txtNomCli.text.toString().trim()
            val correo = binding.txtCorCli.text.toString().trim()
            val usuario = binding.txtUserCli.text.toString().trim()
            val contraseña = binding.txtPasscli.text.toString().trim()
            val estado = binding.chkEstCli.isChecked

            if (nombre.isNotEmpty() && correo.isNotEmpty() && usuario.isNotEmpty() && contraseña.isNotEmpty()) {
                //capturando valores
                objcliente.nombre = nombre
                objcliente.correo = correo
                objcliente.nomusuario = usuario
                objcliente.pasword = contraseña
                objcliente.estado = estado

                //registramos el cliente utilizando la función RegistrarCliente()
                RegistrarCliente(context, objcliente)

                //Mostrar alerta o mensaje después del registro
                objutilidad.MostrarAlerta(context, "Registro Cliente", "Se registró el cliente", false)

                // Limpiar campos después de registrar
                binding.txtNomCli.text.clear()
                binding.txtCorCli.text.clear()
                binding.txtUserCli.text.clear()
                binding.txtPasscli.text.clear()
                binding.chkEstCli.isChecked = false

                // Actualizar o navegar al fragmento correspondiente, si es necesario
                val fcliente = ClienteFragment()
                ActualizarFragmento(fcliente)
            } else {
                objutilidad.MostrarAlerta(
                    context,
                    "Registro Cliente",
                    "Por favor, complete todos los campos",
                    false
                )
            }
        }
        binding.lstCli.setOnItemClickListener { parent, view, position, id ->
            fila = position
            binding.CodCli.setText(
                (registrocliente as ArrayList<ClienteEntity>)[fila].codigo.toString()
            )
            binding.txtNomCli.setText(
                (registrocliente as ArrayList<ClienteEntity>)[fila].nombre
            )
            binding.txtCorCli.setText(
                (registrocliente as ArrayList<ClienteEntity>)[fila].correo
            )
            binding.txtUserCli.setText(
                (registrocliente as ArrayList<ClienteEntity>)[fila].nomusuario
            )
            binding.txtPasscli.setText(
                (registrocliente as ArrayList<ClienteEntity>)[fila].pasword
            )
            binding.chkEstCli.isChecked = (registrocliente as ArrayList<ClienteEntity>)[fila].estado
        }


        binding.btnActualizarCli.setOnClickListener {
            if (fila == -1) {
                objutilidad.MostrarAlerta(context, "Actualizacion de datos",
                    "Por favor seleccione un valor de la lista",
                    false)
                binding.lstCli.requestFocus()
            } else {
                //capturando valores
                cod = binding.CodCli.text.toString().toLong()
                nom = binding.txtNomCli.text.toString()
                val correo = binding.txtCorCli.text.toString()
                val usuario = binding.txtUserCli.text.toString()
                val contraseña = binding.txtPasscli.text.toString()
                est = binding.chkEstCli.isChecked

                //enviamos los objetos a la clase
                objcliente.codigo = cod
                objcliente.nombre = nom
                objcliente.correo = correo
                objcliente.nomusuario = usuario
                objcliente.pasword = contraseña
                objcliente.estado = est

                //Actualizar el cliente utilizando la función ActualizarCliente()
                ActualizarCliente(context, objcliente, cod)
                objutilidad.MostrarAlerta(context, "Actualizacion de Cliente",
                    "Se actualizó el cliente", false)

                // Limpiar campos después de actualizar
                binding.txtNomCli.text.clear()
                binding.txtCorCli.text.clear()
                binding.txtUserCli.text.clear()
                binding.txtPasscli.text.clear()
                binding.chkEstCli.isChecked = false

                val fcliente = ClienteFragment()
                ActualizarFragmento(fcliente)
            }

    }

        //evento del boton actualizar
        binding.btnEliminarCli.setOnClickListener {
            if(fila==-1){
                objutilidad.MostrarAlerta(context,"Eliminacion de Cliente",
                    "Por favor seleccione un valor de la lista",
                    false)
                binding.lstCli.requestFocus()

            }else{
                //capturando valores
                cod=binding.CodCli.text.toString().toLong()

                EliminarCliente(context,cod)
                objutilidad.MostrarAlerta(context,"Eliminacion de Cliente",
                    "Se elimino el Cliente"
                    ,false)
                val fcliente=ClienteFragment()
                ActualizarFragmento(fcliente)

            }



        }




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
                    binding.lstCli.adapter= ClienteAdapter(context,registrocliente
                    )
                }
            }

            override fun onFailure(call: Call<List<ClienteEntity>?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }

        })
    }

   fun RegistrarCliente(context: Context, c:ClienteEntity){
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

            override fun onFailure(call: Call<ClienteEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }
    fun ActualizarCliente(context: Context,c:ClienteEntity,id:Long){
        val call=clienteService!!.update(id,c)
        call!!.enqueue(object : Callback<ClienteEntity?>{
            override fun onResponse(
                call: Call<ClienteEntity?>,
                response: Response<ClienteEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se actualizo")
                }
            }

            override fun onFailure(call: Call<ClienteEntity?>, t: Throwable) {
                Log.e("error: ",t.toString())
            }


        })
    }
    fun EliminarCliente(context: Context,id:Long){
        val call=clienteService!!.delete(id)
        call!!.enqueue(object : Callback<ClienteEntity?>{
            override fun onResponse(
                call: Call<ClienteEntity?>,
                response: Response<ClienteEntity?>
            ) {
                if(response.isSuccessful){
                    Log.e("mensaje:","Se elimino")
                }
            }

            override fun onFailure(call: Call<ClienteEntity?>, t: Throwable) {
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