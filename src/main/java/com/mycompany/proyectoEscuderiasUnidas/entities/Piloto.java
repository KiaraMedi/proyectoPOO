
package com.mycompany.proyectoEscuderiasUnidas;
import java.util.ArrayList;

public class Piloto extends Persona {
    private int numeroCompetencia;
    private int victorias;
    private int polePosition;
    private int vueltasRapidas;
    private int podios;
    private ArrayList<PilotoEscuderia> pilotosEscuderias=new ArrayList<>();//todas las escuderias en las que corrió
    private ArrayList<AutoPiloto> autosPilotos=new ArrayList<>();// cada asignacion del piloto a un auto 
    
    //constructores//
    public Piloto(int numeroCompetencia, int victorias, int polePosition, int vueltasRapidas, int podios,String dni, String nombre, String apellido, Pais pais ){
         super (dni,nombre,apellido, pais);
         this.numeroCompetencia=numeroCompetencia;
         this.victorias=victorias;
         this.polePosition= polePosition;
         this.vueltasRapidas=vueltasRapidas;
         this.podios=podios;
    }
    public Piloto( String dni, String nombre, String apellido,Pais pais ){
         super (dni,nombre,apellido, pais);
         this.numeroCompetencia=0;
         this.victorias=0;
         this.polePosition= 0;
         this.vueltasRapidas=0;
         this.podios=0;
    }
    
       //setters y getters//
    public void setNumeroCompetencia(int numeroCompetencia){
        this.numeroCompetencia= numeroCompetencia;
    }
    public void setVictorias( int victorias){
        this.victorias= victorias;
    }
    public void setPolePosition(int polePosition){
        this.polePosition=polePosition;
    }
     public void setVueltasRapidas( int vueltasRapidas){
        this.vueltasRapidas= vueltasRapidas;
    }
    public void setPodios(int podios){
        this.podios=podios;
    }
    
    public int getNumeroCompetencia(){
        return numeroCompetencia;
    }
     public int getVictorias(){
        return victorias;
    }
    public int getPolePosition(){
        return polePosition;
    }
      public int getVueltasRapidas(){
        return vueltasRapidas;
    }
    public int getPodios(){
        return podios;
    }
    //metodo mostrar info
    public String mostrarInfoPilotos(){
        return super.toString()+ ",numero de competencias:" + numeroCompetencia+ ",victorias:"+ victorias+ ", pole position:"+ polePosition+ ", vueltas rápidas"+vueltasRapidas+ ", podios:"+ podios;
   
    }
    
}

