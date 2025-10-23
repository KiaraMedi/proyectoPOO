
package com.mycompany.proyectoEscuderiasUnidas.entities;

public class ResultadoCarrera {
    private Carrera carrera;
    private AutoPiloto autoPiloto;
    private int posicion;
    private boolean vueltaRapida;
    private int puntosObtenidos;

    // Constructores
    public ResultadoCarrera() {}

    public ResultadoCarrera(Carrera carrera, AutoPiloto autoPiloto, int posicion, boolean vueltaRapida) {
        this.carrera = carrera;
        this.autoPiloto = autoPiloto;
        this.posicion = posicion;
        this.vueltaRapida = vueltaRapida;
        this.puntosObtenidos = calcularPuntos();
    }

    // Setters y Getters
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void setAutoPiloto(AutoPiloto autoPiloto) {
        this.autoPiloto = autoPiloto;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
        this.puntosObtenidos = calcularPuntos(); // Recalcular al cambiar posición
    }

    public void setVueltaRapida(boolean vueltaRapida) {
        this.vueltaRapida = vueltaRapida;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public AutoPiloto getAutoPiloto() {
        return autoPiloto;
    }

    public int getPosicion() {
        return posicion;
    }

    public boolean isVueltaRapida() {
        return vueltaRapida;
    }

    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    // Método para calcular puntos según la posición
    public int calcularPuntos() {
        int puntosBase = 0;

        // Usar el enum Puntos para asignar puntos según posición
        switch(posicion) {
            case 1:
                puntosBase = Puntos.PRIMERO.getPuntos();
                break;
            case 2:
                puntosBase = Puntos.SEGUNDO.getPuntos();
                break;
            case 3:
                puntosBase = Puntos.TERCERO.getPuntos();
                break;
            case 4:
                puntosBase = Puntos.CUARTO.getPuntos();
                break;
            case 5:
                puntosBase = Puntos.QUINTO.getPuntos();
                break;
            case 6:
                puntosBase = Puntos.SEXTO.getPuntos();
                break;
            case 7:
                puntosBase = Puntos.SEPTIMO.getPuntos();
                break;
            case 8:
                puntosBase = Puntos.OCTAVO.getPuntos();
                break;
            case 9:
                puntosBase = Puntos.NOVENO.getPuntos();
                break;
            case 10:
                puntosBase = Puntos.DECIMO.getPuntos();
                break;
            default:
                puntosBase = 0; // Posiciones 11+ no suman puntos
        }

        // Punto adicional por vuelta rápida (solo si terminó en top 10)
        if (vueltaRapida && posicion <= 10) {
            puntosBase += 1;
        }

        return puntosBase;
    }

    // Método para mostrar información
    public void mostrarInfoResultado() {
        System.out.println("Resultado de carrera:");
        System.out.println("Piloto: " + autoPiloto.getPiloto().getNombre() + " " + autoPiloto.getPiloto().getApellido());
        System.out.println("Auto: " + autoPiloto.getAuto().getModelo());
        System.out.println("Posición: " + posicion);
        System.out.println("Vuelta rápida: " + (vueltaRapida ? "Sí" : "No"));
        System.out.println("Puntos obtenidos: " + puntosObtenidos);
        System.out.println("Carrera: " + carrera.getCircuito().getnombre() + " - " + carrera.getFechaRealizacion());
    }

    @Override
    public String toString() {
        return "Pos " + posicion + ": " + autoPiloto.getPiloto().getNombre() +
               " (" + autoPiloto.getAuto().getModelo() + ") - " + puntosObtenidos + " pts" +
               (vueltaRapida ? " + VR" : "");
    }
}
