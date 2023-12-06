package pe.com.supertasty.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.R

class ProductoAdapter(context: Context?, val lista: List<ProductoEntity>?) : BaseAdapter() {
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
            vista = layoutInflater.inflate(R.layout.elemento_lista_producto, parent, false)

            val objproducto = getItem(position) as ProductoEntity

            val lblCod = vista!!.findViewById<TextView>(R.id.lblCodPro)
            val lblNom = vista!!.findViewById<TextView>(R.id.lblNomPro)
            val lblPre = vista!!.findViewById<TextView>(R.id.lblPrePro)
            val lblCant = vista!!.findViewById<TextView>(R.id.lblCantPro)
            val lblEst = vista!!.findViewById<TextView>(R.id.lblEstPro)
            val lblCodCat = vista!!.findViewById<TextView>(R.id.lblCodCatPro)

            lblCod.text = objproducto.codigo.toString()
            lblNom.text = objproducto.nombre
            lblPre.text = objproducto.precio.toString()
            lblCant.text = objproducto.cantidad.toString()
            lblEst.text = if (objproducto.estado == true) {
                "Habilitado"
            } else {
                "Deshabilitado"
            }

            lblCodCat.text = objproducto.categoria.toString()

        }

        return vista!!
    }


}