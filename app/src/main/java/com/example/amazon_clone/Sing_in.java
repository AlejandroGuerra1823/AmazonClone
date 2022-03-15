package com.example.amazon_clone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_clone.databinding.ActivitySingInBinding;

import java.util.HashMap;
import java.util.Map;

public class Sing_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySingInBinding jb = ActivitySingInBinding.inflate(getLayoutInflater());
        setContentView(jb.getRoot());

        jb.btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicio(jb.etcorreo.getText().toString(),jb.etpassword.getText().toString());
            }
        });
    }

        public void inicio (final String email,final String pass){
        ActivitySingInBinding jb = ActivitySingInBinding.inflate(getLayoutInflater());

            String emaail  = jb.etcorreo.getText().toString().trim();
            String password  = jb.etpassword.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(this, "ingrese correo Y contraseña", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()){
            Toast.makeText(this, "ingrese popo", Toast.LENGTH_SHORT).show();

            }else{


            StringRequest request = new StringRequest(Request.Method.POST,
                    "http://192.168.1.7/amazon_clone/login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("ingreso correctamente")) {
                        jb.etcorreo.setText("");
                        jb.etpassword.setText("");

                        startActivity(new Intent(getApplicationContext(), Home.class));
                    } else {
                        Toast.makeText(Sing_in.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Sing_in.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String>params= new HashMap<>();
                   params.put("email", emaail);
                   params.put("password", password);
                   return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Sing_in.this);
            requestQueue.add(request);

        }
        }


}