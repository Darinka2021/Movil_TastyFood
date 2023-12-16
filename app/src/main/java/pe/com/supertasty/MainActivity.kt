package pe.com.supertasty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import pe.com.supertasty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.jmiCategoria -> {
                val fcategoria = CategoriaFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fcategoria)
                    .commit()
                true
            }
            R.id.jmiProducto -> {
                val fproducto = ProductoFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fproducto)
                    .commit()
                true
            }
            R.id.jmiCliente -> {
                val fcliente = ClienteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fcliente)
                    .commit()
                true
            }
            R.id.jmiDetallePedido -> {
                val fcliente = DetallePedidoFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fcliente)
                    .commit()
                true
            }

            R.id.jmiRegistro -> {
                val fcliente = RegistroFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fcliente)
                    .commit()
                true
            }
            R.id.jmiPedido -> {
                val fcliente = PedidoFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, fcliente)
                    .commit()
                true
            }
            R.id.jmiSalir -> {
                finish()
                true
            }
            /*R.id.jmiCerrarSesion -> {
                logout()
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}