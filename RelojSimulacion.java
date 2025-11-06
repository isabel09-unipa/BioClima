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

public class RelojSimulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int diaActual = 1;
    private String estacionActual = "Primavera";

    public void avanzarDia() {
        diaActual++;
        if (diaActual % 90 == 0) cambiarEstacion();
    }

    private void cambiarEstacion() {
        switch (estacionActual) {
            case "Primavera" -> estacionActual = "Verano";
            case "Verano" -> estacionActual = "Otono";
            case "Otono" -> estacionActual = "Invierno";
            case "Invierno" -> estacionActual = "Primavera";
        }
        System.out.println("Cambio de estación: " + estacionActual);
    }

    public String getEstacionActual() {
        return estacionActual;
    }
}

