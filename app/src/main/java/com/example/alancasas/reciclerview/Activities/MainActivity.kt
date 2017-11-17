package com.example.alancasas.reciclerview.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.alancasas.reciclerview.Adapters.MyAdapter
import com.example.alancasas.reciclerview.R

class MainActivity : AppCompatActivity() {

    //Creamos las variables que vamos a necesitar sin inicializarlas en un principio
    var names : ArrayList<String>? = null
    var recyclerView : RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Llamamos a una funcion que nos devuelve un array de nombres que sera lo que se muestre en la interface grafica
        names = getAllNames()

        //Inicializamos el recyclerview con los valores que tenemos en el layout principal.
        recyclerView = findViewById(R.id.recycler_view)

        //Creamos un manegador de layouts de tipo LinearLayout
        layoutManager = LinearLayoutManager(this)

        //Inicializamos el adaptador del tipo de nuestro adaptador personalizado pasandole los nombres que seran mostrados, el layout que se va a mostrar, y el contexto de la aplicacion
        adapter = MyAdapter(names!!,R.layout.recycler_view_item, applicationContext)

        //Hacemos que el layout sea siempre de tamaño fijo
        recyclerView?.setHasFixedSize(true)
        //Le pasamos una animacion por defecto al recycler view que seran animaciones al borrar e insertar nuevos elementos
        recyclerView?.itemAnimator = DefaultItemAnimator()

        //Iniciamos el manegador de layouts del recycler con el manejador que creamos
        recyclerView?.layoutManager = layoutManager
        //Le pasamos el adaptador personalizado al recycler view.
        recyclerView?.adapter = adapter

    }
    
    fun getAllNames () : ArrayList<String>{
        return  arrayListOf(
            "Alan",
                "Bibi",
                "Mario",
                "Elsa",
                "Pilar",
                "Pedro"
        )

    }

}
