package com.mycompany.proyectoEscuderiasUnidas.gui.dialogos;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Diálogo para agregar un nuevo piloto
 */
public class DialogoAgregarPiloto extends JDialog {

    private ArrayList<Piloto> pilotos;
    private ArrayList<Pais> paises;
    private Registros sistema;

    public DialogoAgregarPiloto(JFrame parent, ArrayList<Piloto> pilotos,
                                ArrayList<Pais> paises, Registros sistema) {
        super(parent, "Agregar Nuevo Piloto", true);
        this.pilotos = pilotos;
        this.paises = paises;
        this.sistema = sistema;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtDni = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
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
        panel.add(new JLabel("País:"));
        panel.add(cbPais);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            if (txtDni.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pais paisSeleccionado = paises.get(cbPais.getSelectedIndex());
            Piloto nuevoPiloto = new Piloto(txtDni.getText(), txtNombre.getText(),
                txtApellido.getText(), paisSeleccionado);
            pilotos.add(nuevoPiloto);
            sistema.agregarPiloto(nuevoPiloto);

            JOptionPane.showMessageDialog(this, "Piloto agregado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        add(panel);
    }
}
