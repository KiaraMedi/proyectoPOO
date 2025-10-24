package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de gestión de autos
 */
public class PanelAutos extends JPanel {

    private ArrayList<Auto> autos;
    private ArrayList<Escuderia> escuderias;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelAutos(ArrayList<Auto> autos, ArrayList<Escuderia> escuderias,
                     Registros sistema, JFrame parentFrame) {
        this.autos = autos;
        this.escuderias = escuderias;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Autos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Modelo", "Motor", "Escudería"};
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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Auto");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarAuto();
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Auto a : autos) {
            modeloTabla.addRow(new Object[]{
                a.getModelo(),
                a.getMotor(),
                a.getEscuderia() != null ? a.getEscuderia().getEscuderia() : "Sin escudería"
            });
        }
    }

    private void mostrarDialogoAgregarAuto() {
        JDialog dialogo = new JDialog(parentFrame, "Agregar Nuevo Auto", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtModelo = new JTextField();
        JTextField txtMotor = new JTextField();
        JComboBox<String> cbEscuderia = new JComboBox<>();
        for (Escuderia e : escuderias) {
            cbEscuderia.addItem(e.getEscuderia());
        }

        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Motor:"));
        panel.add(txtMotor);
        panel.add(new JLabel("Escudería:"));
        panel.add(cbEscuderia);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            if (txtModelo.getText().isEmpty() || txtMotor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Escuderia escuderiaSeleccionada = escuderias.get(cbEscuderia.getSelectedIndex());
            Auto nuevoAuto = new Auto(txtModelo.getText(), txtMotor.getText(), escuderiaSeleccionada);
            autos.add(nuevoAuto);
            sistema.agregarAuto(nuevoAuto);
            escuderiaSeleccionada.agregarAuto(nuevoAuto);

            JOptionPane.showMessageDialog(dialogo, "Auto agregado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    public void actualizarDatos() {
        actualizarTabla();
    }
}
