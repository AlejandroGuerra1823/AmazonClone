package com.example.amazon_clone.Models;

import android.media.Image;

import com.google.firebase.firestore.DocumentId;

public class Product {

@DocumentId
 private String id;
 private String nombre;
 private String descripcion;
 private String precio;
 private String imagen;


 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public String getDescripcion() {
  return descripcion;
 }

 public void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
 }

 public String getPrecio() {
  return precio;
 }

 public void setPrecio(String precio) {
  this.precio = precio;
 }

 public String getImagen() { return imagen; }

 public void setImagen(String imagen) { this.imagen = imagen; }

}
