
package com.mycompany.proyectoEscuderiasUnidas;

import java.util.ArrayList;
public class AutoPiloto {
    private String fechaAsignacion;
    private ArrayList<Carrera> carreras= new ArrayList<>();//permite manejar las carreras en las que participó esa combinación de auto y piloto.
    private Auto auto;
    private Piloto piloto;
    
    public AutoPiloto() {}
    public AutoPiloto(String fechaAsignacion, Auto auto, Piloto piloto){
        this.fechaAsignacion=fechaAsignacion;
        this.auto=auto;
        this.piloto=piloto;
    }
    public void setFechaAsignacion(String fechaAsignacion){
        this.fechaAsignacion=fechaAsignacion;
    }
    public void setAuto(Auto auto){
        this.auto=auto;
    }
    public void setPiloto(Piloto piloto){
        this.piloto=piloto;
    }
    
    public String getFechaAsignacion(){
        return fechaAsignacion;
    }
    public Auto getAuto(){
        return auto;
    }
    public Piloto getPiloto(){
        return piloto;
    }
    public ArrayList<Carrera> getCarreras(){
        return carreras;
    }
    
    
    //metodo agregar
    public void agregarCarrera(){
        for (Carrera carrera:carreras){
         carreras.add(carrera);
        }
    }
    
    //metodo mostrarInformacion
    public void mostrarInfoAutoPiloto(){
       System.out.println( "auto:"+auto+", piloto"+piloto+", fecha de asignacion"+ fechaAsignacion);
        for (Carrera carrera: carreras){
            carrera.mostrarInformacion();
        }
    }
}
