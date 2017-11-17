package com.example.alancasas.reciclerview.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.alancasas.reciclerview.R

class MyAdapter (names: ArrayList<String>, customLayout: Int, context: Context) : RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

    //Creamos las variables que vamos a necesitar en la app
    private var names: ArrayList<String> = names
    private var customLayout: Int = customLayout
    var context = context

    //Implementamos los 3 metodos que nos pide el RecyclerView.Adapter<MyAdapter.CustomViewHolder>()
    //Conteo del numero de nombres que tenemos
    override fun getItemCount(): Int {
        return names.size
    }

    //Inflado de nuestro layout con la vista personalizada que nos llega
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        var customView = LayoutInflater.from(parent?.context).inflate(customLayout, parent, false)
        return CustomViewHolder(customView)
    }

    //Personalizacion de lo que se va a mostrar y cuando se toque en cada vista lo que va a hacer
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        var name = names[position]

        holder?.customName?.text = name
        holder!!.setOnCustomItemClickListener(object : CustomOnItemClickListener{
            override fun onCustomItemClickListener(name: String, position: Int) {
                Toast.makeText(context, "Nombre: ${name}", Toast.LENGTH_LONG).show()
            }
        })
    }

    //Creamos una personalizacion de nuestra vista y que hara
    class CustomViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        //Creamos la vista que queremos mostrar
        var customName: TextView

        //Creamos un listener personalizado.
        var customItemClickListener: CustomOnItemClickListener? = null

        //Inicializamos los valores
        init {
            customName = itemView.findViewById(R.id.custom_text_view)
            itemView.setOnClickListener(this)
        }

        //Creamos una funcion que al llamarla llamara a nuestro listener personalizado
        fun setOnCustomItemClickListener( itemClickListener: CustomOnItemClickListener){
            this.customItemClickListener = itemClickListener
        }

        //Implementamos el metodo que nos obliga la clase View.OnClickListener haciendo que haga lo que le hayamos programado en la interface.
        override fun onClick(view: View?) {
            this.customItemClickListener!!.onCustomItemClickListener(view.toString(), adapterPosition)
        }

    }

    //Creamos una interfaz para crear un comportamiento especifico.
    interface CustomOnItemClickListener {
        fun onCustomItemClickListener(name: String, position: Int)
    }
}



































