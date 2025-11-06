/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import java.io.Serializable;
import java.util.Random;

public class Clima implements Serializable {
    private static final long serialVersionUID = 1L;
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
        
        // Asegurar que los valores estén en rangos razonables
        if (temperatura < -10) temperatura = -10;
        if (temperatura > 45) temperatura = 45;
        if (humedad < 0) humedad = 0;
        if (humedad > 100) humedad = 100;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getEstacion() {
        return estacion;
    }

    public boolean isLluvia() {
        return this.humedad > 70.0;
    }

    public double getTemperatura() {
        return this.temperatura;
    }

    public double getHumedad() {
        return this.humedad;
    }

    @Override
    public String toString() {
        return String.format("Temperatura: %.1f°C\nHumedad: %.1f%%\nEstación: %s", 
                           temperatura, humedad, estacion);
    }
}