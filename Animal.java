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

public class Animal extends SerVivo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String tipoAlimentacion;

    public Animal(String nombre, double energia, String especie, String tipoAlimentacion) {
        super(nombre, energia, "Animal", especie);
        this.tipoAlimentacion = tipoAlimentacion;
    }

    @Override
    public void ajustarEnergia(Clima clima) {
        if (clima.isLluvia()) energia -= 5;
        else energia += 5;
        energia -= clima.getTemperatura() / 40;
        
        if (clima.getEstacion().equalsIgnoreCase("Verano")) energia -= 3;
        
        if (energia < 0) energia = 0;
    }

    public String getTipoAlimentacion() {
        return tipoAlimentacion;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Alimentación: " + tipoAlimentacion + "]";
    }
}