package com.example.alancasas.reciclerview.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.alancasas.reciclerview.Adapters.MyAdapter
import com.example.alancasas.reciclerview.R

class MainActivity : AppCompatActivity() {

    //Creamos las variables que vamos a necesitar sin inicializarlas en un principio
    lateinit var names : ArrayList<String>
    lateinit var recyclerView : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var adapter: MyAdapter

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Llamamos a una funcion que nos devuelve un array de nombres que sera lo que se muestre en la interface grafica
        names = getAllNames()

        //Inicializamos el recyclerview con los valores que tenemos en el layout principal.
        recyclerView = findViewById(R.id.recycler_view)

        //Creamos un manegador de layouts de tipo LinearLayout
        layoutManager = LinearLayoutManager(this)

        //Nuevo layout para usarlo en nuestro recyclerView. En forma de grid (columnas)
        layoutManager = GridLayoutManager(this,3)

        //Otro tipo de layout para nuestro recyclerView, este permite redimensionar las filas o columnas dependiendo de los valores que le pongamos
        //Se redimensionan por ejemplo con fotos se ajusta a la foto a mostrar.
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

        //Inicializamos el adaptador del tipo de nuestro adaptador personalizado pasandole los nombres que seran mostrados, el layout que se va a mostrar, y el contexto de la aplicacion
        adapter = MyAdapter(names,R.layout.recycler_view_item, object : MyAdapter.CustomOnItemClickListener{
            override fun onCustomItemClickListener(name: String, position: Int) {
                Toast.makeText(applicationContext, "Se esta borrando ${name}",Toast.LENGTH_LONG).show()
                deleteElement(position)
            }

        })

        //Hacemos que el layout sea siempre de tamaño fijo
        recyclerView.setHasFixedSize(true)
        //Le pasamos una animacion por defecto al recycler view que seran animaciones al borrar e insertar nuevos elementos
        recyclerView.itemAnimator = DefaultItemAnimator()

        //Iniciamos el manegador de layouts del recycler con el manejador que creamos
        recyclerView.layoutManager = layoutManager
        //Le pasamos el adaptador personalizado al recycler view.
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add_name){
            addNewElement(0)
                return true
        }else{
            return super.onOptionsItemSelected(item)
        }
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

    fun addNewElement ( position: Int ){
        names.add(position, "New name ${++counter} ")
        adapter.notifyItemInserted(position)
        layoutManager.scrollToPosition(position)
    }

    fun deleteElement (position : Int) {
        names.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

}
