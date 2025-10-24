package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de gestión de escuderías
 */
public class PanelEscuderias extends JPanel {

    private ArrayList<Escuderia> escuderias;
    private ArrayList<Pais> paises;
    private ArrayList<Auto> autos;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelEscuderias(ArrayList<Escuderia> escuderias, ArrayList<Pais> paises,
                          ArrayList<Auto> autos, Registros sistema, JFrame parentFrame) {
        this.escuderias = escuderias;
        this.paises = paises;
        this.autos = autos;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Escuderías", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "País", "Nº Autos"};
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
        JButton btnAgregar = new JButton("+ Agregar Escudería");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarEscuderia();
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Escuderia e : escuderias) {
            int numAutos = 0;
            for (Auto a : autos) {
                if (a.getEscuderia() != null &&
                    a.getEscuderia().getEscuderia().equals(e.getEscuderia())) {
                    numAutos++;
                }
            }

            modeloTabla.addRow(new Object[]{
                e.getEscuderia(),
                e.getPais().getDescripcion(),
                numAutos
            });
        }
    }

    private void mostrarDialogoAgregarEscuderia() {
        JDialog dialogo = new JDialog(parentFrame, "Agregar Nueva Escudería", true);
        dialogo.setSize(400, 200);
        dialogo.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JComboBox<String> cbPais = new JComboBox<>();
        for (Pais p : paises) {
            cbPais.addItem(p.getDescripcion());
        }

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("País:"));
        panel.add(cbPais);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            if (txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "El nombre es obligatorio",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pais paisSeleccionado = paises.get(cbPais.getSelectedIndex());
            Escuderia nuevaEscuderia = new Escuderia(txtNombre.getText(), paisSeleccionado);
            escuderias.add(nuevaEscuderia);
            sistema.agregarEscuderia(nuevaEscuderia);

            JOptionPane.showMessageDialog(dialogo, "Escudería agregada exitosamente",
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
