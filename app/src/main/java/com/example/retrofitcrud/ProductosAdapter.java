package com.example.retrofitcrud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.example.retrofitcrud.model.Producto;

import java.util.List;

/**
 * Adaptador de la lista de productos
 */
public class ProductosAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto> productos;

    public ProductosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context = context;
        this.productos = objects;
    }

    // Construimos la vista y los visualizamos
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_productos, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtProductoId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtPorductoNombre);

        txtUserId.setText(String.format("#ID: %d", productos.get(pos).getId()));
        txtUsername.setText(String.format("Nombre Producto: %s", productos.get(pos).getNombre()));

        // Abrimos la actividad al hacer click en un elemento de la lista
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamamos a abrir producto
                abrirProducto(pos);
            }
        });

        return rowView;
    }

    /**
     * Abre la actividad Producto
     * @param pos posición del objeto a mandar
     */
    private void abrirProducto(int pos) {
        Intent intent = new Intent(context, ProductoActivity.class);
        intent.putExtra("producto_id", String.valueOf(productos.get(pos).getId()));
        intent.putExtra("producto_nombre", productos.get(pos).getNombre());
        context.startActivity(intent);
    }
}