/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private final Ecosistema eco;
    private JTextArea areaInfo;
    private JButton btnInfo, btnClima, btnEstacion, btnAgregar, btnEliminar, btnSimular, btnGuardar, btnSalir;

    public VentanaPrincipal() {
        eco = Ecosistema.cargar();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.add(new JLabel(" SIMULADOR BIOCLIMA "));
        add(panelTitulo, BorderLayout.NORTH);
        
        areaInfo = new JTextArea(20, 50);
        areaInfo.setEditable(false);
        areaInfo.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(areaInfo);
        add(scroll, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 5, 5));
        
        btnInfo = new JButton(" Información");
        btnClima = new JButton("️ Estado Clima");
        btnEstacion = new JButton(" Cambiar Estación");
        btnAgregar = new JButton(" Agregar Ser Vivo");
        btnEliminar = new JButton(" Eliminar Ser Vivo");
        btnSimular = new JButton(" Simular Días");
        btnGuardar = new JButton(" Guardar");
        btnSalir = new JButton(" Salir");
        
        // Agregar todos los botones al panel
        panelBotones.add(btnInfo);
        panelBotones.add(btnClima);
        panelBotones.add(btnEstacion);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSimular);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnSalir);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        configurarEventos();
    }
    
    private void configurarEventos() {
        btnInfo.addActionListener(e -> mostrarInformacion());
        btnClima.addActionListener(e -> mostrarClima());
        btnEstacion.addActionListener(e -> cambiarEstacion());
        btnAgregar.addActionListener(e -> agregarSerVivo());
        btnEliminar.addActionListener(e -> eliminarSerVivo());
        btnSimular.addActionListener(e -> simularDias());
        btnGuardar.addActionListener(e -> guardarEcosistema());
        btnSalir.addActionListener(e -> confirmarSalida());
    }
    
    private void mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DEL ECOSISTEMA ===\n\n");
        info.append("Ecosistema: Amazonas\n");
        info.append("Seres vivos registrados: ").append(eco.getCantidadSeresVivos()).append("\n\n");
        
        info.append("--- CLIMA ACTUAL ---\n");
        info.append(eco.getClima().toString()).append("\n");
        
        areaInfo.setText(info.toString());
    }
    
    private void mostrarClima() {
        areaInfo.setText("=== ESTADO DEL CLIMA ===\n\n" + eco.getClima().toString());
    }
    
    private void cambiarEstacion() {
        String[] estaciones = {"Primavera", "Verano", "Otoño", "Invierno"};
        String estacion = (String) JOptionPane.showInputDialog(this,
                "Selecciona la nueva estación:",
                "Cambiar Estación",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estaciones,
                estaciones[0]);
        
        if (estacion != null) {
            eco.getClima().setEstacion(estacion);
            areaInfo.setText("✅ Estación cambiada a: " + estacion);
        }
    }
    
    private void agregarSerVivo() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Tipo:"));
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Animal", "Planta"});
        panel.add(comboTipo);
        
        panel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        panel.add(txtNombre);
        
        panel.add(new JLabel("Energía:"));
        JTextField txtEnergia = new JTextField("50");
        panel.add(txtEnergia);
        
        panel.add(new JLabel("Especie:"));
        JTextField txtEspecie = new JTextField();
        panel.add(txtEspecie);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
                "Agregar Ser Vivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String tipo = comboTipo.getSelectedItem().toString();
                String nombre = txtNombre.getText().trim();
                double energia = Double.parseDouble(txtEnergia.getText());
                String especie = txtEspecie.getText().trim();
                
                if (nombre.isEmpty() || especie.isEmpty()) {
                    JOptionPane.showMessageDialog(this, " Nombre y especie son obligatorios", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (tipo.equals("Animal")) {
                    String[] alimentaciones = {"Carnívoro", "Herbívoro", "Omnívoro"};
                    String alimentacion = (String) JOptionPane.showInputDialog(this,
                            "Selecciona el tipo de alimentación:",
                            "Tipo de Alimentación",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            alimentaciones,
                            alimentaciones[0]);
                    
                    if (alimentacion != null) {
                        Animal animal = new Animal(nombre, energia, especie, alimentacion);
                        eco.agregar(animal);
                        areaInfo.setText(" Animal '" + nombre + "' agregado exitosamente\n\n" +
                                       "Especie: " + especie + "\n" +
                                       "Energía: " + energia + "\n" +
                                       "Alimentación: " + alimentacion);
                    }
                } else {
                    Planta planta = new Planta(nombre, energia, especie);
                    eco.agregar(planta);
                    areaInfo.setText(" Planta '" + nombre + "' agregada exitosamente\n\n" +
                                   "Especie: " + especie + "\n" +
                                   "Energía: " + energia);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, " La energía debe ser un número válido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarSerVivo() {
        String nombre = JOptionPane.showInputDialog(this, 
                "Nombre del ser vivo a eliminar:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            boolean eliminado = eco.eliminar(nombre.trim());
            if (eliminado) {
                areaInfo.setText(" Ser vivo '" + nombre + "' eliminado exitosamente");
            } else {
                areaInfo.setText(" No se encontró ningún ser vivo con el nombre: '" + nombre + "'");
            }
        }
    }
    
    private void simularDias() {
        String input = JOptionPane.showInputDialog(this, 
                "¿Cuántos días deseas simular?");
        if (input != null && !input.trim().isEmpty()) {
            try {
                int dias = Integer.parseInt(input.trim());
                if (dias <= 0) {
                    JOptionPane.showMessageDialog(this, " El número de días debe ser mayor que cero");
                    return;
                }
                
                if (dias > 50) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Estás seguro de simular " + dias + " días? Esto puede tomar un tiempo.",
                            "Confirmar simulación larga",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                
                StringBuilder resultado = new StringBuilder();
                resultado.append("=== SIMULACIÓN DE ").append(dias).append(" DÍAS ===\n\n");
                
                for (int i = 1; i <= dias; i++) {
                    resultado.append("--- Día ").append(i).append(" ---\n");
                    eco.simularDia();
                }
                
                resultado.append("\n Simulación completada exitosamente");
                areaInfo.setText(resultado.toString());
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, " Debes ingresar un número válido");
            }
        }
    }
    
    private void guardarEcosistema() {
        eco.guardar();
        areaInfo.setText("""
                          Ecosistema guardado exitosamente
                         
                         Los datos han sido almacenados y podr\u00e1n ser recuperados en la pr\u00f3xima sesi\u00f3n.""");
    }
    
    private void confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres salir del simulador?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            // Preguntar si quiere guardar antes de salir
            int guardar = JOptionPane.showConfirmDialog(this,
                    "¿Deseas guardar el ecosistema antes de salir?",
                    "Guardar antes de salir",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            if (guardar == JOptionPane.YES_OPTION) {
                eco.guardar();
                JOptionPane.showMessageDialog(this, "✅ Ecosistema guardado exitosamente");
            }
            
            if (guardar != JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            }
        }
    }
    
    private void configurarVentana() {
        setTitle("Simulador BioClima - Control de Ecosistemas");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmarSalida();
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
  public static void main(String[] args) {
    // Establecer el look and feel del sistema - CORREGIDO
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | 
             IllegalAccessException | UnsupportedLookAndFeelException e) {
        // Si hay error, usar el look and feel por defecto
        System.err.println("No se pudo cargar el look and feel del sistema: " + e.getMessage());
        // No es crítico, la aplicación puede continuar
    }
    
    SwingUtilities.invokeLater(() -> {
        try {
        } catch (Exception e) {
            System.err.println("Error al crear la ventana principal: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar la aplicación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    });
}
  }