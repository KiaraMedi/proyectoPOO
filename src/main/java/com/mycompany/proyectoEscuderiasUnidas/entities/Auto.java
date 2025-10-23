
package com.mycompany.proyectoEscuderiasUnidas.entities;

import java.util.ArrayList;

public class Auto {
    private String modelo;
    private String motor;
    private Escuderia escuderia;
    private ArrayList<AutoPiloto> autosPilotos=new ArrayList<>();// cada asignacion del auto al piloto

    // constructores// 
    public Auto (){ }
    public Auto (String modelo, String motor, Escuderia escuderia){
        this.modelo= modelo;
        this.motor= motor;
        this.escuderia=escuderia;
    }
   
    //setters y getters//
    public void setModelo(String modelo){
        this.modelo= modelo;
    }
    public void setMotor(String motor){
        this.motor= motor;
    }
    public void setEscuderia(Escuderia escuderia){
        this.escuderia=escuderia;
    }
    
    public String getModelo(){
        return modelo;
    }
     public String getMotor(){
        return motor;
    }
     public Escuderia getEscuderia(){
        return escuderia;
    }
     
    // metodo agregar
     public void agregarAutoPiloto( AutoPiloto ap){
         autosPilotos.add(ap);
     }
     
     //mostrar información
     public void mostrarInfoAuto(){
         System.out.println ("modelo:"+ modelo+",motor"+motor+", escuderia:"+escuderia+", autopiloto:");
         for (AutoPiloto ap : autosPilotos){
              ap.mostrarInfoAutoPiloto();
         }
     }
}
