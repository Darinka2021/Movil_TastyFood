package pe.com.supertasty.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pe.com.supertasty.Entity.DetallePedidoEntity
import pe.com.supertasty.Entity.PedidoEntity
import pe.com.supertasty.R

class DetallePedidoAdapter(context: Context?, val lista: List<DetallePedidoEntity>?) : BaseAdapter() {
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
            vista = layoutInflater.inflate(R.layout.elemento_lista_pedido, parent, false)

            val objproducto = getItem(position) as PedidoEntity

            val lblCod = vista!!.findViewById<TextView>(R.id.lblCodPed)
            val lblPre = vista!!.findViewById<TextView>(R.id.lblPrePed)
            val lblCant = vista!!.findViewById<TextView>(R.id.lblCantPed)
            val lblCat = vista!!.findViewById<TextView>(R.id.lblCatPed)

            lblCod.text = objproducto.codigo.toString()
            lblPre.text = objproducto.precio.toString()
            lblCat.text = objproducto.categoria.nombre.toString()
            lblCant.text = objproducto.cantidad.toString()
        }
        return vista!!
    }


}