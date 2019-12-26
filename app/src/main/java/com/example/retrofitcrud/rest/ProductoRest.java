package com.example.retrofitcrud.rest;

import com.example.retrofitcrud.model.Producto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductoRest {
    // Obtener todos
    // GET: http://localhost:8080/productos
    @GET("productos/")
    Call<List<Producto>> findAll();

    // Obtener uno producto por ID
    // GET: http://localhost:8080/productos/{id}
    @GET("productos/{id}")
    Call<Producto> findById(@Path("id") Long id);

    // Crear un producto
    //POST: http://localhost:8080/productos
    @POST("productos/")
    Call<Producto> create(@Body Producto producto);

    // Elimina un productp
    // DELETE: http://localhost:8080/productos/{id}
    @DELETE("productos/{id}")
    Call<Producto> delete(@Path("id") Long id);

    // Actualiza un producto
    // PUT: http://localhost:8080/productos/{id}
    @PUT("productos/{id}")
    Call<Producto> update(@Path("id") Long id, @Body Producto producto);

}
