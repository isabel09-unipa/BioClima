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

public class GestorDatos {
    private static final String ARCHIVO = "ecosistema.dat";

    public static void guardar(Ecosistema eco) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(eco);
            System.out.println("Ecosistema guardado en " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Ecosistema cargar() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
                Ecosistema eco = (Ecosistema) ois.readObject();
                System.out.println("Ecosistema cargado desde archivo");
                return eco;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar: " + e.getMessage() + ". Creando nuevo ecosistema...");
            }
        }
        
        System.out.println("Creando nuevo ecosistema...");
        Clima climaBase = new Clima(25.0, 70.0, "Verano");
        TipoEcosistema tipoBase = new TipoEcosistema("Selva Tropical", 27, 3000, "Alta", 200);
        return new Ecosistema("Amazonas", climaBase, tipoBase);
    }
}