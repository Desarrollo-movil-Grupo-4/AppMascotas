package com.nallis.clubanimals.views.Objetos;

public class Veterinaria {
    String nombre;
    String direccion;
    String s01;
    String s02;
    String s03;
    String servicio;

    public Veterinaria(){
    }
    public Veterinaria(String nombre, String direccion, String s01, String s02, String s03, String servicio){
        this.nombre = nombre;
        this.direccion = direccion;
        this.s01 = s01;
        this.s02 = s02;
        this.s03 = s03;
        this.servicio = servicio;
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
    public String getS02(){
        return s02;
    }
    public void setS02(String s02){
        this.s02 = s02;
    }
    public String getS03(){
        return s03;
    }
    public void setS03(String s03){
        this.s03 = s03;
    }

    public String Servicios (String servicio){
        return servicio;
    }
    public void setServicios (String servicio){
        this.servicio = servicio;
    }
}
