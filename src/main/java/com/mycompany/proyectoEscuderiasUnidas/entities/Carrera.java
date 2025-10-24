
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Carrera implements Serializable {
    private String nombre;  // Nombre de la carrera (ej: "GP de Mónaco 2024")
    private String fechaRealizacion;
    private int numeroVueltas;
    private String horaRealizacion;
    private Circuito circuito;
    private ArrayList<AutoPiloto> autosPilotos=new ArrayList<>();//Permite representar la participación de múltiples combinaciones de auto y piloto en una carrera.
    private ArrayList<ResultadoCarrera> resultados=new ArrayList<>();//Resultados de la carrera

    //constructores//
     public Carrera(String nombre, String fechaRealizacion,int numeroVueltas, String horaRealizacion, Circuito circuito ){
         this.nombre=nombre;
         this.fechaRealizacion=fechaRealizacion;
         this.numeroVueltas=numeroVueltas;
         this.horaRealizacion=horaRealizacion;
         this.circuito=circuito;
     }

     // Constructor antiguo para compatibilidad (usa nombre del circuito)
     public Carrera(String fechaRealizacion,int numeroVueltas, String horaRealizacion, Circuito circuito ){
         this.nombre = "GP " + circuito.getnombre();
         this.fechaRealizacion=fechaRealizacion;
         this.numeroVueltas=numeroVueltas;
         this.horaRealizacion=horaRealizacion;
         this.circuito=circuito;
     }

     public Carrera(){ }
    
 //setters y getters//
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setFechaRealizacoin(String fechaRealizacion){
        this.fechaRealizacion= fechaRealizacion;
    }
    public void setHoraRealizacion(String horaRealizacion){
        this.horaRealizacion=horaRealizacion;
    }
    public void setNumeroVueltas(int numeroVueltas){
        this.numeroVueltas=numeroVueltas;
    }
    public void setCircuito(Circuito circuito){
        this.circuito=circuito;
    }

    public String getNombre(){
        return nombre;
    }
    public String getFechaRealizacion(){
        return fechaRealizacion;
    }
     public String getHoraRealizacion(){
        return horaRealizacion;
    }
     public int getNumeroVueltas(){
         return numeroVueltas;
     }
     public Circuito getCircuito(){
         return circuito;
     }
         public ArrayList<AutoPiloto> getAutoPilotos() {
             return autosPilotos;
         }

         public ArrayList<ResultadoCarrera> getResultados() {
             return resultados;
         }

     //metodos
     //metodo para agregar autos y pilotos a la carrera
     public void agregarAutoPiloto(String fechaAsignacion, Auto auto, Piloto piloto){
        AutoPiloto ap= new AutoPiloto(fechaAsignacion,auto, piloto );
        autosPilotos.add(ap);
    }

     //metodo para registrar resultado de un piloto en la carrera
     public boolean registrarResultado(AutoPiloto autoPiloto, int posicion, boolean vueltaRapida){
         // Validar que el AutoPiloto participe en esta carrera
         if(!autosPilotos.contains(autoPiloto)){
             System.out.println("Error: El piloto no está inscrito en esta carrera");
             return false;
         }

         // Validar que la posición no esté ya asignada
         for(ResultadoCarrera r : resultados){
             if(r.getPosicion() == posicion){
                 System.out.println("Error: La posición " + posicion + " ya está asignada");
                 return false;
             }
         }

         // Validar que el piloto no tenga ya un resultado en esta carrera
         for(ResultadoCarrera r : resultados){
             if(r.getAutoPiloto().equals(autoPiloto)){
                 System.out.println("Error: El piloto ya tiene un resultado registrado en esta carrera");
                 return false;
             }
         }

         // Crear y agregar el resultado
         ResultadoCarrera resultado = new ResultadoCarrera(this, autoPiloto, posicion, vueltaRapida);
         resultados.add(resultado);
         return true;
     }

     //metodo para obtener el ganador de la carrera
     public ResultadoCarrera getGanador(){
         for(ResultadoCarrera r : resultados){
             if(r.getPosicion() == 1){
                 return r;
             }
         }
         return null;
     }

     //metodo para obtener el podio (top 3)
     public ArrayList<ResultadoCarrera> getPodio(){
         ArrayList<ResultadoCarrera> podio = new ArrayList<>();
         for(int i = 1; i <= 3; i++){
             for(ResultadoCarrera r : resultados){
                 if(r.getPosicion() == i){
                     podio.add(r);
                 }
             }
         }
         return podio;
     }
     //metodo para mostrar la info
     public void mostrarInformacion(){
         System.out.println( "Carrera en:"+ circuito.getnombre() + ", fecha de realizacion:"+fechaRealizacion+", hora de realizacion:"+ horaRealizacion+ ", cantidad de vueltas" + numeroVueltas);
         System.out.println("autos y pilotos participantes:");
         for ( AutoPiloto ap: autosPilotos){
             System.out.println( "piloto:" +ap.getPiloto().getNombre()+ " en " + ap.getAuto().getModelo());
         }

         if(!resultados.isEmpty()){
             System.out.println("\nResultados:");
             for(ResultadoCarrera r : resultados){
                 System.out.println(r.toString());
             }
         }

     }
}
