package pe.com.supertasty.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pe.com.supertasty.Entity.CategoriaEntity
import pe.com.supertasty.R

class CategoriaComboAdapter(context: Context?, val lista:List<CategoriaEntity>?): BaseAdapter(){
    private var layoutInflater: LayoutInflater
    init{
        layoutInflater= LayoutInflater.from(context)
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
        var vista=convertView
        if(vista==null){
            vista=layoutInflater.inflate(R.layout.elemento_spinner_categoria,parent,false)
            val objcategoria=getItem(position) as CategoriaEntity
            //creamos los controles de los elementos de la lista
            val lblNom=vista!!.findViewById<TextView>(R.id.cboNomCategoria)
            //agregamos valores
            lblNom.text = "selecciona"
            lblNom.text=objcategoria.nombre
        }
        return vista!!
    }
}