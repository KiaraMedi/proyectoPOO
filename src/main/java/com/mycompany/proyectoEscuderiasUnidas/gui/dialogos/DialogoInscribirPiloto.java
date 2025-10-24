package com.mycompany.proyectoEscuderiasUnidas.gui.dialogos;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogoInscribirPiloto extends JDialog {

    private ArrayList<Carrera> carreras;
    private ArrayList<Piloto> pilotos;
    private ArrayList<Auto> autos;
    private boolean inscripcionExitosa;

    public DialogoInscribirPiloto(JFrame parent, ArrayList<Carrera> carreras, ArrayList<Piloto> pilotos, ArrayList<Auto> autos) {
        super(parent, "Inscribir Piloto a Carrera", true);
        this.carreras = carreras;
        this.pilotos = pilotos;
        this.autos = autos;
        this.inscripcionExitosa = false;

        if (carreras.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Primero debes crear al menos una carrera", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setSize(500, 300);
        setLocationRelativeTo(getParent());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Selección de carrera
        JComboBox<String> cbCarrera = new JComboBox<>();
        for (Carrera c : carreras) {
            String nombreCarrera = c.getNombre() != null ? c.getNombre() : "GP " + c.getCircuito().getnombre();
            cbCarrera.addItem(nombreCarrera);
        }

        // Selección de piloto
        JComboBox<String> cbPiloto = new JComboBox<>();
        for (Piloto p : pilotos) {
            cbPiloto.addItem(p.getNombre() + " " + p.getApellido());
        }

        // Selección de auto
        JComboBox<String> cbAuto = new JComboBox<>();
        for (Auto a : autos) {
            cbAuto.addItem(a.getModelo() + " (" + a.getEscuderia().getEscuderia() + ")");
        }

        // Fecha de asignación
        JTextField txtFechaAsignacion = new JTextField(java.time.LocalDate.now().toString());

        panel.add(new JLabel("Carrera:"));
        panel.add(cbCarrera);
        panel.add(new JLabel("Piloto:"));
        panel.add(cbPiloto);
        panel.add(new JLabel("Auto:"));
        panel.add(cbAuto);
        panel.add(new JLabel("Fecha Asignación:"));
        panel.add(txtFechaAsignacion);

        JButton btnInscribir = new JButton("Inscribir");
        JButton btnCancelar = new JButton("Cancelar");

        btnInscribir.addActionListener(e -> {
            Carrera carreraSeleccionada = carreras.get(cbCarrera.getSelectedIndex());
            Piloto pilotoSeleccionado = pilotos.get(cbPiloto.getSelectedIndex());
            Auto autoSeleccionado = autos.get(cbAuto.getSelectedIndex());

            // Verificar si el piloto ya está inscrito
            boolean yaInscrito = false;
            for (AutoPiloto ap : carreraSeleccionada.getAutoPilotos()) {
                if (ap.getPiloto().equals(pilotoSeleccionado)) {
                    yaInscrito = true;
                    break;
                }
            }

            if (yaInscrito) {
                JOptionPane.showMessageDialog(this, "El piloto ya está inscrito en esta carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Inscribir piloto
            carreraSeleccionada.agregarAutoPiloto(txtFechaAsignacion.getText(), autoSeleccionado, pilotoSeleccionado);
            inscripcionExitosa = true;

            JOptionPane.showMessageDialog(this,
                pilotoSeleccionado.getNombre() + " " + pilotoSeleccionado.getApellido() +
                " inscrito exitosamente en " + (carreraSeleccionada.getNombre() != null ? carreraSeleccionada.getNombre() : "la carrera"),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        panel.add(btnInscribir);
        panel.add(btnCancelar);

        add(panel);
    }

    public boolean isInscripcionExitosa() {
        return inscripcionExitosa;
    }
}
