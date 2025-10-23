package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Panel de Inicio con tarjetas clickeables
 */
public class PanelInicio extends JPanel {

    private JTabbedPane tabbedPane;
    private ArrayList<Piloto> pilotos;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Auto> autos;
    private ArrayList<Carrera> carreras;
    private ArrayList<Mecanico> mecanicos;

    public PanelInicio(JTabbedPane tabbedPane, ArrayList<Piloto> pilotos,
                       ArrayList<Escuderia> escuderias, ArrayList<Circuito> circuitos,
                       ArrayList<Auto> autos, ArrayList<Carrera> carreras,
                       ArrayList<Mecanico> mecanicos) {
        this.tabbedPane = tabbedPane;
        this.pilotos = pilotos;
        this.escuderias = escuderias;
        this.circuitos = circuitos;
        this.autos = autos;
        this.carreras = carreras;
        this.mecanicos = mecanicos;

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

        // Panel central con tarjetas
        JPanel panelCentral = new JPanel(new GridLayout(2, 3, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Agregar tarjetas clickeables
        panelCentral.add(crearTarjetaClickeable("Pilotos Registrados",
            () -> String.valueOf(pilotos.size()), new Color(52, 152, 219), 1));
        panelCentral.add(crearTarjetaClickeable("Escuderías",
            () -> String.valueOf(escuderias.size()), new Color(46, 204, 113), 2));
        panelCentral.add(crearTarjetaClickeable("Circuitos",
            () -> String.valueOf(circuitos.size()), new Color(155, 89, 182), 5));
        panelCentral.add(crearTarjetaClickeable("Autos",
            () -> String.valueOf(autos.size()), new Color(241, 196, 15), 3));
        panelCentral.add(crearTarjetaClickeable("Carreras",
            () -> String.valueOf(carreras.size()), new Color(230, 126, 34), 6));
        panelCentral.add(crearTarjetaClickeable("Mecánicos",
            () -> String.valueOf(mecanicos.size()), new Color(52, 73, 94), 4));

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInfo = new JPanel();
        panelInfo.add(new JLabel("v2.0 - Haz click en las tarjetas para navegar"));
        add(panelInfo, BorderLayout.SOUTH);
    }

    private JPanel crearTarjetaClickeable(String titulo,
                                          java.util.function.Supplier<String> valorSupplier,
                                          Color color, int pestanaIndex) {
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

        JLabel lblValor = new JLabel(valorSupplier.get(), SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 36));
        lblValor.setForeground(Color.WHITE);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);

        // Eventos de mouse
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
}
