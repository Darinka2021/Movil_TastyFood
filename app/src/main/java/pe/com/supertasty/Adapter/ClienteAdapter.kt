package pe.com.supertasty.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import pe.com.supertasty.Entity.ClienteEntity
import pe.com.supertasty.Entity.ProductoEntity
import pe.com.supertasty.R


class ClienteAdapter (context: Context?, val lista:List<ClienteEntity>?): BaseAdapter(){
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
            vista=layoutInflater.inflate(R.layout.elemento_lista_cliente,parent,false)
            val objcliente=getItem(position) as ClienteEntity
            //creamos los controles de los elementos de la lista
            val lblCod=vista!!.findViewById<TextView>(R.id.lblCodCli)
            val lblCor=vista!!.findViewById<TextView>(R.id.lblCorCli)
            val lblNom=vista!!.findViewById<TextView>(R.id.lblNomCli)
            val lblUse=vista!!.findViewById<TextView>(R.id.lblUseCli)
           val lblPas=vista!!.findViewById<TextView>(R.id.lblPasCli)
           val lblEst=vista!!.findViewById<TextView>(R.id.lblEstCli)
            //agregamos valores
            lblCod.text=objcliente.codigo.toString()
            lblCor.text=objcliente.nombre
            lblNom.text=objcliente.correo
            lblUse.text=objcliente.nomusuario
           lblPas.text=objcliente.pasword

            if(objcliente.estado==true){
                lblEst.text="Habilitado"
            }else{
                lblEst.text="deshabilitado"
            }
        }
        return vista!!
    }

}