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

public class SerVivo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;
    protected double energia;
    private final String tipo;
    private final String especie;

    public SerVivo(String nombre, double energia, String tipo, String especie) {
        this.nombre = nombre;
        this.energia = energia;
        this.tipo = tipo.toLowerCase();
        this.especie = especie;
    }

    public void reaccionar(Clima clima) {
        if (tipo.equals("animal")) {
            if (clima.isLluvia()) energia -= 5;
            else energia += 5;
            energia -= clima.getTemperatura() / 40;
            System.out.println(nombre + " (" + especie + " animal) energía: " + energia);
        } 
        else if (tipo.equals("planta")) {
            if (clima.isLluvia()) energia += 8;
            else energia -= 3;
            energia -= clima.getTemperatura() / 60;
            System.out.println(nombre + " (" + especie + " planta) energía: " + energia);
        }
        if (energia < 0) energia = 0;
    }

    public String getNombre() { return nombre; }
    public double getEnergia() { return energia; }
    public String getTipo() { return tipo; }
    public String getEspecie() { return especie; }

    public void mostrarDatos() {
        System.out.println(nombre + " (" + tipo + " - " + especie + ") Energía: " + energia);
    }

    public void ajustarEnergia(Clima clima) {
        if (tipo.equals("animal")) {
            if (clima.isLluvia()) energia -= 5;
            else energia += 5;
            energia -= clima.getTemperatura() / 40;
        } 
        else if (tipo.equals("planta")) {
            if (clima.isLluvia()) energia += 8;
            else energia -= 3;
            energia -= clima.getTemperatura() / 60;
        }
        if (energia < 0) energia = 0;
    }

    public void reducirEnergia(double cantidad) {
        this.energia -= cantidad;
        if (this.energia < 0) this.energia = 0;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + " - " + especie + ") Energía: " + energia;
    }
}