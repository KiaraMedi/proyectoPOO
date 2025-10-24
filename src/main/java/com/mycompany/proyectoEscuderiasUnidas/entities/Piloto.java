
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Piloto extends Persona implements Serializable {
    private int numeroCompetencia;
    private int victorias;
    private int polePosition;
    private int vueltasRapidas;
    private int podios;
    private int puntosAcumulados; // Puntos totales en el campeonato
    private ArrayList<PilotoEscuderia> pilotosEscuderias=new ArrayList<>();//todas las escuderias en las que corrió
    private ArrayList<AutoPiloto> autosPilotos=new ArrayList<>();// cada asignacion del piloto a un auto
    private ArrayList<ResultadoCarrera> resultadosCarreras=new ArrayList<>();// resultados en todas las carreras 
    
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
         this.puntosAcumulados=0;
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

    public int getPuntosAcumulados(){
        return puntosAcumulados;
    }

    public ArrayList<ResultadoCarrera> getResultadosCarreras(){
        return resultadosCarreras;
    }

    //metodos para gestionar resultados y estadísticas
    public void agregarResultadoCarrera(ResultadoCarrera resultado){
        if(resultado != null){
            resultadosCarreras.add(resultado);
            puntosAcumulados += resultado.getPuntosObtenidos();

            // Actualizar estadísticas automáticamente
            if(resultado.getPosicion() == 1){
                victorias++;
            }
            if(resultado.getPosicion() <= 3){
                podios++;
            }
            if(resultado.isVueltaRapida()){
                vueltasRapidas++;
            }
            numeroCompetencia++;
        }
    }

    // Método para calcular puntos totales recorriendo todos los resultados
    public int calcularPuntosTotales(){
        int total = 0;
        for(ResultadoCarrera resultado : resultadosCarreras){
            total += resultado.getPuntosObtenidos();
        }
        return total;
    }

    // Método para obtener victorias
    public int contarVictorias(){
        int conteo = 0;
        for(ResultadoCarrera resultado : resultadosCarreras){
            if(resultado.getPosicion() == 1){
                conteo++;
            }
        }
        return conteo;
    }

    // Método para obtener podios
    public int contarPodios(){
        int conteo = 0;
        for(ResultadoCarrera resultado : resultadosCarreras){
            if(resultado.getPosicion() <= 3){
                conteo++;
            }
        }
        return conteo;
    }

    //metodo mostrar info
    public String mostrarInfoPilotos(){
        return super.toString()+ ",numero de competencias:" + numeroCompetencia+
               ",victorias:"+ victorias+ ", pole position:"+ polePosition+
               ", vueltas rápidas:"+ vueltasRapidas+ ", podios:"+ podios+
               ", puntos acumulados:"+ puntosAcumulados;

    }

}

