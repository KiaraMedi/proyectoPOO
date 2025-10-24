package com.mycompany.proyectoEscuderiasUnidas.gui.paneles;

import com.mycompany.proyectoEscuderiasUnidas.entities.Registros;
import javax.swing.*;
import java.awt.*;

/**
 * Panel de generación de informes
 */
public class PanelInformes extends JPanel {

    private Registros sistema;
    private JTextArea txtInforme;
    private JFrame parentFrame;

    public PanelInformes(Registros sistema, JFrame parentFrame) {
        this.sistema = sistema;
        this.parentFrame = parentFrame;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Generación de Informes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        txtInforme = new JTextArea();
        txtInforme.setEditable(false);
        txtInforme.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(txtInforme);

        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones de informes
        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btn1 = new JButton("[Informe] Ranking General");
        JButton btn2 = new JButton("[Informe] Histórico de Podios");
        JButton btn3 = new JButton("Autos por Escudería");
        JButton btn4 = new JButton("[Informe] Mecánicos por Escudería");
        JButton btn5 = new JButton("Carreras por Circuito");
        JButton btn6 = new JButton("[Informe] Resultados por Fechas");
        JButton btn7 = new JButton("[Informe] Histórico de Piloto");
        JButton btn8 = new JButton("[Informe] Piloto en Circuito");

        // Acción para ranking general
        btn1.addActionListener(e -> ejecutarInforme(() -> sistema.informeRankingPilotos()));

        // Acción para histórico de podios
        btn2.addActionListener(e -> ejecutarInforme(() -> sistema.informeHistoricoPodiosVictorias()));

        // Acción para autos por escudería
        btn3.addActionListener(e -> {
            String escuderia = JOptionPane.showInputDialog(this, "Nombre de la escudería:");
            if (escuderia != null && !escuderia.isEmpty()) {
                ejecutarInforme(() -> sistema.informeAutosPorEscuderia(escuderia));
            }
        });

        // Acción para mecánicos por escudería
        btn4.addActionListener(e -> {
            String escuderia = JOptionPane.showInputDialog(this, "Nombre de la escudería:");
            if (escuderia != null && !escuderia.isEmpty()) {
                ejecutarInforme(() -> sistema.informeMecanicosPorEscuderia(escuderia));
            }
        });

        // Acción para carreras por circuito
        btn5.addActionListener(e -> {
            String circuito = JOptionPane.showInputDialog(this, "Nombre del circuito:");
            if (circuito != null && !circuito.isEmpty()) {
                ejecutarInforme(() -> sistema.informeCarrerasPorCircuito(circuito));
            }
        });

        panelBotones.add(btn1);
        panelBotones.add(btn2);
        panelBotones.add(btn3);
        panelBotones.add(btn4);
        panelBotones.add(btn5);
        panelBotones.add(btn6);
        panelBotones.add(btn7);
        panelBotones.add(btn8);

        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Ejecuta un informe capturando su salida de consola
     */
    private void ejecutarInforme(Runnable informeAction) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);

        try {
            informeAction.run();
            System.out.flush();
        } finally {
            System.setOut(old);
        }

        txtInforme.setText(baos.toString());
    }

    public void actualizarDatos() {
        // Panel estático - los informes se generan on-demand
    }
}
