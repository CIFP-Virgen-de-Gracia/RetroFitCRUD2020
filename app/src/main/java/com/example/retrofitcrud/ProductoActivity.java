package com.example.retrofitcrud;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofitcrud.rest.APIUtils;
import com.example.retrofitcrud.rest.ProductoRest;

public class ProductoActivity extends AppCompatActivity {

    ProductoRest productoRest;
    EditText edtProductoId;
    EditText edtEdtProductoNombre;
    Button btnSalvar;
    Button btnEliminar;
    TextView txtProductoId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Configuramos la barra de título
        setTitle("Producto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Enlazamos los elementos de la interfaz
        txtProductoId = (TextView) findViewById(R.id.txtProductoId);
        edtProductoId = (EditText) findViewById(R.id.edtProductoId);
        edtEdtProductoNombre = (EditText) findViewById(R.id.edtProductoNombre);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        // Llamamos al servicio
        productoRest = APIUtils.getService();

        // Obrenemos El Bundle de llamar a una actividad
        Bundle extras = getIntent().getExtras();
        final String productoId = extras.getString("producto_id");
        String productoNombre = extras.getString("producto_nombre");

        // Pasamos los datos obtenidos
        edtProductoId.setText(productoId);
        edtEdtProductoNombre.setText(productoNombre);

        // Si es un nuevo producto, no lo podemos editar
        if(productoId != null && productoId.trim().length() > 0 ){
            edtProductoId.setFocusable(false);
        } else {
            // Ademas no podemos eliminarlo
            txtProductoId.setVisibility(View.INVISIBLE);
            edtProductoId.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.INVISIBLE);
        }

        // Eventos de los botones
    }

    /**
     * Si pulsamos atras volvemos
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // Terminamos la actividad
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
