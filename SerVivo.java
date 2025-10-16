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

public abstract class SerVivo implements Serializable {
    protected String nombre;
    protected double energia;

    // Constructor con un solo parámetro: asigna energía por defecto
    public SerVivo(String nombre) {
        this(nombre, 50); // energía inicial predeterminada
    }

    // Constructor con nombre y energía
    public SerVivo(String nombre, double energia) {
        this.nombre = nombre;
        this.energia = energia;
    }

    // Métodos getter

    /**
     *
     * @return
     */
    public String getNombre() { return nombre; }
    public double getEnergia() { return energia; }

    // Método abstracto para el comportamiento polimórfico
    public abstract void reaccionar(Clima clima);
}


