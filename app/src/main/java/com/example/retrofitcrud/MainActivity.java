package com.example.retrofitcrud;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofitcrud.model.Producto;
import com.example.retrofitcrud.rest.APIUtils;
import com.example.retrofitcrud.rest.ProductoRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Elementos de la interfaz
    Button btnAddProducto;
    Button btnGetProductoList;
    ListView listView;

    // Para manejar los elementos de la API REST
    ProductoRest productoRest;
    List<Producto> list = new ArrayList<Producto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cambiamos el título
        setTitle("Retrofit 2 CRUD Productos");

        // Enlazamos los elementos interactivos del layout
        btnAddProducto = (Button) findViewById(R.id.btnAdd);
        btnGetProductoList = (Button) findViewById(R.id.btnGet);
        listView = (ListView) findViewById(R.id.listView);

        // Iniciamos la API REST
        productoRest = APIUtils.getService();
        
        // Eventos de los botones
        btnGetProductoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Listamos los productos
                listarProductos();
            }
        });

    }

    private void listarProductos() {
        // Creamos la tarea que llamará al servicio rest y la encolamos
        Call<List<Producto>> call = productoRest.findAll();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.isSuccessful()){
                    // Si tienes exito nos quedamos con el ResponseBody, listado en JSON
                    // Nos hace el pasrser automáticamente
                    list = response.body();
                    listView.setAdapter(new ProductosAdapter(MainActivity.this, R.layout.list_productos, list));
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
