package pe.com.supertasty

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.com.supertasty.Adapter.CategoriaComboAdapter
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.Service.CategoriaService
import pe.com.supertasty.Service.ProductoService
import pe.com.supertasty.databinding.FragmentDetallePedidoBinding
import pe.com.supertasty.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePedidoFragment : Fragment() {
    private var productoService: ProductoService?=null
    private var categoriaService: CategoriaService? = null
    private var registroproducto:List<ProductoEntity>?=null
    private var registrocategoria:List<CategoriaEntity>?=null

    private var _binding: FragmentDetallePedidoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallePedidoBinding.inflate(inflater, container, false)

        val context = requireContext()
        registrocategoria = ArrayList()
        categoriaService = Api.categoriaService

       //cargarComboCategoria(context)

        return binding.root
    }

    /*fun cargarComboCategoria(context: Context){
        val call = categoriaService!!.findAll()
        call!!.enqueue(object : Callback<List<CategoriaEntity>?> {
            override fun onResponse(
                call: Call<List<CategoriaEntity>?>,
                response: Response<List<CategoriaEntity>?>
            ) {
                if(response.isSuccessful){
                    registrocategoria = response.body()
                    binding.cboCategoria.adapter = CategoriaComboAdapter(context, registrocategoria)
                }
            }

            override fun onFailure(call: Call<List<CategoriaEntity>?>, t: Throwable) {
                Log.e("error: ", t.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    */

}