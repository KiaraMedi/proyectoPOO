package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.gui.dialogos.DialogoAgregarPiloto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de gestión de pilotos
 */
public class PanelPilotos extends JPanel {

    private ArrayList<Pais> paises;
    private ArrayList<Piloto> pilotos;
    private Registros sistema;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelPilotos(ArrayList<Pais> paises, ArrayList<Piloto> pilotos,
                       Registros sistema, JFrame parentFrame) {
        this.paises = paises;
        this.pilotos = pilotos;
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("Gestión de Pilotos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Tabla de pilotos
        String[] columnas = {"DNI", "Nombre", "Apellido", "País", "Victorias", "Podios", "Puntos"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTabla();

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Piloto");
        JButton btnActualizar = new JButton("[Actualizar] Actualizar");

        btnActualizar.addActionListener(e -> actualizarTabla());

        btnAgregar.addActionListener(e -> {
            DialogoAgregarPiloto dialogo = new DialogoAgregarPiloto(parentFrame, pilotos, paises, sistema);
            dialogo.setVisible(true);
            actualizarTabla();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void actualizarTabla() {
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

    public void actualizarDatos() {
        actualizarTabla();
    }
}
