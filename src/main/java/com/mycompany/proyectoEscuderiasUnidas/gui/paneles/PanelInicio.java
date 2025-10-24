package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.persistence.DataPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Panel de inicio con dashboard de estadísticas
 */
public class PanelInicio extends JPanel {

    private ArrayList<Pais> paises;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Piloto> pilotos;
    private ArrayList<Auto> autos;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Carrera> carreras;
    private ArrayList<Mecanico> mecanicos;
    private JTabbedPane tabbedPane;
    private JFrame parentFrame;

    public PanelInicio(ArrayList<Pais> paises, ArrayList<Escuderia> escuderias,
                      ArrayList<Piloto> pilotos, ArrayList<Auto> autos,
                      ArrayList<Circuito> circuitos, ArrayList<Carrera> carreras,
                      ArrayList<Mecanico> mecanicos, JTabbedPane tabbedPane,
                      JFrame parentFrame) {
        this.paises = paises;
        this.escuderias = escuderias;
        this.pilotos = pilotos;
        this.autos = autos;
        this.circuitos = circuitos;
        this.carreras = carreras;
        this.mecanicos = mecanicos;
        this.tabbedPane = tabbedPane;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Sistema de Gestión - Escuderías Unidas F1", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(220, 0, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel central con estadísticas
        JPanel panelCentral = new JPanel(new GridLayout(2, 3, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        panelCentral.add(crearTarjetaEstadistica("Pilotos Registrados", String.valueOf(pilotos.size()), new Color(52, 152, 219), 1));
        panelCentral.add(crearTarjetaEstadistica("Escuderías", String.valueOf(escuderias.size()), new Color(46, 204, 113), 2));
        panelCentral.add(crearTarjetaEstadistica("Circuitos", String.valueOf(circuitos.size()), new Color(155, 89, 182), 5));
        panelCentral.add(crearTarjetaEstadistica("Autos", String.valueOf(autos.size()), new Color(241, 196, 15), 3));
        panelCentral.add(crearTarjetaEstadistica("Carreras", String.valueOf(carreras.size()), new Color(230, 126, 34), 6));
        panelCentral.add(crearTarjetaEstadistica("Mecánicos", String.valueOf(mecanicos.size()), new Color(52, 73, 94), 4));

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones y información
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Botones de persistencia
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnGuardar = new JButton("[Guardar] Guardar Datos");
        JButton btnCargar = new JButton("[Recargar] Recargar Datos");

        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCargar.setFont(new Font("Arial", Font.BOLD, 14));

        btnGuardar.addActionListener(e -> guardarDatos());
        btnCargar.addActionListener(e -> recargarDatos());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);
        panelInferior.add(panelBotones, BorderLayout.NORTH);

        // Información
        JPanel panelInfo = new JPanel();
        panelInfo.add(new JLabel("v2.1 - Haz click en las tarjetas para navegar | Datos se guardan automáticamente"));
        panelInferior.add(panelInfo, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearTarjetaEstadistica(String titulo, String valor, Color color, int pestanaIndex) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 36));
        lblValor.setForeground(Color.WHITE);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);

        // Hacer clickeable
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(pestanaIndex);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.YELLOW, 3),
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        return panel;
    }

    private void guardarDatos() {
        try {
            DataPersistence.guardarTodo(paises, escuderias, pilotos, autos, circuitos, carreras, mecanicos);
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente en la carpeta 'data'",
                "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos:\n" + e.getMessage(),
                "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recargarDatos() {
        try {
            paises.clear();
            escuderias.clear();
            pilotos.clear();
            autos.clear();
            circuitos.clear();
            carreras.clear();
            mecanicos.clear();

            paises.addAll(DataPersistence.cargarPaises());
            escuderias.addAll(DataPersistence.cargarEscuderias());
            pilotos.addAll(DataPersistence.cargarPilotos());
            autos.addAll(DataPersistence.cargarAutos());
            circuitos.addAll(DataPersistence.cargarCircuitos());
            carreras.addAll(DataPersistence.cargarCarreras());
            mecanicos.addAll(DataPersistence.cargarMecanicos());

            JOptionPane.showMessageDialog(this, "Datos recargados exitosamente",
                "Recarga Exitosa", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la UI
            actualizarDatos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al recargar los datos:\n" + e.getMessage(),
                "Error de Recarga", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarDatos() {
        // Actualizar las tarjetas con los nuevos valores
        removeAll();
        inicializarComponentes();
        revalidate();
        repaint();
    }
}
