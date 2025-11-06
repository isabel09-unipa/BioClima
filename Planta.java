/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
public class Planta extends SerVivo {

    public Planta(String nombre, double energia, String tipoPlanta) {
        super(nombre, energia, "Planta", tipoPlanta);
    }

    @Override
    public void ajustarEnergia(Clima clima) {
        if (clima.getEstacion().equalsIgnoreCase("Invierno")) energia -= 1;
        else energia += 2; // gana energía con luz solar
    }
}
