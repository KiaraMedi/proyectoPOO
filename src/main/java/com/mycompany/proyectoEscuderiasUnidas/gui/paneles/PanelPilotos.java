package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.gui.dialogos.DialogoAgregarPiloto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de Gestión de Pilotos
 */
public class PanelPilotos extends JPanel {

    private ArrayList<Piloto> pilotos;
    private ArrayList<Pais> paises;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public PanelPilotos(ArrayList<Piloto> pilotos, ArrayList<Pais> paises, Registros sistema) {
        this.pilotos = pilotos;
        this.paises = paises;
        this.sistema = sistema;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("Gestión de Pilotos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "País", "Victorias", "Podios", "Puntos"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTabla();

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Piloto");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTabla());

        btnAgregar.addActionListener(e -> {
            DialogoAgregarPiloto dialogo = new DialogoAgregarPiloto(
                (JFrame) SwingUtilities.getWindowAncestor(this), pilotos, paises, sistema);
            dialogo.setVisible(true);
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Piloto p : pilotos) {
            modeloTabla.addRow(new Object[]{
                p.getDni(),
                p.getNombre(),
                p.getApellido(),
                p.getPais().getDescripcion(),
                p.getVictorias(),
                p.getPodios(),
                p.getPuntosAcumulados()
            });
        }
    }
}
