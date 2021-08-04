package com.nallis.clubanimals.views.Objetos;

import java.util.ArrayList;

public class Veterinaria {
    String nombre;
    String direccion;
    String s01;

    public Veterinaria(){
    }
    public Veterinaria(String nombre, String direccion, String s01){
        this.nombre = nombre;
        this.direccion = direccion;
        this.s01 = s01;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getS01(){
        return s01;
    }
    public void setS01(String s01){
        this.s01 = s01;
    }

}
