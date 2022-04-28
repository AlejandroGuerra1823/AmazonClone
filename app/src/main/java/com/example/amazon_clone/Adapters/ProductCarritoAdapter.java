package com.example.amazon_clone.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.amazon_clone.Carrito;
import com.example.amazon_clone.Home;
import com.example.amazon_clone.Models.Product;
import com.example.amazon_clone.databinding.CarritoItemBinding;
import com.example.amazon_clone.databinding.ProductItemBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class ProductCarritoAdapter extends RecyclerView.Adapter<ProductCarritoAdapter.ProductCarritoViewHolder> {
    private Context context;
    private CarritoItemBinding productItemBinding;
    private ArrayList<Product> productArrayList;
    private String url,enpoint;
    Retrofit retrofit;
    private ViewBinding jb;
    Carrito carrito = new Carrito();



    public ProductCarritoAdapter(Context context, ArrayList<Product> productArrayList){
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    //cada vez que se va a crear un elemento . oncreat del mainactivity
    public ProductCarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = CarritoItemBinding.inflate(LayoutInflater.from(context));
        return new ProductCarritoViewHolder(productItemBinding);
    }


    //onbindviewholder recorre el ciclo del arraylist
    @Override
    public void onBindViewHolder(@NonNull ProductCarritoViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        Toast.makeText(context, product.getId(), Toast.LENGTH_SHORT).show();

        holder.itemBinding.NombreFroducto.setText(product.getNombre());
        holder.itemBinding.Descripcion.setText(product.getDescripcion());
        holder.itemBinding.Precio.setText(product.getPrecio());
        holder.itemBinding.BtnAAdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteProduct(product);


            }
        });
        url = product.getImagen();
        Glide.with(context).load(url).into(holder.itemBinding.Imagen);

    }


    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductCarritoViewHolder extends RecyclerView.ViewHolder {

        CarritoItemBinding itemBinding;
        public ProductCarritoViewHolder(@NonNull CarritoItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }


    public void deleteProduct(Product product){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.5/amazon_clone/ApiRest/Carrito/delete_carrito.php", new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Producto eliminado")) {

                    Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(context, "no se pudo eliminar", Toast.LENGTH_SHORT).show();
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


                return map;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(context);
        queue.add(request);
    }


}
