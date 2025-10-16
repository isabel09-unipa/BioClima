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
import java.io.Serializable;

public final class Clima implements Serializable {
    private double temperatura;
    private double humedad;
    private boolean lluvia;
    private double viento;
    private String estacion;

    public Clima(String estacion) {
        this.estacion = estacion;
        ajustarPorEstacion();
    }

    public void ajustarPorEstacion() {
        switch (estacion.toLowerCase()) {
            case "verano" -> { temperatura = 35; humedad = 50; lluvia = false; viento = 10; }
            case "invierno" -> { temperatura = 15; humedad = 70; lluvia = true; viento = 25; }
            case "otoño" -> { temperatura = 22; humedad = 60; lluvia = new Random().nextBoolean(); viento = 15; }
            case "primavera" -> { temperatura = 25; humedad = 65; lluvia = true; viento = 10; }
            default -> { temperatura = 25; humedad = 60; lluvia = false; viento = 10; }
        }
    }

    public void simularCambioDiario() {
        Random r = new Random();
        temperatura += r.nextDouble(-2, 2);
        humedad += r.nextDouble(-3, 3);
        viento += r.nextDouble(-1, 1);
        lluvia = r.nextInt(5) == 0; // 20% de probabilidad
    }

    public void mostrarClima() {
        System.out.printf("🌦️ Estación: %s | Temp: %.1f°C | Humedad: %.0f%% | Viento: %.1f km/h | Lluvia: %s%n",
                estacion, temperatura, humedad, viento, lluvia ? "Sí" : "No");
    }

    // Getters y setters
    public double getTemperatura() { return temperatura; }
    public boolean isLluvia() { return lluvia; }
    public double getViento() { return viento; }
    public void setEstacion(String estacion) { 
        this.estacion = estacion; 
        ajustarPorEstacion(); 
    }
}
