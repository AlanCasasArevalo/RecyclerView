package com.example.alancasas.reciclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter ( names: List<String>, customLayout: Int, onItemClick: OnItemClickListener ) : RecyclerView.Adapter<MyAdapter.ViewHolder>( ) {

    private var names : List<String> = names

    private var customLayout : Int = customLayout

    private var onItemClick : OnItemClickListener = onItemClick

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var customView: View
        customView = LayoutInflater.from(parent?.context).inflate(customLayout,parent,false)
        var customViewHolder = ViewHolder(customView)

        return customViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(names[position], onItemClick)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var customName : TextView? = null

        init {
            this.customName = itemView?.findViewById(R.id.custom_text_view)
        }

        fun bind(name: String, listener: OnItemClickListener ){
            customName?.text = name
            itemView.setOnClickListener {
                listener.onItemClick(name, adapterPosition)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(name: String, position: Int)
    }

}

































