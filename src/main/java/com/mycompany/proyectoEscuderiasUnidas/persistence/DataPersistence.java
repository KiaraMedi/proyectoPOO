package com.mycompany.proyectoEscuderiasUnidas.persistence;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase para manejar la persistencia de datos del sistema F1
 * Utiliza serialización de Java para guardar y cargar datos
 */
public class DataPersistence {

    private static final String DATA_DIR = "data";
    private static final String PAISES_FILE = DATA_DIR + "/paises.dat";
    private static final String ESCUDERIAS_FILE = DATA_DIR + "/escuderias.dat";
    private static final String PILOTOS_FILE = DATA_DIR + "/pilotos.dat";
    private static final String AUTOS_FILE = DATA_DIR + "/autos.dat";
    private static final String CIRCUITOS_FILE = DATA_DIR + "/circuitos.dat";
    private static final String CARRERAS_FILE = DATA_DIR + "/carreras.dat";
    private static final String MECANICOS_FILE = DATA_DIR + "/mecanicos.dat";

    /**
     * Crea el directorio de datos si no existe
     */
    public static void inicializarDirectorio() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Guarda una lista en un archivo
     */
    @SuppressWarnings("unchecked")
    private static <T> void guardarLista(String archivo, ArrayList<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
    }

    /**
     * Carga una lista desde un archivo
     */
    @SuppressWarnings("unchecked")
    private static <T> ArrayList<T> cargarLista(String archivo) throws IOException, ClassNotFoundException {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<T>) ois.readObject();
        }
    }

    // ========== MÉTODOS DE GUARDADO ==========

    public static void guardarPaises(ArrayList<Pais> paises) throws IOException {
        guardarLista(PAISES_FILE, paises);
    }

    public static void guardarEscuderias(ArrayList<Escuderia> escuderias) throws IOException {
        guardarLista(ESCUDERIAS_FILE, escuderias);
    }

    public static void guardarPilotos(ArrayList<Piloto> pilotos) throws IOException {
        guardarLista(PILOTOS_FILE, pilotos);
    }

    public static void guardarAutos(ArrayList<Auto> autos) throws IOException {
        guardarLista(AUTOS_FILE, autos);
    }

    public static void guardarCircuitos(ArrayList<Circuito> circuitos) throws IOException {
        guardarLista(CIRCUITOS_FILE, circuitos);
    }

    public static void guardarCarreras(ArrayList<Carrera> carreras) throws IOException {
        guardarLista(CARRERAS_FILE, carreras);
    }

    public static void guardarMecanicos(ArrayList<Mecanico> mecanicos) throws IOException {
        guardarLista(MECANICOS_FILE, mecanicos);
    }

    /**
     * Guarda todos los datos del sistema
     */
    public static void guardarTodo(
            ArrayList<Pais> paises,
            ArrayList<Escuderia> escuderias,
            ArrayList<Piloto> pilotos,
            ArrayList<Auto> autos,
            ArrayList<Circuito> circuitos,
            ArrayList<Carrera> carreras,
            ArrayList<Mecanico> mecanicos
    ) throws IOException {
        inicializarDirectorio();
        guardarPaises(paises);
        guardarEscuderias(escuderias);
        guardarPilotos(pilotos);
        guardarAutos(autos);
        guardarCircuitos(circuitos);
        guardarCarreras(carreras);
        guardarMecanicos(mecanicos);
    }

    // ========== MÉTODOS DE CARGA ==========

    public static ArrayList<Pais> cargarPaises() throws IOException, ClassNotFoundException {
        return cargarLista(PAISES_FILE);
    }

    public static ArrayList<Escuderia> cargarEscuderias() throws IOException, ClassNotFoundException {
        return cargarLista(ESCUDERIAS_FILE);
    }

    public static ArrayList<Piloto> cargarPilotos() throws IOException, ClassNotFoundException {
        return cargarLista(PILOTOS_FILE);
    }

    public static ArrayList<Auto> cargarAutos() throws IOException, ClassNotFoundException {
        return cargarLista(AUTOS_FILE);
    }

    public static ArrayList<Circuito> cargarCircuitos() throws IOException, ClassNotFoundException {
        return cargarLista(CIRCUITOS_FILE);
    }

    public static ArrayList<Carrera> cargarCarreras() throws IOException, ClassNotFoundException {
        return cargarLista(CARRERAS_FILE);
    }

    public static ArrayList<Mecanico> cargarMecanicos() throws IOException, ClassNotFoundException {
        return cargarLista(MECANICOS_FILE);
    }

    /**
     * Verifica si existen datos guardados
     */
    public static boolean existenDatos() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            return false;
        }

        File paisesFile = new File(PAISES_FILE);
        return paisesFile.exists();
    }

    /**
     * Elimina todos los archivos de datos (para resetear el sistema)
     */
    public static void limpiarDatos() {
        File[] archivos = {
            new File(PAISES_FILE),
            new File(ESCUDERIAS_FILE),
            new File(PILOTOS_FILE),
            new File(AUTOS_FILE),
            new File(CIRCUITOS_FILE),
            new File(CARRERAS_FILE),
            new File(MECANICOS_FILE)
        };

        for (File archivo : archivos) {
            if (archivo.exists()) {
                archivo.delete();
            }
        }
    }
}
