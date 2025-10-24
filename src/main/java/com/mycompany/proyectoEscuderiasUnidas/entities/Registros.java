
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
import java.io.Serializable;
public class Registros implements Serializable { //clase para gestionar registros
    private ArrayList<Auto> autos;
    private ArrayList<Mecanico> mecanicos;
    private ArrayList<Piloto> pilotos;
    private  ArrayList<Circuito> circuitos;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Pais> paises;
    private ArrayList<Carrera> carreras;

  // Constructor que recibe las listas ya creadas
    public Registros(ArrayList<Auto> autos, ArrayList<Mecanico> mecanicos,
                   ArrayList<Piloto> pilotos, ArrayList<Circuito> circuitos,
                   ArrayList<Escuderia> escuderias, ArrayList<Pais> paises) {
        this.autos = autos;
        this.mecanicos = mecanicos;
        this.pilotos = pilotos;
        this.circuitos = circuitos;
        this.escuderias = escuderias;
        this.paises = paises;
        this.carreras = new ArrayList<>();
    }
    //métodos agregar
    public void agregarPiloto(Piloto pe){
        pilotos.add(pe) ;
    }
    public void agregarAuto (Auto auto){
        autos.add(auto);
    }
    public void agregarMecanico (Mecanico mecanico){
        mecanicos.add(mecanico);
    }
    public void agregarCircuito (Circuito circuito){
        circuitos.add(circuito);
    }
     public void agregarEscuderia(Escuderia e){
        escuderias.add(e) ;
    }
     public void agregarPais(Pais pais){
        paises.add(pais) ;
    }

     public void agregarCarrera(Carrera carrera){
        carreras.add(carrera);
    }

    // INFORMES REQUERIDOS

    // 1. Resultados detallados de carreras en un rango de fechas
    public void informeResultadosPorFechas(String fechaDesde, String fechaHasta){
        System.out.println("\n=== INFORME: Resultados de carreras ===");
        System.out.println("Período: " + fechaDesde + " al " + fechaHasta);
        boolean hayResultados = false;

        for(Carrera carrera : carreras){
            // Comparación simple de strings (asumiendo formato YYYY-MM-DD o similar)
            if(carrera.getFechaRealizacion().compareTo(fechaDesde) >= 0 &&
               carrera.getFechaRealizacion().compareTo(fechaHasta) <= 0){
                hayResultados = true;
                System.out.println("\n--- Carrera: " + carrera.getCircuito().getnombre() +
                                 " (" + carrera.getFechaRealizacion() + ") ---");
                ArrayList<ResultadoCarrera> resultados = carrera.getResultados();
                if(resultados.isEmpty()){
                    System.out.println("Sin resultados registrados");
                } else {
                    for(ResultadoCarrera r : resultados){
                        System.out.println(r.toString());
                    }
                }
            }
        }
        if(!hayResultados){
            System.out.println("No hay carreras en el rango de fechas especificado");
        }
    }

    // 2. Ranking de pilotos según puntos acumulados
    public void informeRankingPilotos(){
        System.out.println("\n=== INFORME: Ranking de Pilotos ===");
        if(pilotos.isEmpty()){
            System.out.println("No hay pilotos registrados");
            return;
        }

        // Ordenar pilotos por puntos (método burbuja simple)
        ArrayList<Piloto> rankingPilotos = new ArrayList<>(pilotos);
        for(int i = 0; i < rankingPilotos.size() - 1; i++){
            for(int j = 0; j < rankingPilotos.size() - i - 1; j++){
                if(rankingPilotos.get(j).getPuntosAcumulados() <
                   rankingPilotos.get(j+1).getPuntosAcumulados()){
                    Piloto temp = rankingPilotos.get(j);
                    rankingPilotos.set(j, rankingPilotos.get(j+1));
                    rankingPilotos.set(j+1, temp);
                }
            }
        }

        System.out.println("Pos | Piloto                    | Puntos | Victorias | Podios");
        System.out.println("----+---------------------------+--------+-----------+--------");
        for(int i = 0; i < rankingPilotos.size(); i++){
            Piloto p = rankingPilotos.get(i);
            System.out.printf("%3d | %-25s | %6d | %9d | %6d\n",
                            (i+1),
                            p.getNombre() + " " + p.getApellido(),
                            p.getPuntosAcumulados(),
                            p.getVictorias(),
                            p.getPodios());
        }
    }

    // 3. Histórico de podios y victorias de todos los pilotos
    public void informeHistoricoPodiosVictorias(){
        System.out.println("\n=== INFORME: Histórico de Podios y Victorias ===");
        if(pilotos.isEmpty()){
            System.out.println("No hay pilotos registrados");
            return;
        }

        for(Piloto p : pilotos){
            System.out.println("\nPiloto: " + p.getNombre() + " " + p.getApellido());
            System.out.println("Victorias totales: " + p.getVictorias());
            System.out.println("Podios totales: " + p.getPodios());

            ArrayList<ResultadoCarrera> resultados = p.getResultadosCarreras();
            if(!resultados.isEmpty()){
                System.out.println("Detalle de podios:");
                for(ResultadoCarrera r : resultados){
                    if(r.getPosicion() <= 3){
                        System.out.println("  - Posición " + r.getPosicion() +
                                         " en " + r.getCarrera().getCircuito().getnombre() +
                                         " (" + r.getCarrera().getFechaRealizacion() + ")");
                    }
                }
            }
        }
    }

    // 4. Histórico de podios y victorias de un piloto determinado
    public void informeHistoricoPiloto(String dniPiloto){
        System.out.println("\n=== INFORME: Histórico de Piloto ===");
        Piloto pilotoEncontrado = null;

        for(Piloto p : pilotos){
            if(p.getDni().equals(dniPiloto)){
                pilotoEncontrado = p;
                break;
            }
        }

        if(pilotoEncontrado == null){
            System.out.println("No se encontró piloto con DNI: " + dniPiloto);
            return;
        }

        System.out.println("Piloto: " + pilotoEncontrado.getNombre() + " " +
                         pilotoEncontrado.getApellido());
        System.out.println("Número de competencia: " + pilotoEncontrado.getNumeroCompetencia());
        System.out.println("Victorias: " + pilotoEncontrado.getVictorias());
        System.out.println("Podios: " + pilotoEncontrado.getPodios());
        System.out.println("Pole positions: " + pilotoEncontrado.getPolePosition());
        System.out.println("Vueltas rápidas: " + pilotoEncontrado.getVueltasRapidas());
        System.out.println("Puntos acumulados: " + pilotoEncontrado.getPuntosAcumulados());

        ArrayList<ResultadoCarrera> resultados = pilotoEncontrado.getResultadosCarreras();
        if(!resultados.isEmpty()){
            System.out.println("\nHistorial de carreras:");
            for(ResultadoCarrera r : resultados){
                System.out.println("  " + r.getCarrera().getFechaRealizacion() +
                                 " - " + r.getCarrera().getCircuito().getnombre() +
                                 " - Posición: " + r.getPosicion() +
                                 " - Puntos: " + r.getPuntosObtenidos());
            }
        }
    }

    // 5. Informe de autos utilizados por escudería en diferentes carreras
    public void informeAutosPorEscuderia(String nombreEscuderia){
        System.out.println("\n=== INFORME: Autos por Escudería ===");
        Escuderia escuderiaEncontrada = null;

        for(Escuderia e : escuderias){
            if(e.getEscuderia().equalsIgnoreCase(nombreEscuderia)){
                escuderiaEncontrada = e;
                break;
            }
        }

        if(escuderiaEncontrada == null){
            System.out.println("No se encontró la escudería: " + nombreEscuderia);
            return;
        }

        System.out.println("Escudería: " + escuderiaEncontrada.getEscuderia());
        System.out.println("\nAutos utilizados en carreras:");

        for(Auto auto : autos){
            if(auto.getEscuderia() != null &&
               auto.getEscuderia().getEscuderia().equals(nombreEscuderia)){
                System.out.println("\nAuto: " + auto.getModelo() + " - Motor: " + auto.getMotor());

                // Buscar carreras donde se usó este auto
                for(Carrera carrera : carreras){
                    for(AutoPiloto ap : carrera.getAutoPilotos()){
                        if(ap.getAuto().equals(auto)){
                            System.out.println("  - Carrera: " + carrera.getCircuito().getnombre() +
                                             " (" + carrera.getFechaRealizacion() + ")" +
                                             " - Piloto: " + ap.getPiloto().getNombre());
                        }
                    }
                }
            }
        }
    }

    // 6. Informe de años de experiencia y especialidad de mecánicos por escudería
    public void informeMecanicosPorEscuderia(String nombreEscuderia){
        System.out.println("\n=== INFORME: Mecánicos por Escudería ===");
        Escuderia escuderiaEncontrada = null;

        for(Escuderia e : escuderias){
            if(e.getEscuderia().equalsIgnoreCase(nombreEscuderia)){
                escuderiaEncontrada = e;
                break;
            }
        }

        if(escuderiaEncontrada == null){
            System.out.println("No se encontró la escudería: " + nombreEscuderia);
            return;
        }

        System.out.println("Escudería: " + escuderiaEncontrada.getEscuderia());
        System.out.println("\nMecánicos:");
        System.out.println("Nombre                    | Especialidad    | Años Experiencia");
        System.out.println("--------------------------|-----------------|------------------");

        for(Mecanico m : mecanicos){
            if(m.getEscuderias().contains(escuderiaEncontrada)){
                System.out.printf("%-25s | %-15s | %d años\n",
                                m.getNombre() + " " + m.getApellido(),
                                m.getEspecialidad(),
                                m.getAniosExperiencia());
            }
        }
    }

    // 7. Cantidad de veces que un piloto corrió en un circuito determinado
    public void informePilotoEnCircuito(String dniPiloto, String nombreCircuito){
        System.out.println("\n=== INFORME: Participación de Piloto en Circuito ===");
        Piloto pilotoEncontrado = null;

        for(Piloto p : pilotos){
            if(p.getDni().equals(dniPiloto)){
                pilotoEncontrado = p;
                break;
            }
        }

        if(pilotoEncontrado == null){
            System.out.println("No se encontró piloto con DNI: " + dniPiloto);
            return;
        }

        int contador = 0;
        System.out.println("Piloto: " + pilotoEncontrado.getNombre() + " " +
                         pilotoEncontrado.getApellido());
        System.out.println("Circuito: " + nombreCircuito);
        System.out.println("\nCarreras disputadas:");

        for(ResultadoCarrera r : pilotoEncontrado.getResultadosCarreras()){
            if(r.getCarrera().getCircuito().getnombre().equalsIgnoreCase(nombreCircuito)){
                contador++;
                System.out.println("  - Fecha: " + r.getCarrera().getFechaRealizacion() +
                                 " - Posición: " + r.getPosicion());
            }
        }

        System.out.println("\nTotal de veces que corrió en " + nombreCircuito + ": " + contador);
    }

    // 8. Cantidad de carreras en un circuito determinado
    public void informeCarrerasPorCircuito(String nombreCircuito){
        System.out.println("\n=== INFORME: Carreras por Circuito ===");
        System.out.println("Circuito: " + nombreCircuito);

        int contador = 0;
        System.out.println("\nCarreras disputadas:");

        for(Carrera carrera : carreras){
            if(carrera.getCircuito().getnombre().equalsIgnoreCase(nombreCircuito)){
                contador++;
                System.out.println("  - Fecha: " + carrera.getFechaRealizacion() +
                                 " - Hora: " + carrera.getHoraRealizacion() +
                                 " - Vueltas: " + carrera.getNumeroVueltas());
            }
        }

        System.out.println("\nTotal de carreras en " + nombreCircuito + ": " + contador);
    }



    public void mostrarInfoAutos(){
        System.out.println("autos:");
        for(Auto a:autos){
            System.out.println(a.getModelo()+ a.getMotor());
        }
    }
    public void mostrarInfoPilotos(){
        System.out.println("pilotos:");
        for(Piloto pe: pilotos){
            System.out.println(pe);
        }
    }
    public void mostrarInfoMecanicos(){
        System.out.println("mecanicos:");
        for(Mecanico m:mecanicos){
            System.out.println(m.getNombre()+"-"+ m.getEspecialidad());
        }
    }
    public void mostrarInfoCircuitos(){
        System.out.println("circuitos:");
        for(Circuito c: circuitos){
            System.out.println(c);
        }
    }
    public void mostrarInfoEscuderias(){
        System.out.println("Escuderias:");
        for(Escuderia e: escuderias){
            System.out.println(e);
        }
    }
     public void mostrarInfoPaises(){
         System.out.println("paises:");
        for(Pais p: paises){
            System.out.println(p);
        }
    }
}
