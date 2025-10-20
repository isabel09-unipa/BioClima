/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import java.util.Scanner;

public class BioClima {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Ecosistema eco = Ecosistema.cargar(); // Carga desde archivo si existe
            boolean salir = false;
            
            do {
                System.out.println("\n=== Bienvenidos a BioClima ===");
                System.out.println("1. Mostrar clima actual");
                System.out.println("2. Cambiar estaciones del año");
                System.out.println("3. Agregar seres vivos");
                System.out.println("4. Simular un día");
                System.out.println("5. Guardar ecosistema");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                
                String inputOpcion = sc.nextLine().trim();
                int opcion;
                
                try {
                    opcion = Integer.parseInt(inputOpcion);
                } catch (NumberFormatException e) {
                    System.out.println("Debes ingresar un número válido.");
                    continue;
                }
                
                switch (opcion) {
                    case 1 -> eco.getClima().mostrarClima();
                    
                    case 2 -> {
                        System.out.print("Ingrese estación que quiere (Primavera, Verano, Otoño, Invierno): ");
                        String estacion = sc.nextLine().trim();
                        eco.getClima().setEstacion(estacion);
                        System.out.println("Estación actualizada a " + estacion);
                    }
                    
                    case 3 -> {
                        System.out.print("Tipo (Animal/Planta): ");
                        String tipo = sc.nextLine().trim();
                        
                        if (!tipo.equalsIgnoreCase("animal") && !tipo.equalsIgnoreCase("planta")) {
                            System.out.println("Tipo inválido. Usa 'Animal' o 'Planta'.");
                            continue;
                        }
                        
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine().trim();
                        
                        System.out.print("Energía inicial: ");
                        double energia;
                        try {
                            energia = Double.parseDouble(sc.nextLine().trim());
                            if (energia < 0) {
                                System.out.println("Energía negativa, se usará valor por defecto (50).");
                                energia = 50;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Energía inválida, se usará valor por defecto (50).");
                            energia = 50;
                        }
                        
                        // Polimorfismo mediante clases anónimas
                        SerVivo nuevo;
                        if (tipo.equalsIgnoreCase("animal")) {
                            nuevo = new SerVivo(nombre, energia) {
                                @Override
                                public void reaccionar(Clima clima) {
                                    if (clima.isLluvia()) energia -= 5;
                                    else energia += 5;
                                    energia -= clima.getViento() / 10;
                                    System.out.println(nombre + " (Animal) energía: " + energia);
                                }
                            };
                        } else {
                            nuevo = new SerVivo(nombre, energia) {
                                @Override
                                public void reaccionar(Clima clima) {
                                    if (clima.isLluvia()) energia += 8;
                                    else energia -= 3;
                                    energia -= clima.getViento() / 20;
                                    System.out.println(nombre + " (Planta) energía: " + energia);
                                }
                            };
                        }
                        
                        eco.agregar(nuevo);
                    }
                    
                    case 4 -> eco.simularDia();
                    
                    case 5 -> eco.guardar();
                    
                    case 6 -> {
                        System.out.println("Fin de la simulación. Hasta la próxima estación.");
                        salir = true;
                    }
                    
                    default -> System.out.println("Opción no válida, intenta nuevamente.");
                }
                
            } while (!salir);
        } // Carga desde archivo si existe
    }
}

