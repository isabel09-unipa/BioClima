/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import java.util.Random;

public class Clima {
    private double temperatura;
    private double humedad;
    private String estacion;

    public Clima(double temperatura, double humedad, String estacion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.estacion = estacion;
    }

    public void mostrarClima() {
        System.out.println("Temperatura: " + temperatura + "°C");
        System.out.println("Humedad: " + humedad + "%");
        System.out.println("Estación: " + estacion);
    }

    public void cambiarClima() {
        Random r = new Random();
        temperatura += r.nextDouble() * 4 - 2;
        humedad += r.nextDouble() * 10 - 5;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getEstacion() {
        return estacion;
    }

    boolean isLluvia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getTemperatura() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
