package com.example.pmrtask;

public class Pasajeros
{
    String nombre;
    String vuelo;
    String info;
    String userEmail;
    public Pasajeros(){

    }
    public Pasajeros(String nombre, String vuelo, String info, String userEmail) {
        this.nombre = nombre;
        this.vuelo = vuelo;
        this.info = info;
        this.userEmail=userEmail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVuelo() {
        return vuelo;
    }

    public String getInfo() {
        return info;
    }
    public String getUserEmail() {
        return userEmail;
    }
}

