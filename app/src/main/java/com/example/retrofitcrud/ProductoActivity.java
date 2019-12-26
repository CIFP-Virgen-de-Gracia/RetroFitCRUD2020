package com.example.retrofitcrud;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofitcrud.model.Producto;
import com.example.retrofitcrud.rest.APIUtils;
import com.example.retrofitcrud.rest.ProductoRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {

    ProductoRest productoRest;
    EditText edtProductoId;
    EditText edtProductoNombre;
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
        edtProductoNombre = (EditText) findViewById(R.id.edtProductoNombre);
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
        edtProductoNombre.setText(productoNombre);

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

        // Botón salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto();
                p.setNombre(edtProductoNombre.getText().toString());
                // Vamos a ver en que modo estamos
                if(productoId != null && productoId.trim().length() > 0){
                    //ctualizar Producto
                    actualizarProducto(Long.valueOf(productoId), p);
                } else {
                    //salvamos el producto
                    salvarProducto(p);
                }
            }
        });

        // botón eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProducto(Long.valueOf(productoId));
                // Abrimos la pantalla
                Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Salva un producto mediante RESR
     * @param p Producto a salvar
     */
    private void salvarProducto(Producto p) {
        // Llamamos al metodo de crear
        Call<Producto> call = productoRest.create(p);
        call.enqueue(new Callback<Producto>() {
            // Si todo ok
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductoActivity.this, "Producto salvado", Toast.LENGTH_SHORT).show();
                }
            }

            // Si error
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Eliminamos el producto
     * @param id ID del producto
     */
    private void eliminarProducto(Long id) {
        // Llamamos al servicio a eliminar
        Call<Producto> call = productoRest.delete(id);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                // Si ok
                if(response.isSuccessful()){
                    Toast.makeText(ProductoActivity.this, "Producto Elimnado", Toast.LENGTH_SHORT).show();
                }
            }
            //Si error
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Actualizamos un producto
     * @param id ID del Producto
     * @param p Producto
     */
    private void actualizarProducto(Long id, Producto p) {
        // Llamamos al método actualizar
        Call<Producto> call = productoRest.update(id, p);
        call.enqueue(new Callback<Producto>() {
            @Override
            // Si todo ok
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductoActivity.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                }
            }
            // Si error
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
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
