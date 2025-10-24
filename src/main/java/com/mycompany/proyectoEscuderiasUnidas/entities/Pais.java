
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
import java.io.Serializable;
public class Pais implements Serializable {
    private int id;
    private String descripcion;
    private ArrayList<Persona> personas= new ArrayList<>();//guarda todas las personas asociadas a ese país.
    private ArrayList<Escuderia> escuderias=new ArrayList<>();//almacena todas las escuderías asociadas a un país.
    private ArrayList<Circuito> circuitos=new ArrayList<>();//almacenar todos los circuitos que pertenecen a un país
    private ArrayList<Carrera> carreras=new ArrayList<>();//guardar múltiples carreras asociadas a un país.
    //constructores//
    public Pais(){ }
    public Pais (int id, String descripcion){
        this.id=id;
        this.descripcion= descripcion;
    }
    
    //setters y getters//
    public void setDescripcion( String descripcion){
        this.descripcion=descripcion;
    }
    public void setId( int id){
        this.id=id;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public int getId(){
        return id;
    }
    
    //métodos
    //metodos agregar
    public void agregarPersona(String dni, String nombre, String apellido, Pais pais){
        Persona p= new Persona (dni, nombre,apellido,pais);
        personas.add(p);
    }
    
    public void agregarEscuderia(Escuderia e){
        escuderias.add(e);
    }
    public void agregarCircuito( Circuito c){
        circuitos.add(c);
    }
      public void agregarCarrera(Carrera ca){
        carreras.add(ca);
    }
      
 
    public void mostrarInformacionPais(){
        System.out.println( "informacion del pais");
         System.out.println( "id:"+ id + "descripcion:"+ descripcion);
          System.out.println( "personas:");
          for(Persona persona: personas){
               System.out.println( persona.getNombre()+persona.getApellido()+ ", dni:"+ persona.getDni());
          }
         System.out.print( "Escuderias:");
          for(Escuderia escuderia: escuderias){
                escuderia.mostrarInfoEscuderia();
          }
           System.out.println( "circuitos");
          for(Circuito circuito : circuitos){
              circuito.mostrarInfoCircuito();
          }
          
           System.out.println( "carreras:");
          for(Carrera carrera: carreras){
            carrera.mostrarInformacion();
          }
    }
            
}
