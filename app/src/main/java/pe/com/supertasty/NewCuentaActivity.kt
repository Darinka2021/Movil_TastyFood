package pe.com.supertasty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import pe.com.apptienditarest.util.Utilidad
import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Service.ClienteService
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewCuentaActivity : AppCompatActivity() {
    private var objcliente = ClienteEntity()
    private var clienteService: ClienteService? = null
    private var objutilidad = Utilidad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cuenta)

        clienteService = Api.clienteService

        val btnRegistrarCli = findViewById<Button>(R.id.btnRegistrarCli)
        val txtNomCli = findViewById<EditText>(R.id.txtNomCli)
        val txtCorCli = findViewById<EditText>(R.id.txtCorCli)
        val txtUserCli = findViewById<EditText>(R.id.txtUserCli)
        val txtPasscli = findViewById<EditText>(R.id.txtPasscli)
        val chkEstCli = findViewById<CheckBox>(R.id.chkEstCli)

        btnRegistrarCli.setOnClickListener {
            val nombre = txtNomCli.text.toString().trim()
            val correo = txtCorCli.text.toString().trim()
            val usuario = txtUserCli.text.toString().trim()
            val contraseña = txtPasscli.text.toString().trim()
            val estado = chkEstCli.isChecked

            if (validarCampos(nombre, correo, usuario, contraseña)) {
                objcliente.nombre = nombre
                objcliente.correo = correo
                objcliente.nomusuario = usuario
                objcliente.pasword = contraseña
                objcliente.estado = estado

                RegistrarCliente(objcliente)

                objutilidad.MostrarAlerta(this, "Registro Cliente", "Se registró el cliente", false)

                txtNomCli.text.clear()
                txtCorCli.text.clear()
                txtUserCli.text.clear()
                txtPasscli.text.clear()
                chkEstCli.isChecked = false
            }
        }
    }

    private fun RegistrarCliente(c: ClienteEntity) {
        val call = clienteService!!.add(c)
        call!!.enqueue(object : Callback<ClienteEntity?> {
            override fun onResponse(call: Call<ClienteEntity?>, response: Response<ClienteEntity?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje:", "Se registro")
                    // Redirige a LoginActivity después de un registro exitoso
                    val intent = Intent(this@NewCuentaActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Finaliza la actividad actual
                }
            }

            override fun onFailure(call: Call<ClienteEntity?>, t: Throwable) {
                Log.e("error: ", t.toString())
            }
        })
    }

    private fun validarCampos(nombre: String, correo: String, usuario: String, contraseña: String): Boolean {
        var isValid = true
        var errorMessage = ""

        if (isValid && (!correo.contains('@') || correo.contains(' '))) {
            errorMessage = "El correo no es válido"
            isValid = false
        }

        if (nombre.any { it.isDigit() }) {
            errorMessage = "Nombre no válido"
            isValid = false
        }

        if (isValid && usuario.any { it.isLetterOrDigit().not() }) {
            errorMessage = "El usuario no debe contener símbolos"
            isValid = false
        }
        if (isValid && contraseña.length < 6) {
            errorMessage = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        }

        if (!isValid) {
            objutilidad.MostrarAlerta(this, "Registro Cliente", errorMessage, false)
        }

        return isValid
    }
}