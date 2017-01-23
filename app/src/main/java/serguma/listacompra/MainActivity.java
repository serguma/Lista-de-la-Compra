package serguma.listacompra;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private boolean[] productoComprado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asigno el array de recursos
        TypedArray losProductos = getResources().obtainTypedArray(R.array.productos_compra);

        if(null != savedInstanceState){
            //si hay contenido lo recupero en el array de booleanos
            productoComprado = (boolean[]) savedInstanceState.get("Valores");
            Log.d(getClass().getCanonicalName(), "Hay contenido");

        }else{
            //array de control para saber si los productos han sido comprados
            productoComprado = new boolean[losProductos.length()];
            //inicializamos a cero el array
            Arrays.fill(productoComprado, Boolean.FALSE);
            Log.d(getClass().getCanonicalName(), "No hay contenido");
        }

        //instancio el adaptador
        Adapter arrayProductos = new ListaAdapter(this, losProductos, productoComprado);

        //el adaptador a la lista
        ListView listView = (ListView) findViewById(R.id.listadoProductos);
        listView.setAdapter((ListAdapter) arrayProductos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              ImageView imagen = (ImageView) view.findViewById(R.id.imageView);

              if(productoComprado[position] == false) {
                  view.setBackgroundColor(Color.rgb(243, 243, 243));
                  imagen.setVisibility(View.VISIBLE);
                  productoComprado[position] = true;
              }else{
                  view.setBackgroundColor(Color.rgb(255, 255, 255));
                  imagen.setVisibility(View.INVISIBLE);
                  productoComprado[position] = false;
              }

          }
        }
        );



    }


    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        //guardo el array de estado
        bundle.putBooleanArray("Valores", productoComprado);
    }

}
