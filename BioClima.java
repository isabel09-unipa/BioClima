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

            // Pantalla de bienvenida
            System.out.println("        SIMULADOR BIOCLIMA");
            System.out.println("==================================");
            System.out.println("""
                Bienvenido al simulador de ecosistemas BioClima.
                Este programa permite observar cómo los factores del clima influyen 
                en el comportamiento y energía de los seres vivos dentro de un ambiente virtual.
                
                Podrás realizar diferentes acciones como:
                - Consultar el estado del clima
                - Cambiar la estación del año
                - Agregar o eliminar seres vivos
                - Simular el paso de varios días
                - Guardar o cargar el ecosistema
                
                Explora la naturaleza digital y observa cómo evoluciona tu ecosistema.
                """);

            System.out.print("¿Deseas comenzar la simulacion? (S/N): ");
            String continuar = sc.nextLine().trim();

            if (!continuar.equalsIgnoreCase("S")) {
                System.out.println("\nHas decidido salir del programa. Gracias por visitar BioClima.");
                return;
            }

            Ecosistema eco = Ecosistema.cargar(); // Carga desde archivo si existe
            boolean salir = false;

            // Ciclo principal del menú
            do {
            
                System.out.println("           MENU PRINCIPAL");
            
                System.out.println("1. Ver información general del ecosistema");
                System.out.println("2. Consultar estado del clima");
                System.out.println("3. Cambiar estación del año");
                System.out.println("4. Administrar seres vivos");
                System.out.println("5. Avanzar el tiempo (simular varios días)");
                System.out.println("6. Guardar cambios del ecosistema");
                System.out.println("7. Salir del programa");
                System.out.print("Selecciona una opción: ");

                String input = sc.nextLine().trim();
                int opcion;

                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un número válido.");
                    continue;
                }

                switch (opcion) {
                    case 1 -> {
                        System.out.println("\n--- INFORMACIÓN DEL ECOSISTEMA ---");
                        eco.mostrarInformacion();
                    }

                    case 2 -> {
                        System.out.println("\n--- ESTADO ACTUAL DEL CLIMA ---");
                        eco.getClima().mostrarClima();
                    }

                    case 3 -> {
                        System.out.print("\nIngrese la nueva estación (Primavera, Verano, Otono, Invierno): ");
                        String estacion = sc.nextLine().trim();
                        eco.getClima().setEstacion(estacion);
                        System.out.println("La estación actual ha sido actualizada a: " + estacion + ".");
                    }

                    case 4 -> {
                        System.out.println("\n--- ADMINISTRACIÓN DE SERES VIVOS ---");
                        System.out.println("1. Agregar un nuevo ser vivo");
                        System.out.println("2. Eliminar un ser vivo");
                        System.out.print("Selecciona una opción: ");
                        String subOpcion = sc.nextLine().trim();

                        switch (subOpcion) {
                            case "1" -> {
                                System.out.print("Tipo (Animal/Planta): ");
                                String tipo = sc.nextLine().trim();

                                if (tipo.equalsIgnoreCase("animal")) {
                                    System.out.print("Especie (herbívoro, carnívoro, etc.): ");
                                    String especie = sc.nextLine().trim();
                                    System.out.print("Nombre: ");
                                    String nombre = sc.nextLine().trim();
                                    System.out.print("Tipo de alimentación: ");
                                    String alimentacion = sc.nextLine().trim();
                                    System.out.print("Energía inicial: ");
                                    double energia = leerEnergia(sc);
                                    Animal nuevo = new Animal(nombre, energia, especie, alimentacion);
                                    eco.agregar(nuevo);
                                    System.out.println("Animal agregado correctamente.");
                                } else if (tipo.equalsIgnoreCase("planta")) {
                                    System.out.print("Tipo de planta (flor, árbol, etc.): ");
                                    String especie = sc.nextLine().trim();
                                    System.out.print("Nombre: ");
                                    String nombre = sc.nextLine().trim();
                                    System.out.print("Energía inicial: ");
                                    double energia = leerEnergia(sc);
                                    Planta nueva = new Planta(nombre, energia, especie);
                                    eco.agregar(nueva);
                                    System.out.println("Planta agregada correctamente.");
                                } else {
                                    System.out.println("Tipo inválido. Usa 'Animal' o 'Planta'.");
                                }
                            }

                            case "2" -> {
                                System.out.print("Escribe el nombre del ser vivo a eliminar: ");
                                String nombre = sc.nextLine().trim();
                                boolean eliminado = eco.eliminar(nombre);
                                if (eliminado)
                                    System.out.println("El ser vivo '" + nombre + "' fue eliminado.");
                                else
                                    System.out.println("No se encontró ningún ser vivo con ese nombre.");
                            }

                            default -> System.out.println("Opción inválida en el submenú.");
                        }
                    }

                    case 5 -> {
                        System.out.print("\n¿Cuántos días deseas simular?: ");
                        int dias;
                        try {
                            dias = Integer.parseInt(sc.nextLine().trim());
                            if (dias <= 0) {
                                System.out.println("El número de días debe ser mayor que cero.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Debes ingresar un número válido.");
                            continue;
                        }

                        for (int i = 1; i <= dias; i++) {
                            System.out.println("\n--- Día " + i + " ---");
                            eco.simularDia();
                        }

                        System.out.println("\nLa simulación de " + dias + " días ha finalizado.");
                    }

                    case 6 -> {
                        System.out.println("\nGuardando información del ecosistema...");
                        eco.guardar();
                        System.out.println("Datos guardados correctamente. Podrás continuar más tarde.");
                    }

                    case 7 -> {
                        System.out.println("\nFin del programa. Gracias por usar BioClima.");
                        salir = true;
                    }

                    default -> System.out.println("Opción no valida, intenta nuevamente.");
                }

            } while (!salir);
        }
    }

    private static double leerEnergia(Scanner sc) {
        double energia;
        try {
            energia = Double.parseDouble(sc.nextLine().trim());
            if (energia < 0) {
                System.out.println("La energia no puede ser negativa, se usara el valor por defecto (50).");
                energia = 50;
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido. Se usará energia por defecto (50).");
            energia = 50;
        }
        return energia;
    }
}
