package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.gui.dialogos.*;
import com.mycompany.proyectoEscuderiasUnidas.persistence.DataPersistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Panel de gestión de carreras
 */
public class PanelCarreras extends JPanel {

    private ArrayList<Carrera> carreras;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Piloto> pilotos;
    private ArrayList<Auto> autos;
    private ArrayList<Pais> paises;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Mecanico> mecanicos;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelCarreras(ArrayList<Carrera> carreras, ArrayList<Circuito> circuitos,
                        ArrayList<Piloto> pilotos, ArrayList<Auto> autos,
                        ArrayList<Pais> paises, ArrayList<Escuderia> escuderias,
                        ArrayList<Mecanico> mecanicos, Registros sistema, JFrame parentFrame) {
        this.carreras = carreras;
        this.circuitos = circuitos;
        this.pilotos = pilotos;
        this.autos = autos;
        this.paises = paises;
        this.escuderias = escuderias;
        this.mecanicos = mecanicos;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Carreras", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Fecha", "Hora", "Circuito", "Vueltas", "Participantes"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTabla();

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCrear = new JButton("+ Crear Carrera");
        JButton btnInscribir = new JButton("+ Inscribir Piloto");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());

        btnCrear.addActionListener(e -> {
            DialogoCrearCarrera dialogo = new DialogoCrearCarrera(parentFrame, circuitos);
            dialogo.setVisible(true);

            Carrera nuevaCarrera = dialogo.getCarreraCreada();
            if (nuevaCarrera != null) {
                carreras.add(nuevaCarrera);
                sistema.agregarCarrera(nuevaCarrera);
                nuevaCarrera.getCircuito().agregarCarrera(nuevaCarrera);
                autoGuardar();
                actualizarTabla();
            }
        });

        btnInscribir.addActionListener(e -> {
            if (carreras.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debes crear al menos una carrera",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DialogoInscribirPiloto dialogo = new DialogoInscribirPiloto(parentFrame, carreras, pilotos, autos);
            dialogo.setVisible(true);

            if (dialogo.isInscripcionExitosa()) {
                autoGuardar();
                actualizarTabla();
            }
        });

        panelBotones.add(btnCrear);
        panelBotones.add(btnInscribir);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Carrera c : carreras) {
            modeloTabla.addRow(new Object[]{
                c.getNombre() != null ? c.getNombre() : "GP " + c.getCircuito().getnombre(),
                c.getFechaRealizacion(),
                c.getHoraRealizacion(),
                c.getCircuito().getnombre(),
                c.getNumeroVueltas(),
                c.getAutoPilotos().size()
            });
        }
    }

    private void autoGuardar() {
        try {
            DataPersistence.guardarTodo(paises, escuderias, pilotos, autos, circuitos, carreras, mecanicos);
        } catch (IOException ex) {
            System.err.println("Error al guardar automáticamente: " + ex.getMessage());
        }
    }

    public void actualizarDatos() {
        actualizarTabla();
    }
}
