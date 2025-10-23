/*
 * Sistema de Gestión de Escuderías de Fórmula 1
 * Ejemplo completo de uso del sistema
 */
package com.mycompany.proyectoEscuderiasUnidas;

import com.mycompany.proyectoEscuderiasUnidas.entities.*;
import java.util.ArrayList;

public class ProyectoPOO {
   public static void main (String [] args){
       System.out.println("===================================================");
       System.out.println("   SISTEMA DE GESTION - ESCUDERIAS UNIDAS F1");
       System.out.println("===================================================\n");

       // ========== PASO 1: CREAR PAÍSES ==========
       System.out.println(">>> PASO 1: Registrando países...");
       Pais argentina = new Pais(1, "Argentina");
       Pais brasil = new Pais(2, "Brasil");
       Pais italia = new Pais(3, "Italia");
       Pais alemania = new Pais(4, "Alemania");
       Pais monaco = new Pais(5, "Monaco");
       System.out.println("Países registrados exitosamente.\n");

       // ========== PASO 2: CREAR ESCUDERÍAS ==========
       System.out.println(">>> PASO 2: Registrando escuderías...");
       Escuderia ferrari = new Escuderia("Ferrari", italia);
       Escuderia redBull = new Escuderia("Red Bull Racing", alemania);
       Escuderia mercedes = new Escuderia("Mercedes AMG", alemania);
       System.out.println("Escuderías registradas exitosamente.\n");

       // ========== PASO 3: CREAR PILOTOS ==========
       System.out.println(">>> PASO 3: Registrando pilotos...");
       Piloto hamilton = new Piloto("44123456", "Lewis", "Hamilton", alemania);
       Piloto verstappen = new Piloto("33789012", "Max", "Verstappen", alemania);
       Piloto leclerc = new Piloto("16345678", "Charles", "Leclerc", monaco);
       Piloto perez = new Piloto("11567890", "Sergio", "Perez", argentina);
       System.out.println("Pilotos registrados exitosamente.\n");

       // ========== PASO 4: ASIGNAR PILOTOS A ESCUDERÍAS ==========
       System.out.println(">>> PASO 4: Asignando pilotos a escuderías...");
       ferrari.agregarPiloto("2024-01-01", "2024-12-31", leclerc, ferrari);
       redBull.agregarPiloto("2024-01-01", "2024-12-31", verstappen, redBull);
       redBull.agregarPiloto("2024-01-01", "2024-12-31", perez, redBull);
       mercedes.agregarPiloto("2024-01-01", "2024-12-31", hamilton, mercedes);
       System.out.println("Pilotos asignados a escuderías.\n");

       // ========== PASO 5: CREAR AUTOS ==========
       System.out.println(">>> PASO 5: Registrando autos...");
       Auto ferraríSF24 = new Auto("SF-24", "Ferrari V6 Turbo", ferrari);
       Auto redBullRB20 = new Auto("RB20", "Honda RBPT V6", redBull);
       Auto mercedesW15 = new Auto("W15", "Mercedes-AMG M14", mercedes);

       ferrari.agregarAuto(ferraríSF24);
       redBull.agregarAuto(redBullRB20);
       mercedes.agregarAuto(mercedesW15);
       System.out.println("Autos registrados exitosamente.\n");

       // ========== PASO 6: CREAR MECÁNICOS ==========
       System.out.println(">>> PASO 6: Registrando mecánicos...");
       Mecanico mec1 = new Mecanico(15, Especialidad.MOTOR, "30111222", "Giovanni", "Rossi", italia);
       Mecanico mec2 = new Mecanico(10, Especialidad.CHASIS, "30222333", "Hans", "Schmidt", alemania);
       Mecanico mec3 = new Mecanico(8, Especialidad.NEUMATICOS, "30333444", "Pierre", "Dubois", monaco);

       ferrari.agregarMecanico(mec1);
       redBull.agregarMecanico(mec2);
       mercedes.agregarMecanico(mec3);
       System.out.println("Mecánicos asignados a escuderías.\n");

       // ========== PASO 7: CREAR CIRCUITOS ==========
       System.out.println(">>> PASO 7: Registrando circuitos...");
       Circuito monza = new Circuito("Autodromo di Monza", 5793, italia);
       Circuito monaco_circuit = new Circuito("Circuit de Monaco", 3337, monaco);
       Circuito interlagos = new Circuito("Autodromo de Interlagos", 4309, brasil);
       System.out.println("Circuitos registrados exitosamente.\n");

       // ========== PASO 8: CREAR CARRERAS ==========
       System.out.println(">>> PASO 8: Planificando carreras...");
       Carrera carreraMonza = new Carrera("2024-09-01", 53, "14:00", monza);
       Carrera carreraMonaco = new Carrera("2024-05-26", 78, "15:00", monaco_circuit);
       Carrera carreraInterlagos = new Carrera("2024-11-03", 71, "14:00", interlagos);

       monza.agregarCarrera(carreraMonza);
       monaco_circuit.agregarCarrera(carreraMonaco);
       interlagos.agregarCarrera(carreraInterlagos);
       System.out.println("Carreras planificadas exitosamente.\n");

       // ========== PASO 9: ASIGNAR PILOTOS A AUTOS PARA CARRERAS ==========
       System.out.println(">>> PASO 9: Asignando pilotos a autos para las carreras...");

       // Carrera Monza - Crear e inscribir pilotos
       carreraMonza.agregarAutoPiloto("2024-08-31", ferraríSF24, leclerc);
       carreraMonza.agregarAutoPiloto("2024-08-31", redBullRB20, verstappen);
       carreraMonza.agregarAutoPiloto("2024-08-31", mercedesW15, hamilton);

       // Obtener referencias a los AutoPiloto inscritos
       AutoPiloto ap1_monza = carreraMonza.getAutoPilotos().get(0);
       AutoPiloto ap2_monza = carreraMonza.getAutoPilotos().get(1);
       AutoPiloto ap3_monza = carreraMonza.getAutoPilotos().get(2);

       // Carrera Monaco - Crear e inscribir pilotos
       carreraMonaco.agregarAutoPiloto("2024-05-25", ferraríSF24, leclerc);
       carreraMonaco.agregarAutoPiloto("2024-05-25", redBullRB20, verstappen);
       carreraMonaco.agregarAutoPiloto("2024-05-25", mercedesW15, hamilton);

       // Obtener referencias a los AutoPiloto inscritos
       AutoPiloto ap1_monaco = carreraMonaco.getAutoPilotos().get(0);
       AutoPiloto ap2_monaco = carreraMonaco.getAutoPilotos().get(1);
       AutoPiloto ap3_monaco = carreraMonaco.getAutoPilotos().get(2);

       System.out.println("Asignaciones completadas.\n");

       // ========== PASO 10: REGISTRAR RESULTADOS DE CARRERAS ==========
       System.out.println(">>> PASO 10: Registrando resultados de carreras...");
       System.out.println("\n--- Resultados Gran Premio de Monza ---");
       boolean r1 = carreraMonza.registrarResultado(ap1_monza, 1, true); // Leclerc 1° con vuelta rápida
       boolean r2 = carreraMonza.registrarResultado(ap2_monza, 2, false); // Verstappen 2°
       boolean r3 = carreraMonza.registrarResultado(ap3_monza, 3, false); // Hamilton 3°

       if(r1 && r2 && r3){
           System.out.println("Resultados de Monza registrados exitosamente.");

           // Actualizar estadísticas de pilotos
           ResultadoCarrera res1 = carreraMonza.getResultados().get(0);
           ResultadoCarrera res2 = carreraMonza.getResultados().get(1);
           ResultadoCarrera res3 = carreraMonza.getResultados().get(2);

           leclerc.agregarResultadoCarrera(res1);
           verstappen.agregarResultadoCarrera(res2);
           hamilton.agregarResultadoCarrera(res3);
       }

       System.out.println("\n--- Resultados Gran Premio de Monaco ---");
       r1 = carreraMonaco.registrarResultado(ap2_monaco, 1, false); // Verstappen 1°
       r2 = carreraMonaco.registrarResultado(ap1_monaco, 2, true); // Leclerc 2° con vuelta rápida
       r3 = carreraMonaco.registrarResultado(ap3_monaco, 4, false); // Hamilton 4°

       if(r1 && r2 && r3){
           System.out.println("Resultados de Monaco registrados exitosamente.");

           ResultadoCarrera res1m = carreraMonaco.getResultados().get(0);
           ResultadoCarrera res2m = carreraMonaco.getResultados().get(1);
           ResultadoCarrera res3m = carreraMonaco.getResultados().get(2);

           verstappen.agregarResultadoCarrera(res1m);
           leclerc.agregarResultadoCarrera(res2m);
           hamilton.agregarResultadoCarrera(res3m);
       }

       System.out.println("\nResultados registrados y estadísticas actualizadas.\n");

       // ========== PASO 11: CREAR SISTEMA DE REGISTROS ==========
       System.out.println(">>> PASO 11: Inicializando sistema de registros...");
       ArrayList<Auto> listaAutos = new ArrayList<>();
       listaAutos.add(ferraríSF24);
       listaAutos.add(redBullRB20);
       listaAutos.add(mercedesW15);

       ArrayList<Mecanico> listaMecanicos = new ArrayList<>();
       listaMecanicos.add(mec1);
       listaMecanicos.add(mec2);
       listaMecanicos.add(mec3);

       ArrayList<Piloto> listaPilotos = new ArrayList<>();
       listaPilotos.add(hamilton);
       listaPilotos.add(verstappen);
       listaPilotos.add(leclerc);
       listaPilotos.add(perez);

       ArrayList<Circuito> listaCircuitos = new ArrayList<>();
       listaCircuitos.add(monza);
       listaCircuitos.add(monaco_circuit);
       listaCircuitos.add(interlagos);

       ArrayList<Escuderia> listaEscuderias = new ArrayList<>();
       listaEscuderias.add(ferrari);
       listaEscuderias.add(redBull);
       listaEscuderias.add(mercedes);

       ArrayList<Pais> listaPaises = new ArrayList<>();
       listaPaises.add(argentina);
       listaPaises.add(brasil);
       listaPaises.add(italia);
       listaPaises.add(alemania);
       listaPaises.add(monaco);

       Registros sistema = new Registros(listaAutos, listaMecanicos, listaPilotos,
                                         listaCircuitos, listaEscuderias, listaPaises);

       sistema.agregarCarrera(carreraMonza);
       sistema.agregarCarrera(carreraMonaco);
       sistema.agregarCarrera(carreraInterlagos);

       System.out.println("Sistema de registros inicializado.\n");

       // ========== PASO 12: DEMOSTRAR FUNCIONALIDADES ==========
       System.out.println("\n===================================================");
       System.out.println("         DEMOSTRACION DE FUNCIONALIDADES");
       System.out.println("===================================================\n");

       // 1. Mostrar información de una carrera con resultados
       System.out.println("\n========== 1. Información de Carrera ==========");
       carreraMonza.mostrarInformacion();

       // 2. Mostrar ganador y podio
       System.out.println("\n========== 2. Ganador y Podio de Monza ==========");
       ResultadoCarrera ganador = carreraMonza.getGanador();
       if(ganador != null){
           System.out.println("Ganador: " + ganador.getAutoPiloto().getPiloto().getNombre() +
                            " " + ganador.getAutoPiloto().getPiloto().getApellido() +
                            " - Puntos: " + ganador.getPuntosObtenidos());
       }

       System.out.println("\nPodio completo:");
       for(ResultadoCarrera r : carreraMonza.getPodio()){
           System.out.println(r.toString());
       }

       // 3. Mostrar información de un piloto
       System.out.println("\n========== 3. Información de Piloto ==========");
       System.out.println(leclerc.mostrarInfoPilotos());

       // ========== PASO 13: GENERAR INFORMES ==========
       System.out.println("\n\n===================================================");
       System.out.println("              GENERACION DE INFORMES");
       System.out.println("===================================================");

       // Informe 1: Ranking de pilotos
       sistema.informeRankingPilotos();

       // Informe 2: Resultados por rango de fechas
       sistema.informeResultadosPorFechas("2024-05-01", "2024-09-30");

       // Informe 3: Histórico de un piloto
       sistema.informeHistoricoPiloto("16345678"); // Leclerc

       // Informe 4: Autos por escudería
       sistema.informeAutosPorEscuderia("Ferrari");

       // Informe 5: Mecánicos por escudería
       sistema.informeMecanicosPorEscuderia("Red Bull Racing");

       // Informe 6: Piloto en circuito
       sistema.informePilotoEnCircuito("16345678", "Autodromo di Monza");

       // Informe 7: Carreras por circuito
       sistema.informeCarrerasPorCircuito("Circuit de Monaco");

       // Informe 8: Histórico general de podios
       sistema.informeHistoricoPodiosVictorias();

       System.out.println("\n===================================================");
       System.out.println("         SISTEMA EJECUTADO EXITOSAMENTE");
       System.out.println("===================================================\n");
   }
}
