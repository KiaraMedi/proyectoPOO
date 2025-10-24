package com.mycompany.proyectoEscuderiasUnidas.gui;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import com.mycompany.proyectoEscuderiasUnidas.persistence.DataPersistence;
import com.mycompany.proyectoEscuderiasUnidas.gui.dialogos.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Interfaz Gráfica Principal del Sistema de Gestión F1
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

        // Agregar pestañas
        tabbedPane.addTab("Inicio", crearPanelInicio());
        tabbedPane.addTab("Pilotos", crearPanelPilotos());
        tabbedPane.addTab("Escuderias", crearPanelEscuderias());
        tabbedPane.addTab("Autos", crearPanelAutos());
        tabbedPane.addTab("Mecanicos", crearPanelMecanicos());
        tabbedPane.addTab("Circuitos", crearPanelCircuitos());
        tabbedPane.addTab("Carreras", crearPanelCarreras());
        tabbedPane.addTab("Resultados", crearPanelResultados());
        tabbedPane.addTab("Ranking", crearPanelRanking());
        tabbedPane.addTab("Informes", crearPanelInformes());

        add(tabbedPane);

        // Aplicar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        paises = new ArrayList<>();
        paises.add(new Pais(1, "Argentina"));
        paises.add(new Pais(2, "Brasil"));
        paises.add(new Pais(3, "Italia"));
        paises.add(new Pais(4, "Alemania"));
        paises.add(new Pais(5, "Mónaco"));
        paises.add(new Pais(6, "España"));
        paises.add(new Pais(7, "Reino Unido"));

        // Crear escuderías
        escuderias = new ArrayList<>();
        escuderias.add(new Escuderia("Ferrari", paises.get(2)));
        escuderias.add(new Escuderia("Red Bull Racing", paises.get(3)));
        escuderias.add(new Escuderia("Mercedes AMG", paises.get(3)));

        // Crear pilotos
        pilotos = new ArrayList<>();
        pilotos.add(new Piloto("44123456", "Lewis", "Hamilton", paises.get(6)));
        pilotos.add(new Piloto("33789012", "Max", "Verstappen", paises.get(3)));
        pilotos.add(new Piloto("16345678", "Charles", "Leclerc", paises.get(4)));
        pilotos.add(new Piloto("11567890", "Sergio", "Pérez", paises.get(0)));

        // Asignar pilotos a escuderías
        escuderias.get(0).agregarPiloto("2024-01-01", "2024-12-31", pilotos.get(2), escuderias.get(0));
        escuderias.get(1).agregarPiloto("2024-01-01", "2024-12-31", pilotos.get(1), escuderias.get(1));
        escuderias.get(1).agregarPiloto("2024-01-01", "2024-12-31", pilotos.get(3), escuderias.get(1));
        escuderias.get(2).agregarPiloto("2024-01-01", "2024-12-31", pilotos.get(0), escuderias.get(2));

        // Crear autos
        autos = new ArrayList<>();
        autos.add(new Auto("SF-24", "Ferrari V6 Turbo", escuderias.get(0)));
        autos.add(new Auto("RB20", "Honda RBPT V6", escuderias.get(1)));
        autos.add(new Auto("W15", "Mercedes-AMG M14", escuderias.get(2)));

        // Crear circuitos
        circuitos = new ArrayList<>();
        circuitos.add(new Circuito("Autodromo di Monza", 5793, paises.get(2)));
        circuitos.add(new Circuito("Circuit de Monaco", 3337, paises.get(4)));
        circuitos.add(new Circuito("Autodromo de Interlagos", 4309, paises.get(1)));
        circuitos.add(new Circuito("Silverstone Circuit", 5891, paises.get(6)));

        // Crear mecánicos
        mecanicos = new ArrayList<>();
        mecanicos.add(new Mecanico(15, Especialidad.MOTOR, "30111222", "Giovanni", "Rossi", paises.get(2)));
        mecanicos.add(new Mecanico(10, Especialidad.CHASIS, "30222333", "Hans", "Schmidt", paises.get(3)));
        mecanicos.add(new Mecanico(8, Especialidad.NEUMATICOS, "30333444", "Pierre", "Dubois", paises.get(4)));

        // Inicializar carreras vacío
        carreras = new ArrayList<>();

        // Crear sistema de registros
        sistema = new Registros(autos, mecanicos, pilotos, circuitos, escuderias, paises);
    }

    // ========== PANEL DE INICIO ==========
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Sistema de Gestión - Escuderías Unidas F1", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(220, 0, 0));
        panel.add(titulo, BorderLayout.NORTH);

        // Panel central con estadísticas
        JPanel panelCentral = new JPanel(new GridLayout(2, 3, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        panelCentral.add(crearTarjetaEstadistica("Pilotos Registrados", String.valueOf(pilotos.size()), new Color(52, 152, 219)));
        panelCentral.add(crearTarjetaEstadistica("Escuderías", String.valueOf(escuderias.size()), new Color(46, 204, 113)));
        panelCentral.add(crearTarjetaEstadistica("Circuitos", String.valueOf(circuitos.size()), new Color(155, 89, 182)));
        panelCentral.add(crearTarjetaEstadistica("Autos", String.valueOf(autos.size()), new Color(241, 196, 15)));
        panelCentral.add(crearTarjetaEstadistica("Carreras", String.valueOf(carreras.size()), new Color(230, 126, 34)));
        panelCentral.add(crearTarjetaEstadistica("Mecánicos", String.valueOf(mecanicos.size()), new Color(52, 73, 94)));

        panel.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones y información
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Botones de persistencia
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnGuardar = new JButton("[Guardar] Guardar Datos");
        JButton btnCargar = new JButton("[Recargar] Recargar Datos");

        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCargar.setFont(new Font("Arial", Font.BOLD, 14));

        btnGuardar.addActionListener(e -> guardarDatos());
        btnCargar.addActionListener(e -> recargarDatos());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);
        panelInferior.add(panelBotones, BorderLayout.NORTH);

        // Información
        JPanel panelInfo = new JPanel();
        panelInfo.add(new JLabel("v2.1 - Haz click en las tarjetas para navegar | Datos se guardan automáticamente"));
        panelInferior.add(panelInfo, BorderLayout.SOUTH);

        panel.add(panelInferior, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearTarjetaEstadistica(String titulo, String valor, Color color) {
        final int pestanaIndex;

        // Mapear título a índice de pestaña
        switch(titulo) {
            case "Pilotos Registrados": pestanaIndex = 1; break;
            case "Escuderías": pestanaIndex = 2; break;
            case "Autos": pestanaIndex = 3; break;
            case "Mecánicos": pestanaIndex = 4; break;
            case "Circuitos": pestanaIndex = 5; break;
            case "Carreras": pestanaIndex = 6; break;
            default: pestanaIndex = 0;
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 36));
        lblValor.setForeground(Color.WHITE);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);

        // Hacer clickeable
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(pestanaIndex);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.YELLOW, 3),
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        return panel;
    }

    // ========== PANEL DE PILOTOS ==========
    private JPanel crearPanelPilotos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("Gestión de Pilotos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        // Tabla de pilotos
        String[] columnas = {"DNI", "Nombre", "Apellido", "País", "Victorias", "Podios", "Puntos"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaPilotos(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Piloto");
        JButton btnActualizar = new JButton("[Actualizar] Actualizar");

        btnActualizar.addActionListener(e -> actualizarTablaPilotos(modeloTabla));

        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarPiloto();
            actualizarTablaPilotos(modeloTabla);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaPilotos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Piloto p : pilotos) {
            modelo.addRow(new Object[]{
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

    private void mostrarDialogoAgregarPiloto() {
        JDialog dialogo = new JDialog(this, "Agregar Nuevo Piloto", true);
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(this);

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
            if (txtDni.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pais paisSeleccionado = paises.get(cbPais.getSelectedIndex());
            Piloto nuevoPiloto = new Piloto(txtDni.getText(), txtNombre.getText(), txtApellido.getText(), paisSeleccionado);
            pilotos.add(nuevoPiloto);
            sistema.agregarPiloto(nuevoPiloto);

            JOptionPane.showMessageDialog(dialogo, "Piloto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    // ========== PANEL DE AUTOS (MEJORADO CON FORMULARIO) ==========
    private JPanel crearPanelAutos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Autos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Modelo", "Motor", "Escudería"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaAutos(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        // NUEVO: Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Auto");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTablaAutos(modeloTabla));
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarAuto();
            actualizarTablaAutos(modeloTabla);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaAutos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Auto a : autos) {
            modelo.addRow(new Object[]{
                a.getModelo(),
                a.getMotor(),
                a.getEscuderia() != null ? a.getEscuderia().getEscuderia() : "Sin escudería"
            });
        }
    }

    private void mostrarDialogoAgregarAuto() {
        JDialog dialogo = new JDialog(this, "Agregar Nuevo Auto", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtModelo = new JTextField();
        JTextField txtMotor = new JTextField();
        JComboBox<String> cbEscuderia = new JComboBox<>();
        for (Escuderia e : escuderias) {
            cbEscuderia.addItem(e.getEscuderia());
        }

        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Motor:"));
        panel.add(txtMotor);
        panel.add(new JLabel("Escudería:"));
        panel.add(cbEscuderia);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            if (txtModelo.getText().isEmpty() || txtMotor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Escuderia escuderiaSeleccionada = escuderias.get(cbEscuderia.getSelectedIndex());
            Auto nuevoAuto = new Auto(txtModelo.getText(), txtMotor.getText(), escuderiaSeleccionada);
            autos.add(nuevoAuto);
            sistema.agregarAuto(nuevoAuto);
            escuderiaSeleccionada.agregarAuto(nuevoAuto);

            JOptionPane.showMessageDialog(dialogo, "Auto agregado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    // ========== PANEL DE ESCUDERÍAS (NUEVO) ==========
    private JPanel crearPanelEscuderias() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Escuderías", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "País", "Nº Autos"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaEscuderias(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Escudería");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTablaEscuderias(modeloTabla));
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarEscuderia();
            actualizarTablaEscuderias(modeloTabla);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaEscuderias(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Escuderia e : escuderias) {
            int numAutos = 0;
            for (Auto a : autos) {
                if (a.getEscuderia() != null &&
                    a.getEscuderia().getEscuderia().equals(e.getEscuderia())) {
                    numAutos++;
                }
            }

            modelo.addRow(new Object[]{
                e.getEscuderia(),
                e.getPais().getDescripcion(),
                numAutos
            });
        }
    }

    private void mostrarDialogoAgregarEscuderia() {
        JDialog dialogo = new JDialog(this, "Agregar Nueva Escudería", true);
        dialogo.setSize(400, 200);
        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JComboBox<String> cbPais = new JComboBox<>();
        for (Pais p : paises) {
            cbPais.addItem(p.getDescripcion());
        }

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
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
            Escuderia nuevaEscuderia = new Escuderia(txtNombre.getText(), paisSeleccionado);
            escuderias.add(nuevaEscuderia);
            sistema.agregarEscuderia(nuevaEscuderia);

            JOptionPane.showMessageDialog(dialogo, "Escudería agregada exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    // ========== PANEL DE MECÁNICOS (NUEVO) ==========
    private JPanel crearPanelMecanicos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Mecánicos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"DNI", "Nombre", "Apellido", "Especialidad", "Años Exp.", "País"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaMecanicos(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Mecánico");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTablaMecanicos(modeloTabla));
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarMecanico();
            actualizarTablaMecanicos(modeloTabla);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaMecanicos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Mecanico m : mecanicos) {
            modelo.addRow(new Object[]{
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
        JDialog dialogo = new JDialog(this, "Agregar Nuevo Mecánico", true);
        dialogo.setSize(400, 350);
        dialogo.setLocationRelativeTo(this);

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

    // ========== PANEL DE CIRCUITOS (NUEVO) ==========
    private JPanel crearPanelCircuitos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Circuitos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Longitud (m)", "País"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaCircuitos(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("+ Agregar Circuito");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTablaCircuitos(modeloTabla));
        btnAgregar.addActionListener(e -> {
            mostrarDialogoAgregarCircuito();
            actualizarTablaCircuitos(modeloTabla);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaCircuitos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Circuito c : circuitos) {
            modelo.addRow(new Object[]{
                c.getnombre(),
                c.getLongitud(),
                c.getPais().getDescripcion()
            });
        }
    }

    private void mostrarDialogoAgregarCircuito() {
        JDialog dialogo = new JDialog(this, "Agregar Nuevo Circuito", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(this);

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

    // ========== PANEL DE CARRERAS ==========
    private JPanel crearPanelCarreras() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Carreras", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Fecha", "Hora", "Circuito", "Vueltas", "Participantes"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        actualizarTablaCarreras(modeloTabla);

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCrear = new JButton("+ Crear Carrera");
        JButton btnInscribir = new JButton("+ Inscribir Piloto");
        JButton btnActualizar = new JButton("[Actualizar]");

        btnActualizar.addActionListener(e -> actualizarTablaCarreras(modeloTabla));

        btnCrear.addActionListener(e -> {
            mostrarDialogoCrearCarrera();
            actualizarTablaCarreras(modeloTabla);
        });

        btnInscribir.addActionListener(e -> {
            mostrarDialogoInscribirPiloto();
            actualizarTablaCarreras(modeloTabla);
        });

        panelBotones.add(btnCrear);
        panelBotones.add(btnInscribir);
        panelBotones.add(btnActualizar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private void actualizarTablaCarreras(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        for (Carrera c : carreras) {
            modelo.addRow(new Object[]{
                c.getNombre() != null ? c.getNombre() : "GP " + c.getCircuito().getnombre(),
                c.getFechaRealizacion(),
                c.getHoraRealizacion(),
                c.getCircuito().getnombre(),
                c.getNumeroVueltas(),
                c.getAutoPilotos().size()
            });
        }
    }

    private void mostrarDialogoCrearCarrera() {
        JDialog dialogo = new JDialog(this, "Crear Nueva Carrera", true);
        dialogo.setSize(500, 450);
        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField("GP Monaco 2024");
        JTextField txtFecha = new JTextField("2024-11-15");
        JTextField txtHora = new JTextField("15:00");
        JTextField txtVueltas = new JTextField("50");
        JComboBox<String> cbCircuito = new JComboBox<>();
        for (Circuito c : circuitos) {
            cbCircuito.addItem(c.getnombre());
        }

        panel.add(new JLabel("Nombre de la Carrera:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        panel.add(txtFecha);
        panel.add(new JLabel("Hora (HH:MM):"));
        panel.add(txtHora);
        panel.add(new JLabel("Número de Vueltas:"));
        panel.add(txtVueltas);
        panel.add(new JLabel("Circuito:"));
        panel.add(cbCircuito);

        JButton btnCrear = new JButton("Crear Carrera");
        JButton btnCancelar = new JButton("Cancelar");

        btnCrear.addActionListener(e -> {
            try {
                if (txtNombre.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "El nombre de la carrera es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int vueltas = Integer.parseInt(txtVueltas.getText());
                Circuito circuitoSeleccionado = circuitos.get(cbCircuito.getSelectedIndex());

                Carrera nuevaCarrera = new Carrera(txtNombre.getText(), txtFecha.getText(), vueltas, txtHora.getText(), circuitoSeleccionado);
                carreras.add(nuevaCarrera);
                sistema.agregarCarrera(nuevaCarrera);
                circuitoSeleccionado.agregarCarrera(nuevaCarrera);

                // Auto-guardar datos
                autoGuardar();

                JOptionPane.showMessageDialog(dialogo, "Carrera creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialogo.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "El número de vueltas debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnCrear);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    private void mostrarDialogoInscribirPiloto() {
        if (carreras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debes crear al menos una carrera", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialogo = new JDialog(this, "Inscribir Piloto a Carrera", true);
        dialogo.setSize(500, 300);
        dialogo.setLocationRelativeTo(this);

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
                JOptionPane.showMessageDialog(dialogo, "El piloto ya está inscrito en esta carrera", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Inscribir piloto
            carreraSeleccionada.agregarAutoPiloto(txtFechaAsignacion.getText(), autoSeleccionado, pilotoSeleccionado);

            // Auto-guardar datos
            autoGuardar();

            JOptionPane.showMessageDialog(dialogo,
                pilotoSeleccionado.getNombre() + " " + pilotoSeleccionado.getApellido() +
                " inscrito exitosamente en " + (carreraSeleccionada.getNombre() != null ? carreraSeleccionada.getNombre() : "la carrera"),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(btnInscribir);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.setVisible(true);
    }

    // ========== PANEL DE RESULTADOS ==========
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Registro de Resultados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

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

        panel.add(panelFormulario, BorderLayout.NORTH);

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

        panel.add(panelInferior, BorderLayout.CENTER);

        return panel;
    }

    // ========== PANEL DE RANKING ==========
    private JPanel crearPanelRanking() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Ranking de Pilotos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Pos", "Piloto", "Puntos", "Victorias", "Podios", "Vueltas Rápidas"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabla);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnActualizar = new JButton("[Actualizar] Actualizar Ranking");
        btnActualizar.addActionListener(e -> {
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
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        // Actualizar automáticamente al abrir
        btnActualizar.doClick();

        return panel;
    }

    // ========== PANEL DE INFORMES ==========
    private JPanel crearPanelInformes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Generación de Informes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JTextArea txtInforme = new JTextArea();
        txtInforme.setEditable(false);
        txtInforme.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(txtInforme);

        panel.add(scrollPane, BorderLayout.CENTER);

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
        btn1.addActionListener(e -> {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(baos);
            java.io.PrintStream old = System.out;
            System.setOut(ps);
            sistema.informeRankingPilotos();
            System.out.flush();
            System.setOut(old);
            txtInforme.setText(baos.toString());
        });

        // Acción para histórico de podios
        btn2.addActionListener(e -> {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(baos);
            java.io.PrintStream old = System.out;
            System.setOut(ps);
            sistema.informeHistoricoPodiosVictorias();
            System.out.flush();
            System.setOut(old);
            txtInforme.setText(baos.toString());
        });

        // Acción para autos por escudería
        btn3.addActionListener(e -> {
            String escuderia = JOptionPane.showInputDialog(this, "Nombre de la escudería:");
            if (escuderia != null && !escuderia.isEmpty()) {
                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.io.PrintStream ps = new java.io.PrintStream(baos);
                java.io.PrintStream old = System.out;
                System.setOut(ps);
                sistema.informeAutosPorEscuderia(escuderia);
                System.out.flush();
                System.setOut(old);
                txtInforme.setText(baos.toString());
            }
        });

        // Acción para mecánicos por escudería
        btn4.addActionListener(e -> {
            String escuderia = JOptionPane.showInputDialog(this, "Nombre de la escudería:");
            if (escuderia != null && !escuderia.isEmpty()) {
                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.io.PrintStream ps = new java.io.PrintStream(baos);
                java.io.PrintStream old = System.out;
                System.setOut(ps);
                sistema.informeMecanicosPorEscuderia(escuderia);
                System.out.flush();
                System.setOut(old);
                txtInforme.setText(baos.toString());
            }
        });

        // Acción para carreras por circuito
        btn5.addActionListener(e -> {
            String circuito = JOptionPane.showInputDialog(this, "Nombre del circuito:");
            if (circuito != null && !circuito.isEmpty()) {
                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.io.PrintStream ps = new java.io.PrintStream(baos);
                java.io.PrintStream old = System.out;
                System.setOut(ps);
                sistema.informeCarrerasPorCircuito(circuito);
                System.out.flush();
                System.setOut(old);
                txtInforme.setText(baos.toString());
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

        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    // ========== MÉTODOS DE PERSISTENCIA ==========

    /**
     * Guarda todos los datos del sistema en archivos
     */
    private void guardarDatos() {
        try {
            DataPersistence.guardarTodo(paises, escuderias, pilotos, autos, circuitos, carreras, mecanicos);
            JOptionPane.showMessageDialog(this,
                "Datos guardados exitosamente en la carpeta 'data'",
                "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar los datos:\n" + e.getMessage(),
                "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Recarga los datos desde archivos
     */
    private void recargarDatos() {
        try {
            paises = DataPersistence.cargarPaises();
            escuderias = DataPersistence.cargarEscuderias();
            pilotos = DataPersistence.cargarPilotos();
            autos = DataPersistence.cargarAutos();
            circuitos = DataPersistence.cargarCircuitos();
            carreras = DataPersistence.cargarCarreras();
            mecanicos = DataPersistence.cargarMecanicos();

            sistema = new Registros(autos, mecanicos, pilotos, circuitos, escuderias, paises);

            JOptionPane.showMessageDialog(this,
                "Datos recargados exitosamente.\nPor favor reinicie la aplicación para ver todos los cambios.",
                "Recarga Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar los datos:\n" + e.getMessage(),
                "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Guarda automáticamente sin mostrar mensaje (para auto-save)
     */
    private void autoGuardar() {
        try {
            DataPersistence.guardarTodo(paises, escuderias, pilotos, autos, circuitos, carreras, mecanicos);
            System.out.println("Auto-guardado exitoso");
        } catch (IOException e) {
            System.err.println("Error en auto-guardado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaF1GUI gui = new SistemaF1GUI();
            gui.setVisible(true);
        });
    }
}
