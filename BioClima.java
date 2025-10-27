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
                Este programa permite observar como los factores del clima influyen 
                en el comportamiento y energia de los seres vivos dentro de un ambiente virtual.
                
                Podras realizar diferentes acciones como consultar el estado del clima, 
                cambiar la estacion del anio, agregar o eliminar seres vivos, 
                y simular el paso de varios dias para estudiar los cambios en el ecosistema.
                
                Tambien puedes guardar los datos del ecosistema para continuar 
                tu simulacion en otro momento.
                """);

            System.out.print("Deseas comenzar la simulacion? (S/N): ");
            String continuar = sc.nextLine().trim();

            if (!continuar.equalsIgnoreCase("S")) {
                System.out.println("\nHas decidido salir del programa. Gracias por visitar BioClima.");
                return;
            }

            Ecosistema eco = Ecosistema.cargar(); // Carga desde archivo si existe
            boolean salir = false;

            // Ciclo principal del menu
            do {
                System.out.println("\n==================================");
                System.out.println("           MENU PRINCIPAL");
                System.out.println("==================================");
                System.out.println("1. Ver informacion general del ecosistema");
                System.out.println("2. Consultar estado del clima");
                System.out.println("3. Cambiar estacion del anio");
                System.out.println("4. Administrar seres vivos");
                System.out.println("5. Avanzar el tiempo (simular varios dias)");
                System.out.println("6. Guardar cambios del ecosistema");
                System.out.println("7. Salir del programa");
                System.out.print("Selecciona una opcion: ");

                String input = sc.nextLine().trim();
                int opcion;

                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Error: debes ingresar un numero valido.");
                    continue;
                }

                switch (opcion) {

                    case 1 -> {
                        System.out.println("\n--- INFORMACION DEL ECOSISTEMA ---");
                        eco.mostrarInformacion();
                    }

                    case 2 -> {
                        System.out.println("\n--- ESTADO ACTUAL DEL CLIMA ---");
                        eco.getClima().mostrarClima();
                    }

                    case 3 -> {
                        System.out.print("\nIngrese la nueva estacion (Primavera, Verano, Otono, Invierno): ");
                        String estacion = sc.nextLine().trim();
                        eco.getClima().setEstacion(estacion);
                        System.out.println("La estacion actual ha sido actualizada a: " + estacion + ".");
                    }

                    case 4 -> {
                        System.out.println("\n--- ADMINISTRACION DE SERES VIVOS ---");
                        System.out.println("1. Agregar un nuevo ser vivo");
                        System.out.println("2. Eliminar un ser vivo");
                        System.out.print("Selecciona una opcion: ");
                        String subOpcion = sc.nextLine().trim();

                    switch (subOpcion) {
                        case "1" ->                             {
                                System.out.print("Tipo (Animal/Planta): ");
                                String tipo = sc.nextLine().trim();
                                if (!tipo.equalsIgnoreCase("animal") && !tipo.equalsIgnoreCase("planta")) {
                                    System.out.println("Tipo invalido. Usa 'Animal' o 'Planta'.");
                                    continue;
                                }   System.out.print("Nombre: ");
                                String nombre = sc.nextLine().trim();
                                System.out.print("Energia inicial: ");
                                double energia;
                                try {
                                    energia = Double.parseDouble(sc.nextLine().trim());
                                    if (energia < 0) {
                                        System.out.println("Energia negativa, se usara valor por defecto (50).");
                                        energia = 50;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Valor invalido. Se usara energia por defecto (50).");
                                    energia = 50;
                                }   SerVivo nuevo;
                                if (tipo.equalsIgnoreCase("animal")) {
                                    nuevo = new SerVivo(nombre, energia) {
                                        @Override
                                        public void reaccionar(Clima clima) {
                                            if (clima.isLluvia()) energia -= 5;
                                            else energia += 5;
                                            energia -= clima.getViento() / 10;
                                            System.out.println(nombre + " (Animal) energia: " + energia);
                                        }
                                    };
                                } else {
                                    nuevo = new SerVivo(nombre, energia) {
                                        @Override
                                        public void reaccionar(Clima clima) {
                                            if (clima.isLluvia()) energia += 8;
                                            else energia -= 3;
                                            energia -= clima.getViento() / 20;
                                            System.out.println(nombre + " (Planta) energia: " + energia);
                                        }
                                    };
                                }   eco.agregar(nuevo);
                                System.out.println("El nuevo ser vivo ha sido agregado correctamente.");
                            }
                        case "2" ->                             {
                                System.out.print("Escribe el nombre del ser vivo a eliminar: ");
                                String nombre = sc.nextLine().trim();
                                boolean eliminado = eco.eliminar(nombre);
                                if (eliminado)
                                    System.out.println("El ser vivo '" + nombre + "' fue eliminado.");
                                else
                                    System.out.println("No se encontro ningun ser vivo con ese nombre.");
                            }
                        default -> System.out.println("Opcion invalida en el submenu.");
                    }
                    }

                    case 5 -> {
                        System.out.print("\nCuantos dias deseas simular?: ");
                        int dias;
                        try {
                            dias = Integer.parseInt(sc.nextLine().trim());
                            if (dias <= 0) {
                                System.out.println("El numero de dias debe ser mayor que cero.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Debes ingresar un numero valido.");
                            continue;
                        }

                        for (int i = 1; i <= dias; i++) {
                            System.out.println("\n--- Dia " + i + " ---");
                            eco.simularDia();
                        }

                        System.out.println("\nLa simulacion de " + dias + " dias ha finalizado.");
                    }

                    case 6 -> {
                        System.out.println("\nGuardando informacion del ecosistema...");
                        eco.guardar();
                        System.out.println("Datos guardados correctamente. Podras continuar mas tarde.");
                    }

                    case 7 -> {
                        System.out.println("\nFin del programa. Gracias por usar BioClima.");
                        salir = true;
                    }

                    default -> System.out.println("Opcion no valida, intenta nuevamente.");
                }

            } while (!salir);
        }
    }
}


