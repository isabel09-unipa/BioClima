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
        // Verificar si se pasó argumento para interfaz gráfica
        if (args.length > 0 && args[0].equals("-gui")) {
            iniciarInterfazGrafica();
        } else {
            iniciarModoConsola();
        }
    }
    
    private static void iniciarInterfazGrafica() {
        System.out.println(" Iniciando BioClima en modo gráfico...");
        try {
            javax.swing.SwingUtilities.invokeLater(() -> {
                new VentanaPrincipal().setVisible(true);
            });
        } catch (Exception e) {
            System.err.println(" Error al iniciar interfaz gráfica: " + e.getMessage());
            System.out.println(" Iniciando en modo consola...");
            iniciarModoConsola();
        }
    }
    
    private static void iniciarModoConsola() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("        SIMULADOR BIOCLIMA");
            System.out.println("==================================");
            System.out.println("""
                Bienvenido al simulador de ecosistemas BioClima.
                
                Modo: CONSOLA
                
                Podrás realizar diferentes acciones como:
                - Consultar el estado del clima
                - Cambiar la estación del año
                - Agregar o eliminar seres vivos
                - Simular el paso de varios días
                - Guardar o cargar el ecosistema
                """);

            System.out.print("¿Deseas usar la interfaz gráfica en su lugar? (S/N): ");
            String usarGUI = sc.nextLine().trim();

            if (usarGUI.equalsIgnoreCase("S")) {
                iniciarInterfazGrafica();
                return;
            }

            System.out.print("¿Comenzar simulación en consola? (S/N): ");
            String continuar = sc.nextLine().trim();

            if (!continuar.equalsIgnoreCase("S")) {
                System.out.println("\nHas decidido salir del programa. Gracias por visitar BioClima.");
                return;
            }

            Ecosistema eco = Ecosistema.cargar();
            boolean salir = false;

            // Ciclo principal del menú (tu código original)
            do {
                System.out.println("\n           MENU PRINCIPAL");
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
                        System.out.print("\nIngrese la nueva estación (Primavera, Verano, Otoño, Invierno): ");
                        String estacion = sc.nextLine().trim();
                        eco.getClima().setEstacion(estacion);
                        System.out.println("La estación actual ha sido actualizada a: " + estacion + ".");
                    }
                    case 4 -> administrarSeresVivos(eco, sc);
                    case 5 -> simularDias(eco, sc);
                    case 6 -> {
                        System.out.println("\nGuardando información del ecosistema...");
                        eco.guardar();
                        System.out.println("Datos guardados correctamente.");
                    }
                    case 7 -> {
                        System.out.println("\nFin del programa. Gracias por usar BioClima.");
                        salir = true;
                    }
                    default -> System.out.println("Opción no válida, intenta nuevamente.");
                }
            } while (!salir);
        }
    }
    
    private static void administrarSeresVivos(Ecosistema eco, Scanner sc) {
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
                    System.out.print("Especie: ");
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
                    System.out.print("Tipo de planta: ");
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
    
    private static void simularDias(Ecosistema eco, Scanner sc) {
        System.out.print("\n¿Cuántos días deseas simular?: ");
        int dias;
        try {
            dias = Integer.parseInt(sc.nextLine().trim());
            if (dias <= 0) {
                System.out.println("El número de días debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número válido.");
            return;
        }

        for (int i = 1; i <= dias; i++) {
            System.out.println("\n--- Día " + i + " ---");
            eco.simularDia();
        }
        System.out.println("\nLa simulación de " + dias + " días ha finalizado.");
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