/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
public class Animal extends SerVivo {

    public Animal(String nombre, double energia, String especie, String tipoAlimentacion) {
        super(nombre, energia, "Animal", especie);
    }

    /**
     *
     * @param clima
     */
    @Override
    public void ajustarEnergia(Clima clima) {
        super.ajustarEnergia(clima);
        if (clima.getEstacion().equalsIgnoreCase("Verano")) energia -= 3;
    }
}
