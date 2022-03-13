 package com.example.amazon_clone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.amazon_clone.databinding.ActivitySingUpBinding;

import java.util.HashMap;
import java.util.Map;

 public class Sing_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySingUpBinding javaBinding = ActivitySingUpBinding.inflate(getLayoutInflater());
        setContentView(javaBinding.getRoot());

        javaBinding.btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registro(javaBinding.etnombre.getText().toString(), javaBinding.etemail.getText().toString(),javaBinding.etpassword.getText().toString());
            }
        });
    }
        public void  registro(final String nombre, final String email,final String password){
           ActivitySingUpBinding javaBinding = ActivitySingUpBinding.inflate(getLayoutInflater());

            if (nombre.isEmpty()){
                javaBinding.etnombre.setError("Complete los campos");
                return;
            }else if (email.isEmpty()){
                javaBinding.etemail.setError("complete los campos");
            }else {


                //====== CAMBIAR IP DEPENDE DE LA UBICACION ====


                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.7/amazon_clone/insertar.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("registro correctamente")) {
                            javaBinding.etnombre.setText("");
                            javaBinding.etemail.setText("");
                            javaBinding.etpassword.setText("");
                            Toast.makeText(Sing_up.this, "datos insertados", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Sing_up.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Sing_up.this, response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Sing_up.this, "no se pudo insertar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        javaBinding.etnombre.setText("");
                        javaBinding.etemail.setText("");
                        javaBinding.etpassword.setText("");
                        Toast.makeText(Sing_up.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> map= new HashMap<String, String>();
                        map.put("nombre",nombre);
                        map.put("email",email);
                        map.put("password",password);
                        return map;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Sing_up.this);
                queue.add(request);
            }
        }
 }

