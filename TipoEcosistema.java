/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
public class TipoEcosistema {
    private final String nombre;
    private final String biodiversidad;
    private final double altitudPromedio;

    public TipoEcosistema(String nombre, double temp, double lluvia, String bio, double altitud) {
        this.nombre = nombre;
        this.biodiversidad = bio;
        this.altitudPromedio = altitud;
    }

    public void mostrarInfoTipo() {
        System.out.println("Tipo de Ecosistema: " + nombre);
        System.out.println("Biodiversidad: " + biodiversidad + " | Altitud: " + altitudPromedio + " m");
    }
}
