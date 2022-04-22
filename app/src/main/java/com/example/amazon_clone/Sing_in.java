package com.example.amazon_clone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_clone.Adapters.ProductModel;
import com.example.amazon_clone.databinding.ActivitySingInBinding;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Sing_in extends AppCompatActivity {

    ArrayList <ProductModel> myappmodelarraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // creo un objeto del view binding
        ActivitySingInBinding jb = ActivitySingInBinding.inflate(getLayoutInflater());
        setContentView(jb.getRoot());
        //creo un evento con el boton continuar
        jb.btncontinuar.setOnClickListener(new View.OnClickListener() {

         // se crea la funcion "onClick", la cual hace la peticon al momento de dar click en el boton continuar

            @Override
            public void onClick(View view) {
                //se crea un objeto en el que se almacenaran los datos a enviar


                HashMap data = new HashMap();


                // .put nos sirve para guardar un valor especifico y lo
                // asocia con una clave especifica en el mapa o mapeo
                data.put("email",jb.etcorreo.getText().toString());

                //funciona asi " objeto.put(1, 2);
                //1. objeto - es la referencia o key, .put asocia el valor con una key especifica en el mapa
                //2. valor - es el contenido que vamos a almacenar en el .put

                data.put("password",jb.etpassword.getText().toString());

                // almacenamos la direccion url en la variable "url"

                String url = "http://172.20.10.4/amazon_clone/ApiRest/features/login/login1.php";

                //por ultimo llamamos  la funcion y le mandamos por parametros el email y el password
                //la url y los datos guardados en la variable "data" del hasmap
                inicio(jb.etcorreo.getText().toString(),jb.etpassword.getText().toString(),url,data);
            }
        });
    }

    //funcion llamada "inicio" la cual hara la peticion y recibira la respuesta de la API
    //recibe por parametros email, password, url y el mapeo de los datos almacenados anteriormente
     public void inicio (final String email,final String pass,final String url, HashMap data){

        //se crea un objeto que se utilizara para accionar la peticion
         // el requestqueue viene de la biblioteca "volley" la cual estamos utilizando
         //para hacer las peticiones
         RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());

         //se crea un objeto el cual va a almacenar todos los datos de la peticion, tiene
         //parecido con las promesas en javascript
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
                @Override
                    //esta funcion se utilizara para tratar la respuesta que dara la API
                //la API va a retornar los datos que necesitamos para hacer la validacion
                public void onResponse(JSONObject response) {
                    // se crea una variable llamada " response" en esta se almacena la respuesta de
                    // los datos que retorno la API

                    //el try y el catch nos ayudaran a que la app continue funcionando
                    // dado el caso de que los datos que retorno la API sean diferentes a los esperados
                    //(que la API retorne un error)
                    try {
                       String password;
                        //se crea una variable que va a almacenar el valor de password el cual
                        //esta en la variable de la respuesta de la API(response)
                        password = response.getString("password");

                        //se crea cun condicionl para confimar que la password que
                        // esta en la base de datos sea igual a la que el usuario dijito en
                        // el campo de password
                      if (email.equals(email)&&pass.equals(password)){

                          //si esta valido, la accion staractivity y Intent  nos ayudaran a cambiar
                          //de clase (se pasa al home)
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                          //si la contraseña es incorrecta, se envia un aviso de que los datos estan invalidos
                            Toast.makeText(Sing_in.this, "error datos invalidos", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception error){
                        //si la API responde algo diferente a lo esperado
                        //el catch nos ayudara para dar aviso a que los datos estan invalidos
                        Toast.makeText(Sing_in.this, "error datos invalidos", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                //la funcion onErrorResponse se accionara dado el caso de que haya un error en la API
                @Override
                public void onErrorResponse(VolleyError error) {
                    //damos un aviso de el error que esta pasando en la api
                    Toast.makeText(Sing_in.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            //aqui realizamos un llamado para accionar el requerimiento y dentro de el
         //enviamos la solicitud o peticion de los datos que va a recibir la API
            requstQueue.add(request);

        }
}


