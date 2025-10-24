package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de gestión de circuitos
 */
public class PanelCircuitos extends JPanel {

    private ArrayList<Circuito> circuitos;
    private ArrayList<Pais> paises;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelCircuitos(ArrayList<Circuito> circuitos, ArrayList<Pais> paises,
                         Registros sistema, JFrame parentFrame) {
        this.circuitos = circuitos;
        this.paises = paises;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Circuitos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Longitud (m)", "País"};
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
        JButton btnAgregar = new JButton("+ Agregar Circuito");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarCircuito();
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Circuito c : circuitos) {
            modeloTabla.addRow(new Object[]{
                c.getnombre(),
                c.getLongitud(),
                c.getPais().getDescripcion()
            });
        }
    }

    private void mostrarDialogoAgregarCircuito() {
        JDialog dialogo = new JDialog(parentFrame, "Agregar Nuevo Circuito", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JSpinner spinLongitud = new JSpinner(new SpinnerNumberModel(5000, 1000, 10000, 100));
        JComboBox<String> cbPais = new JComboBox<>();
        for (Pais p : paises) {
            cbPais.addItem(p.getDescripcion());
        }

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Longitud (m):"));
        panel.add(spinLongitud);
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
            int longitud = (Integer) spinLongitud.getValue();

            Circuito nuevoCircuito = new Circuito(txtNombre.getText(), longitud, paisSeleccionado);
            circuitos.add(nuevoCircuito);
            sistema.agregarCircuito(nuevoCircuito);

            JOptionPane.showMessageDialog(dialogo, "Circuito agregado exitosamente",
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
