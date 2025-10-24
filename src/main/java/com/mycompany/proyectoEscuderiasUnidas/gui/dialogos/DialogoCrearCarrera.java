package com.mycompany.proyectoEscuderiasUnidas.gui.dialogos;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogoCrearCarrera extends JDialog {

    private ArrayList<Circuito> circuitos;
    private Carrera carreraCreada;

    public DialogoCrearCarrera(JFrame parent, ArrayList<Circuito> circuitos) {
        super(parent, "Crear Nueva Carrera", true);
        this.circuitos = circuitos;
        this.carreraCreada = null;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setSize(500, 450);
        setLocationRelativeTo(getParent());

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField("GP Monaco 2024");
        JTextField txtFecha = new JTextField("2024-11-15");
        JTextField txtHora = new JTextField("15:00");
        JTextField txtVueltas = new JTextField("50");
        JComboBox<String> cbCircuito = new JComboBox<>();
        for (Circuito c : circuitos) {
            cbCircuito.addItem(c.getnombre());
        }

        panel.add(new JLabel("Nombre de la Carrera:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        panel.add(txtFecha);
        panel.add(new JLabel("Hora (HH:MM):"));
        panel.add(txtHora);
        panel.add(new JLabel("Número de Vueltas:"));
        panel.add(txtVueltas);
        panel.add(new JLabel("Circuito:"));
        panel.add(cbCircuito);

        JButton btnCrear = new JButton("Crear Carrera");
        JButton btnCancelar = new JButton("Cancelar");

        btnCrear.addActionListener(e -> {
            try {
                if (txtNombre.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre de la carrera es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int vueltas = Integer.parseInt(txtVueltas.getText());
                Circuito circuitoSeleccionado = circuitos.get(cbCircuito.getSelectedIndex());

                carreraCreada = new Carrera(txtNombre.getText(), txtFecha.getText(), vueltas, txtHora.getText(), circuitoSeleccionado);

                JOptionPane.showMessageDialog(this, "Carrera creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El número de vueltas debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        panel.add(btnCrear);
        panel.add(btnCancelar);

        add(panel);
    }

    public Carrera getCarreraCreada() {
        return carreraCreada;
    }
}
