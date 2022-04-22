package com.example.amazon_clone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazon_clone.Models.Product;
import com.example.amazon_clone.databinding.ProductItemBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ProductItemBinding productItemBinding;
    private ArrayList<Product> productArrayList;
    private String url;

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
        holder.itemBinding.BtnAAdir.setOnClickListener(view ->
                Toast.makeText(context, product.getId(), Toast.LENGTH_SHORT).show());

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
}
