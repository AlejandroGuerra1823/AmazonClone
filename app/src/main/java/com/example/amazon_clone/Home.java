package com.example.amazon_clone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_clone.Adapters.ProductAdapter;
import com.example.amazon_clone.Models.Product;
import com.example.amazon_clone.Services.ListProductService;
import com.example.amazon_clone.databinding.ActivityHomeBinding;
import com.example.amazon_clone.databinding.ActivitySingInBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding jb;
    private FirebaseFirestore db;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    private String url;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jb = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = jb.getRoot();
        setContentView(view);
        db = FirebaseFirestore.getInstance();
        url= "/amazon_clone/ApiRest/features/list-product/list-product.php";

        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productArrayList);
        jb.rvProducts.setHasFixedSize(true);
        jb.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        jb.rvProducts.setAdapter(productAdapter);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5/").addConverterFactory(GsonConverterFactory.create())
                .build();
        getProducts();

        jb.carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Home.this, Carrito.class);
                startActivity(intent);
            }
        });
    }


    public void getProducts(){
        ArrayList<Product> products = new ArrayList();


        ListProductService listProductService = retrofit.create(ListProductService.class);
        Call<ArrayList<Product>> listProduct = listProductService.listProduct();
        listProduct.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                Toast.makeText(Home.this, "hola", Toast.LENGTH_SHORT).show();



                for (int i = 0; i<response.body().size(); i++){
                    productArrayList.add(response.body().get(i));
                }
                productAdapter.notifyDataSetChanged();
            }
  /*  Product p1 = new Product();
        p1.setNombre("Mouse");
        Product p2 = new Product();
        p2.setNombre("Teclado");
        Product p3 = new Product();
        p3.setDescripcion("Teclado");
        Product p4 = new Product();
        p4.setPrecio("Teclado");

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);*/

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {



            }
        });

    }



    }

