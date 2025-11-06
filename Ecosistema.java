/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import java.util.ArrayList;
import java.util.List;

public class Ecosistema {
    private final String nombre;
    private final Clima clima;
    private final TipoEcosistema tipo;
    private final List<SerVivo> seresVivos;
    private final List<Desastre> desastres;

    public Ecosistema(String nombre, Clima clima, TipoEcosistema tipo) {
        this.nombre = nombre;
        this.clima = clima;
        this.tipo = tipo;
        this.seresVivos = new ArrayList<>();
        this.desastres = new ArrayList<>();
    }

    public static Ecosistema cargar() {
        // Se podría leer de archivo. Por ahora creamos uno base.
        Clima climaBase = new Clima(25.0, 70.0, "Verano");
        TipoEcosistema tipoBase = new TipoEcosistema("Selva Tropical", 27, 3000, "Alta", 200);
            return new Ecosistema("Amazonas", climaBase, tipoBase);
    }

    public void mostrarInformacion() {
        System.out.println("Ecosistema: " + nombre);
        tipo.mostrarInfoTipo();
        clima.mostrarClima();
        System.out.println("Seres vivos: " + seresVivos.size());
        for (SerVivo s : seresVivos) {
            System.out.println(" - " + s);
        }
    }

    public Clima getClima() {
        return clima;
    }

    public void agregar(SerVivo s) {
        seresVivos.add(s);
    }

    public boolean eliminar(String nombre) {
        return seresVivos.removeIf(s -> s.getNombre().equalsIgnoreCase(nombre));
    }

    public void simularDia() {
        clima.cambiarClima();
        for (SerVivo s : seresVivos) {
            s.ajustarEnergia(clima);
        }
        // 10% de probabilidad de desastre
        if (Math.random() < 0.1) {
            Desastre d = Desastre.generarAleatorio();
            desastres.add(d);
            d.aplicarEfecto(this);
        }
        System.out.println("Día simulado con éxito.");
    }

    public void afectarPorDesastre(double intensidad) {
        for (SerVivo s : seresVivos) {
            s.reducirEnergia(intensidad * 5);
        }
    }

    public void guardar() {
        GestorDatos.guardar(this);
    }
}
