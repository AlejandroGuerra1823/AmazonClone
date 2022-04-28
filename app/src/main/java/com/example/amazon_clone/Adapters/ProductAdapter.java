package com.example.amazon_clone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.amazon_clone.Home;
import com.example.amazon_clone.MainActivity;
import com.example.amazon_clone.Models.Product;
import com.example.amazon_clone.Services.ListProductService;
import com.example.amazon_clone.Sing_up;
import com.example.amazon_clone.databinding.ProductItemBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ProductItemBinding productItemBinding;
    private ArrayList<Product> productArrayList;
    private String url,enpoint;
    Retrofit retrofit;
    private ViewBinding jb;



    public ProductAdapter(Context context, ArrayList<Product> productArrayList){
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    //cada vez que se va a crear un elemento . oncreat del mainactivity
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = ProductItemBinding.inflate(LayoutInflater.from(context));
        return new ProductViewHolder(productItemBinding);
    }


    //onbindviewholder recorre el ciclo del arraylist
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        holder.itemBinding.NombreFroducto.setText(product.getNombre());
        holder.itemBinding.Descripcion.setText(product.getDescripcion());
        holder.itemBinding.Precio.setText(product.getPrecio());
        holder.itemBinding.BtnAAdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              searchProduct(product);

            }
        });

        url = product.getImagen();
        Glide.with(context).load(url).into(holder.itemBinding.Imagen);

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding itemBinding;
        public ProductViewHolder(@NonNull ProductItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }


    public void searchProduct(Product product){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.5/amazon_clone/ApiRest/Carrito/adicionar_carrito.php", new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("registro correctamente")) {

                    Toast.makeText(context, "Producto añadido", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(context, "no se pudo insertar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
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

        RequestQueue queue= Volley.newRequestQueue(context);
        queue.add(request);
    }


}











