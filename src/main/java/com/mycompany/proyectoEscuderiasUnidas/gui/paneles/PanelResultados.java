package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel para registrar resultados de carreras
 */
public class PanelResultados extends JPanel {

    private ArrayList<Carrera> carreras;
    private ArrayList<Piloto> pilotos;
    private JFrame parentFrame;

    public PanelResultados(ArrayList<Carrera> carreras, ArrayList<Piloto> pilotos, JFrame parentFrame) {
        this.carreras = carreras;
        this.pilotos = pilotos;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Registro de Resultados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Selección de carrera
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Seleccionar Carrera:"), gbc);

        JComboBox<String> cbCarrera = new JComboBox<>();
        cbCarrera.addItem("-- Seleccione una carrera --");
        for (Carrera c : carreras) {
            String nombreCarrera = c.getNombre() != null ? c.getNombre() : "GP " + c.getCircuito().getnombre();
            cbCarrera.addItem(nombreCarrera + " - " + c.getFechaRealizacion());
        }
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelFormulario.add(cbCarrera, gbc);
        gbc.gridwidth = 1;

        // Botón actualizar lista de carreras
        JButton btnActualizarLista = new JButton("[Actualizar]");
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelFormulario.add(btnActualizarLista, gbc);

        btnActualizarLista.addActionListener(e -> {
            cbCarrera.removeAllItems();
            cbCarrera.addItem("-- Seleccione una carrera --");
            for (Carrera c : carreras) {
                String nombreCarrera = c.getNombre() != null ? c.getNombre() : "GP " + c.getCircuito().getnombre();
                cbCarrera.addItem(nombreCarrera + " - " + c.getFechaRealizacion());
            }
            JOptionPane.showMessageDialog(this, "Lista de carreras actualizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        // Selección de piloto
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Piloto:"), gbc);

        JComboBox<String> cbPiloto = new JComboBox<>();
        for (Piloto p : pilotos) {
            cbPiloto.addItem(p.getNombre() + " " + p.getApellido());
        }
        gbc.gridx = 1; gbc.gridy = 1;
        panelFormulario.add(cbPiloto, gbc);

        // Posición
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Posición:"), gbc);

        JSpinner spinPosicion = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        gbc.gridx = 1; gbc.gridy = 2;
        panelFormulario.add(spinPosicion, gbc);

        // Vuelta rápida
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Vuelta Rápida:"), gbc);

        JCheckBox chkVueltaRapida = new JCheckBox();
        gbc.gridx = 1; gbc.gridy = 3;
        panelFormulario.add(chkVueltaRapida, gbc);

        // Botón registrar
        JButton btnRegistrar = new JButton("[Registrar] Registrar Resultado");
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        panelFormulario.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            if (cbCarrera.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Carrera carreraSeleccionada = carreras.get(cbCarrera.getSelectedIndex() - 1);
            Piloto pilotoSeleccionado = pilotos.get(cbPiloto.getSelectedIndex());
            int posicion = (Integer) spinPosicion.getValue();
            boolean vueltaRapida = chkVueltaRapida.isSelected();

            // Buscar el AutoPiloto en la carrera
            AutoPiloto autoPilotoEnCarrera = null;
            for (AutoPiloto ap : carreraSeleccionada.getAutoPilotos()) {
                if (ap.getPiloto().equals(pilotoSeleccionado)) {
                    autoPilotoEnCarrera = ap;
                    break;
                }
            }

            if (autoPilotoEnCarrera == null) {
                JOptionPane.showMessageDialog(this, "El piloto no está inscrito en esta carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exito = carreraSeleccionada.registrarResultado(autoPilotoEnCarrera, posicion, vueltaRapida);

            if (exito) {
                // Actualizar estadísticas del piloto
                ResultadoCarrera resultado = carreraSeleccionada.getResultados().get(carreraSeleccionada.getResultados().size() - 1);
                pilotoSeleccionado.agregarResultadoCarrera(resultado);

                JOptionPane.showMessageDialog(this,
                    "Resultado registrado exitosamente\nPuntos obtenidos: " + resultado.getPuntosObtenidos(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(panelFormulario, BorderLayout.NORTH);

        // Área de texto para mostrar resultados
        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(txtResultados);

        JButton btnMostrarResultados = new JButton("Ver Resultados de Carrera");
        btnMostrarResultados.addActionListener(e -> {
            if (cbCarrera.getSelectedIndex() == 0) {
                return;
            }

            Carrera carreraSeleccionada = carreras.get(cbCarrera.getSelectedIndex() - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("RESULTADOS - ").append(carreraSeleccionada.getCircuito().getnombre()).append("\n");
            sb.append("Fecha: ").append(carreraSeleccionada.getFechaRealizacion()).append("\n\n");

            if (carreraSeleccionada.getResultados().isEmpty()) {
                sb.append("Sin resultados registrados\n");
            } else {
                for (ResultadoCarrera r : carreraSeleccionada.getResultados()) {
                    sb.append(r.toString()).append("\n");
                }
            }

            txtResultados.setText(sb.toString());
        });

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnMostrarResultados, BorderLayout.NORTH);
        panelInferior.add(scrollResultados, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.CENTER);
    }

    public void actualizarDatos() {
        // Panel dinámico - se actualiza mediante los combos y botones
    }
}
