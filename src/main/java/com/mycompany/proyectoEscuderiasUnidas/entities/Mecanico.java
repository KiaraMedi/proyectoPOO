
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
import java.io.Serializable;

public class Mecanico extends Persona implements Serializable {
    private Especialidad especialidad;
    private int aniosExperiencia;
     private ArrayList<Escuderia> escuderias=new ArrayList<>();//guarda todas las escuderias donde trabaja ese mecanico
    
    //constructores//
    public Mecanico(int aniosExperiencia, Especialidad especialidad,String dni, String nombre, String apellido, Pais pais ){
         super (dni,nombre,apellido, pais);
         this.aniosExperiencia=aniosExperiencia;
         this.especialidad=especialidad;
    }
    public Mecanico( String dni, String nombre, String apellido,Pais pais ){
         super (dni,nombre,apellido, pais);
         this.aniosExperiencia=0;
         
    } 
 //setters y getters//
    public void setEspecialidad(Especialidad especialidad){
        this.especialidad=especialidad;
    }
    public void setAniosExperiencia(int aniosExperiencia){
        this.aniosExperiencia= aniosExperiencia;
    }
    
    public Especialidad getEspecialidad(){
        return especialidad;
    }
     public int getAniosExperiencia(){
        return aniosExperiencia;
    }
     public ArrayList<Escuderia> getEscuderias() {
         return escuderias; 
     }
    
     //método mostrar info
     public String mostrarInfo(){
        return super.toString()+ "especialidad:"+ especialidad+", años de experiencia:"+ aniosExperiencia;
     }
}
