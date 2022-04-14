package com.example.amazon_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.amazon_clone.Adapters.ProductAdapter;
import com.example.amazon_clone.Entities.Product;
import com.example.amazon_clone.databinding.ActivityHomeBinding;
import com.example.amazon_clone.databinding.ActivitySingInBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding jb;
    private String url;
    ArrayList <Product> productArrayList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jb = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(jb.getRoot());
        url="";
        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productArrayList);
        jb.rvProducts.setHasFixedSize(true);
        jb.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        jb.rvProducts.setAdapter(productAdapter);







    }
}