/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import java.io.*;
import java.util.ArrayList;

public class Ecosistema implements Serializable {
    private final Clima clima;
    private final ArrayList<SerVivo> seres = new ArrayList<>();

    public Ecosistema(Clima clima) { 
        this.clima = clima; 
    }

    public void agregar(SerVivo s) {
        seres.add(s);
        System.out.println("Se agregó " + s.getNombre());
    }

    public void simularDia() {
        System.out.println("\nInicia un nuevo día en el ecosistema...");
        clima.simularCambioDiario();
        clima.mostrarClima();

        for (SerVivo s : seres) {
            s.reaccionar(clima);
        }

        mostrarEstadoEcosistema();
    }

    public void mostrarEstadoEcosistema() {
        System.out.println("\nEstado general del ecosistema:");
        for (SerVivo s : seres) {
            String estado;
            if (s.getEnergia() > 80) {
                estado = "Saludable";
            } else if (s.getEnergia() > 40) {
                estado = "Estable";
            } else {
                estado = "Débil";
            }
            System.out.printf(" - %s → Energía: %.1f → %s%n", s.getNombre(), s.getEnergia(), estado);
        }
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ecosistema.dat"))) {
            oos.writeObject(this);
            System.out.println("Ecosistema guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el ecosistema.");
        }
    }

    public static Ecosistema cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ecosistema.dat"))) {
            System.out.println("Ecosistema cargado desde archivo.");
            return (Ecosistema) ois.readObject();
        } catch (Exception e) {
            System.out.println("No hay datos previos. Se crea un ecosistema nuevo.");
            return new Ecosistema(new Clima("primavera"));
        }
    }

    public Clima getClima() { 
        return clima; 
    }
}
