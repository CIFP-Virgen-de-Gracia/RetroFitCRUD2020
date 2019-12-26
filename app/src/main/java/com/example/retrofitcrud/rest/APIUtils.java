package com.example.retrofitcrud.rest;

public class APIUtils {
    // IP del servidor
    private static final String server = "10.0.2.2";
    // Puerto del microservicio
    private static final String port = "8080";
    // IP del servicio
    public static final String API_URL = "http://"+server+":"+port+"/";

    private APIUtils() {
    }

    // Constructor del servicio con los elementos de la interfaz
    public static ProductoRest getService() {
        return RetrofitClient.getClient(API_URL).create(ProductoRest.class);
    }

}