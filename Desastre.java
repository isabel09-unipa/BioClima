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

public class Desastre implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String tipo;
    private final double intensidad;
    private final int duracion;

    public Desastre(String tipo, double intensidad, int duracion) {
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.duracion = duracion;
    }

    public void aplicarEfecto(Ecosistema eco) {
        System.out.println("⚠ Desastre: " + tipo + " (intensidad " + intensidad + ")");
        eco.afectarPorDesastre(intensidad);
    }

    public static Desastre generarAleatorio() {
        String[] tipos = {"Tormenta", "Sequía", "Incendio", "Inundación"};
        Random r = new Random();
        String tipo = tipos[r.nextInt(tipos.length)];
        double intensidad = 1 + r.nextDouble() * 3;
        int duracion = 1 + r.nextInt(5);
        return new Desastre(tipo, intensidad, duracion);
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public double getIntensidad() {
        return intensidad;
    }
    
    public int getDuracion() {
        return duracion;
    }
}