package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de gestión de mecánicos
 */
public class PanelMecanicos extends JPanel {

    private ArrayList<Mecanico> mecanicos;
    private ArrayList<Pais> paises;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelMecanicos(ArrayList<Mecanico> mecanicos, ArrayList<Pais> paises,
                         Registros sistema, JFrame parentFrame) {
        this.mecanicos = mecanicos;
        this.paises = paises;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Mecánicos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"DNI", "Nombre", "Apellido", "Especialidad", "Años Exp.", "País"};
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
        JButton btnAgregar = new JButton("+ Agregar Mecánico");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarMecanico();
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Mecanico m : mecanicos) {
            modeloTabla.addRow(new Object[]{
                m.getDni(),
                m.getNombre(),
                m.getApellido(),
                m.getEspecialidad(),
                m.getAniosExperiencia(),
                m.getPais().getDescripcion()
            });
        }
    }

    private void mostrarDialogoAgregarMecanico() {
        JDialog dialogo = new JDialog(parentFrame, "Agregar Nuevo Mecánico", true);
        dialogo.setSize(400, 350);
        dialogo.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtDni = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JComboBox<Especialidad> cbEspecialidad = new JComboBox<>(Especialidad.values());
        JSpinner spinAnios = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        JComboBox<String> cbPais = new JComboBox<>();
        for (Pais p : paises) {
            cbPais.addItem(p.getDescripcion());
        }

        panel.add(new JLabel("DNI:"));
        panel.add(txtDni);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Especialidad:"));
        panel.add(cbEspecialidad);
        panel.add(new JLabel("Años Exp.:"));
        panel.add(spinAnios);
        panel.add(new JLabel("País:"));
        panel.add(cbPais);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            if (txtDni.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pais paisSeleccionado = paises.get(cbPais.getSelectedIndex());
            Especialidad especialidad = (Especialidad) cbEspecialidad.getSelectedItem();
            int anios = (Integer) spinAnios.getValue();

            Mecanico nuevoMecanico = new Mecanico(anios, especialidad, txtDni.getText(),
                txtNombre.getText(), txtApellido.getText(), paisSeleccionado);
            mecanicos.add(nuevoMecanico);
            sistema.agregarMecanico(nuevoMecanico);

            JOptionPane.showMessageDialog(dialogo, "Mecánico agregado exitosamente",
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
