package com.example.amazon_clone.Services;

import com.example.amazon_clone.Models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListProductService {
    @GET("amazon_clone/ApiRest/features/login/list-product/list-product.php")
    Call<ArrayList<Product>> listProduct();

    @GET("amazon_clone/ApiRest/Carrito/adicionar_carrito.php")
    Call<ArrayList<Product>> productCarrito();

    @GET("amazon_clone/ApiRest/Carrito/list_carrito.php")
    Call<ArrayList<Product>> listCarrito();

    @GET("amazon_clone/ApiRest/Carrito/delete_carrito.php")
    Call<ArrayList<Product>> deleteCarrito();

}
