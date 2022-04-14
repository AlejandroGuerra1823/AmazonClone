package com.example.amazon_clone.Entities;

import android.media.Image;

public class Product {

 private String id;
 private String nombre;
 private String descripcion;
 private double precio;
 private Image imagen;

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

 public double getPrecio() {
  return precio;
 }

 public void setPrecio(double precio) {
  this.precio = precio;
 }

 public Image getImagen() {
  return imagen;
 }

 public void setImagen(Image imagen) {
  this.imagen = imagen;
 }
}
