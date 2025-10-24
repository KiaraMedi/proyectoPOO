package com.mycompany.proyectoEscuderiasUnidas.gui;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.persistence.DataPersistence;
import com.mycompany.proyectoEscuderiasUnidas.gui.paneles.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Interfaz Gráfica Principal del Sistema de Gestión F1
 * Version modular - Cada panel en su propia clase
 */
public class SistemaF1GUI extends JFrame {

    // Sistema de registros central
    private Registros sistema;

    // Listas de datos
    private ArrayList<Pais> paises;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Piloto> pilotos;
    private ArrayList<Auto> autos;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Carrera> carreras;
    private ArrayList<Mecanico> mecanicos;

    // Componentes principales
    private JTabbedPane tabbedPane;

    // Paneles modulares
    private PanelInicio panelInicio;
    private PanelPilotos panelPilotos;
    private PanelEscuderias panelEscuderias;
    private PanelAutos panelAutos;
    private PanelMecanicos panelMecanicos;
    private PanelCircuitos panelCircuitos;
    private PanelCarreras panelCarreras;
    private PanelResultados panelResultados;
    private PanelRanking panelRanking;
    private PanelInformes panelInformes;

    public SistemaF1GUI() {
        // Inicializar datos
        inicializarDatos();

        // Configuración de la ventana principal
        setTitle("Sistema de Gestión - Escuderías Unidas F1");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal con pestañas
        tabbedPane = new JTabbedPane();

        // Crear e inicializar todos los paneles modulares
        crearPaneles();

        // Agregar pestañas
        tabbedPane.addTab("Inicio", panelInicio);
        tabbedPane.addTab("Pilotos", panelPilotos);
        tabbedPane.addTab("Escuderias", panelEscuderias);
        tabbedPane.addTab("Autos", panelAutos);
        tabbedPane.addTab("Mecanicos", panelMecanicos);
        tabbedPane.addTab("Circuitos", panelCircuitos);
        tabbedPane.addTab("Carreras", panelCarreras);
        tabbedPane.addTab("Resultados", panelResultados);
        tabbedPane.addTab("Ranking", panelRanking);
        tabbedPane.addTab("Informes", panelInformes);

        add(tabbedPane);

        // Aplicar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea todos los paneles modulares
     */
    private void crearPaneles() {
        panelInicio = new PanelInicio(paises, escuderias, pilotos, autos, circuitos,
                                     carreras, mecanicos, tabbedPane, this);

        panelPilotos = new PanelPilotos(paises, pilotos, sistema, this);

        panelEscuderias = new PanelEscuderias(escuderias, paises, autos, sistema, this);

        panelAutos = new PanelAutos(autos, escuderias, sistema, this);

        panelMecanicos = new PanelMecanicos(mecanicos, paises, sistema, this);

        panelCircuitos = new PanelCircuitos(circuitos, paises, sistema, this);

        panelCarreras = new PanelCarreras(carreras, circuitos, pilotos, autos,
                                         paises, escuderias, mecanicos, sistema, this);

        panelResultados = new PanelResultados(carreras, pilotos, this);

        panelRanking = new PanelRanking(pilotos, this);

        panelInformes = new PanelInformes(sistema, this);
    }

    /**
     * Inicializa los datos del sistema
     * Intenta cargar desde archivos, si no existen crea datos por defecto
     */
    private void inicializarDatos() {
        // Intentar cargar datos guardados
        if (DataPersistence.existenDatos()) {
            try {
                paises = DataPersistence.cargarPaises();
                escuderias = DataPersistence.cargarEscuderias();
                pilotos = DataPersistence.cargarPilotos();
                autos = DataPersistence.cargarAutos();
                circuitos = DataPersistence.cargarCircuitos();
                carreras = DataPersistence.cargarCarreras();
                mecanicos = DataPersistence.cargarMecanicos();

                sistema = new Registros(autos, mecanicos, pilotos, circuitos, escuderias, paises);
                System.out.println("Datos cargados exitosamente desde archivo");
                return;
            } catch (Exception e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                    "No se pudieron cargar los datos guardados.\nSe inicializarán datos por defecto.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Si no hay datos guardados o hubo error, crear datos por defecto
        crearDatosPorDefecto();
    }

    /**
     * Crea datos de ejemplo para el sistema
     */
    private void crearDatosPorDefecto() {
        paises = new ArrayList<>();
        paises.add(new Pais(1, "Argentina"));
        paises.add(new Pais(2, "Brasil"));
        paises.add(new Pais(3, "Italia"));
        paises.add(new Pais(4, "Alemania"));
        paises.add(new Pais(5, "Mónaco"));
        paises.add(new Pais(6, "España"));
        paises.add(new Pais(7, "Reino Unido"));

        escuderias = new ArrayList<>();
        escuderias.add(new Escuderia("Ferrari", paises.get(2)));
        escuderias.add(new Escuderia("Mercedes", paises.get(3)));
        escuderias.add(new Escuderia("Red Bull Racing", paises.get(6)));

        pilotos = new ArrayList<>();
        pilotos.add(new Piloto("12345678", "Lewis", "Hamilton", paises.get(6)));
        pilotos.add(new Piloto("87654321", "Max", "Verstappen", paises.get(3)));
        pilotos.add(new Piloto("11223344", "Charles", "Leclerc", paises.get(4)));

        autos = new ArrayList<>();
        autos.add(new Auto("SF-23", "Ferrari V6", escuderias.get(0)));
        autos.add(new Auto("W14", "Mercedes V6", escuderias.get(1)));
        autos.add(new Auto("RB19", "Honda V6", escuderias.get(2)));

        escuderias.get(0).agregarAuto(autos.get(0));
        escuderias.get(1).agregarAuto(autos.get(1));
        escuderias.get(2).agregarAuto(autos.get(2));

        circuitos = new ArrayList<>();
        circuitos.add(new Circuito("Mónaco", 3337, paises.get(4)));
        circuitos.add(new Circuito("Monza", 5793, paises.get(2)));
        circuitos.add(new Circuito("Silverstone", 5891, paises.get(6)));

        carreras = new ArrayList<>();
        mecanicos = new ArrayList<>();

        // Crear sistema de registros
        sistema = new Registros(autos, mecanicos, pilotos, circuitos, escuderias, paises);

        System.out.println("Datos por defecto inicializados");
    }

    /**
     * Método principal para ejecutar la aplicación
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaF1GUI gui = new SistemaF1GUI();
            gui.setVisible(true);
        });
    }
}
