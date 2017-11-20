package com.example.alancasas.reciclerview.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alancasas.reciclerview.R

class MyAdapter (names: ArrayList<String>, customLayout: Int, listener: CustomOnItemClickListener) : RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

    //Creamos las variables que vamos a necesitar en la app
        var names: ArrayList<String> = names
        var customLayout: Int = customLayout
        var itemClickListener: CustomOnItemClickListener = listener

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
        holder?.bind(names[position], itemClickListener)
    }

    //Creamos una personalizacion de nuestra vista y que hara
    class CustomViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){

        //Creamos la vista que queremos mostrar
        var customName: TextView

        //Creamos un itemClickListener personalizado.
        var customItemClickListener: CustomOnItemClickListener? = null

        //Inicializamos los valores
        init {
            customName = itemView.findViewById(R.id.custom_text_view)
        }

        fun bind(name: String, listener: CustomOnItemClickListener){
            customName.text = name

            itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    listener.onCustomItemClickListener(name, adapterPosition)
                }
            })
        }

        //Implementamos el metodo que nos obliga la clase View.OnClickListener haciendo que haga lo que le hayamos programado en la interface.
    }

    //Creamos una interfaz para crear un comportamiento especifico.
    interface CustomOnItemClickListener {
        fun onCustomItemClickListener(name: String, position: Int)
    }
}



































