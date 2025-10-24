package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.Piloto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel de ranking de pilotos
 */
public class PanelRanking extends JPanel {

    private ArrayList<Piloto> pilotos;
    private DefaultTableModel modeloTabla;
    private JFrame parentFrame;

    public PanelRanking(ArrayList<Piloto> pilotos, JFrame parentFrame) {
        this.pilotos = pilotos;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Ranking de Pilotos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Pos", "Piloto", "Puntos", "Victorias", "Podios", "Vueltas Rápidas"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);

        JButton btnActualizar = new JButton("[Actualizar] Actualizar Ranking");
        btnActualizar.addActionListener(e -> actualizarTabla());

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnActualizar);
        add(panelBoton, BorderLayout.SOUTH);

        // Actualizar automáticamente al abrir
        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        // Ordenar pilotos por puntos
        ArrayList<Piloto> ranking = new ArrayList<>(pilotos);
        ranking.sort((p1, p2) -> Integer.compare(p2.getPuntosAcumulados(), p1.getPuntosAcumulados()));

        int posicion = 1;
        for (Piloto p : ranking) {
            modeloTabla.addRow(new Object[]{
                posicion++,
                p.getNombre() + " " + p.getApellido(),
                p.getPuntosAcumulados(),
                p.getVictorias(),
                p.getPodios(),
                p.getVueltasRapidas()
            });
        }
    }

    public void actualizarDatos() {
        actualizarTabla();
    }
}
