
package com.mycompany.proyectoEscuderiasUnidas;
import java.util.ArrayList;

public class Carrera {
    private String fechaRealizacion;
    private int numeroVueltas;
    private String horaRealizacion;
    private Circuito circuito;
    private ArrayList<AutoPiloto> autosPilotos=new ArrayList<>();//Permite representar la participación de múltiples combinaciones de auto y piloto en una carrera.
    
    //constructores//
     public Carrera(String fechaRealizacion,int numeroVueltas, String horaRealizacion, Circuito circuito ){
         this.fechaRealizacion=fechaRealizacion;
         this.numeroVueltas=numeroVueltas;
         this.horaRealizacion=horaRealizacion;
         this.circuito=circuito;
     }
     public Carrera(){ }
    
 //setters y getters//
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
     
     //metodos
     //metodo para agregar autos y pilotos a la carrera
     public void agregarAutoPiloto(String fechaAsignacion, Auto auto, Piloto piloto){
        AutoPiloto ap= new AutoPiloto(fechaAsignacion,auto, piloto );
        autosPilotos.add(ap);
    }
     //metodo para mostrar la info
     public void mostrarInformacion(){
         System.out.println( "Carrera en:"+ circuito + ", fecha de realizacion:"+fechaRealizacion+", hora de realizacion:"+ horaRealizacion+ ", cantidad de vueltas" + numeroVueltas);
         System.out.println("autos y pilotos participantes:");
         for ( AutoPiloto ap: autosPilotos){
             System.out.println( "piloto:" +ap.getPiloto()+ "en" + ap.getAuto());
         }
     
     }
}
