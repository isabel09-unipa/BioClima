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

public class Planta extends SerVivo implements Serializable {
    private static final long serialVersionUID = 1L;

    public Planta(String nombre, double energia, String tipoPlanta) {
        super(nombre, energia, "Planta", tipoPlanta);
    }

    @Override
    public void ajustarEnergia(Clima clima) {
        if (clima.isLluvia()) energia += 8;
        else energia -= 3;
        energia -= clima.getTemperatura() / 60;
        
        if (clima.getEstacion().equalsIgnoreCase("Invierno")) energia -= 1;
        else energia += 2;
        
        if (energia < 0) energia = 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Tipo: Planta]";
    }
}