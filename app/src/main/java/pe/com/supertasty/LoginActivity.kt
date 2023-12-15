package pe.com.supertasty

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import pe.com.apptienditarest.util.Utilidad
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.databinding.ActivityLoginBinding
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    private var clienteservicio:ClienteService? = null
    private var listado_clientes:List<ClienteEntity>? = null
    private var objutilidades:Utilidad = Utilidad()
    private var cliente_autenticado:Long =0L
    private var email:String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listado_clientes = ArrayList()
        clienteservicio= Api.clienteService
        registroCliente(this)

        binding.btnLogin.setOnClickListener {
            email = binding.tilEmail.editText?.text.toString().trim()
            password = binding.tilPassword.editText?.text.toString().trim()
            if(email.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                objutilidades.MostrarAlerta(this,"Login Cliente","Ingrese un correo valido",false)
            }
            else if(password.isNullOrEmpty()){
                objutilidades.MostrarAlerta(this,"Login Cliente","Ingrese un password valido",false)
            }
            else{
                Log.e("CORREO", "el correo es: "+email.toString() )
                Log.e("PASSWORD", "el PASSWORD es: "+password.toString() )

                var idcli = validacionAcceso(this, listado_clientes as ArrayList<ClienteEntity>,email,password)

                Toast.makeText(this, " id: "+ idcli.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun registroCliente(context:Context){
        val call = clienteservicio?.findAll()
        call!!.enqueue(object : Callback<List<ClienteEntity>?>{
            override fun onResponse(
                call: Call<List<ClienteEntity>?>,
                response: Response<List<ClienteEntity>?>
            ) {
               if(response.isSuccessful){
                   listado_clientes = response.body()
               }
            }
            override fun onFailure(call: Call<List<ClienteEntity>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun validacionAcceso(context: Context,listado:ArrayList<ClienteEntity>,correo:String,contrasenia: String):Long{
        for (c:ClienteEntity in listado_clientes as ArrayList<ClienteEntity>){
            if(c.correo.toString().trim() == correo.toString().trim() && c.pasword.toString().trim() == contrasenia.toString().trim()){
                Log.e("CORREO CLIENTE", "el correo es identico"+correo+" == "+ c.correo, )
                Log.e("PASSWORD CLIENTE", "el Password es identico"+correo+" == "+ c.correo, )
                 cliente_autenticado = c.codigo
                siguientePage(cliente_autenticado.toString())
            }
            else{
                cliente_autenticado = 0
            }
        }
        return cliente_autenticado
    }

    fun siguientePage(idCliente:String) {
        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}