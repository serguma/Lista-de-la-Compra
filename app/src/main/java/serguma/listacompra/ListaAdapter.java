package serguma.listacompra;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sergu on 23/01/2017.
 */

public class ListaAdapter extends BaseAdapter {

    private Context ctx;
    private TypedArray listaDeCompra;
    private boolean[] productoComprado;

    public ListaAdapter(Context context, TypedArray listaCompra, boolean[] estanComprados){
        this.ctx = context;
        this.listaDeCompra = listaCompra;
        this.productoComprado = estanComprados;
    }

    @Override
    public int getCount() {
        return listaDeCompra.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vista = null;

        if(convertView == null){

            //preparo para inflar e inflo la vista
            Activity activity = (Activity) ctx;
            LayoutInflater listado = activity.getLayoutInflater();
            vista = listado.inflate(R.layout.producto, parent, false);
            Log.d(getClass().getCanonicalName(), "Se infla la vista");

        }else{
            //Reciclo la vista

            vista = convertView;
            Log.d(getClass().getCanonicalName(), "Reciclando vista");
        }

        //cargo los valores en el textview de la vista
        TextView textView = (TextView) vista.findViewById(R.id.textView);
        textView.setText(this.listaDeCompra.getText(position));

        //la imagen de ok
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView);

        if(productoComprado[position] == true){
            vista.setBackgroundColor(Color.rgb(243, 243, 243));
            imagen.setVisibility(View.VISIBLE);
        }else{
            vista.setBackgroundColor(Color.rgb(255, 255, 255));
            imagen.setVisibility(View.INVISIBLE);
        }

        return vista;
    }
}
