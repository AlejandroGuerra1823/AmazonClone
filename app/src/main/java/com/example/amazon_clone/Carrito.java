package com.example.amazon_clone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_clone.Adapters.ProductAdapter;
import com.example.amazon_clone.Adapters.ProductCarritoAdapter;
import com.example.amazon_clone.Models.Product;
import com.example.amazon_clone.Services.ListProductService;
import com.example.amazon_clone.databinding.ActivityCarritoBinding;
import com.example.amazon_clone.databinding.ActivityHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Carrito extends AppCompatActivity {

    private ActivityCarritoBinding jb;//error en javabinding
    ArrayList<Product> productArrayList;
    ProductCarritoAdapter productCarritoAdapter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        jb = ActivityCarritoBinding.inflate(getLayoutInflater());
        View view = jb.getRoot();
        setContentView(view);

        productArrayList = new ArrayList<>();
        productCarritoAdapter = new ProductCarritoAdapter(this, productArrayList);
        jb.rvProducts1.setHasFixedSize(true);//error
        jb.rvProducts1.setLayoutManager(new LinearLayoutManager(this));//error
        jb.rvProducts1.setAdapter(productCarritoAdapter);//error


        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5/").addConverterFactory(GsonConverterFactory.create())
                .build();

       productCarrito();
       jb.imageButton2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               refresh();
           }
       });

       jb.imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               menu();
           }
       });
       jb.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               compra();
           }
       });

    }

    public void productCarrito(){

        ArrayList<Product> products = new ArrayList();



        ListProductService listProductService = retrofit.create(ListProductService.class);
        Call<ArrayList<Product>> productCarrito = listProductService.listCarrito();
        productCarrito.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {


                for (int i = 0; i<response.body().size(); i++){
                    productArrayList.add(response.body().get(i));
                }

                productCarritoAdapter.notifyDataSetChanged();
            }



            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    public void refresh(){
        Intent intent= new Intent(this, Carrito.class);
        startActivity(intent);

    }
    public void menu(){
        Intent intent = new Intent(this, Home.class);
    }

    public void compra(){


        for (int o=0; o< productArrayList.size(); o++){
            Product product = productArrayList.get(o);

            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.5/amazon_clone/ApiRest/Carrito/delete_carrito.php", new com.android.volley.Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Producto eliminado")) {

                        Toast.makeText(Carrito.this, "Producto eliminado", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(Carrito.this, "no se pudo eliminar", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Carrito.this, "", Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> map= new HashMap<String, String>();


                    map.put("id", product.getId());
                    map.put("nombre",product.getNombre());
                    map.put("descripcion", product.getDescripcion());
                    map.put("precio",product.getPrecio());
                    map.put("imagen",product.getImagen());


                    return map;
                }
            };

            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
        }

        }

    }
}