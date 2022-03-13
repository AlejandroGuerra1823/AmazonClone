package com.example.amazon_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.amazon_clone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding javaBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(javaBinding.getRoot());

        javaBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sing_in.class);
                startActivity(intent);
            }
        });


         javaBinding.btnRegistration.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent( MainActivity.this, Sing_up.class);
                 startActivity(intent);
             }
         });
    }

}