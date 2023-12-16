package pe.com.supertasty.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.R

class ProductoComboAdapter (context: Context?, val lista: List<ProductoEntity>?) : BaseAdapter() {
    private var layoutInflater: LayoutInflater
    init {
        layoutInflater = LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return lista!!.size
    }
    override fun getItem(position: Int): Any {
        return lista!![position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView

        if (vista == null) {
            vista = layoutInflater.inflate(R.layout.elemento_spinner_producto, parent, false)
            val objproducto = getItem(position) as ProductoEntity

            val lblNom = vista!!.findViewById<TextView>(R.id.cboProducto)
            lblNom.text = objproducto.nombre
        }
        return vista!!
    }


}