
package com.mycompany.proyectoEscuderiasUnidas;
import java.util.ArrayList;
public class Circuito {
   private String nombre; 
   private int longitud;
   private Pais pais;
   private ArrayList<Carrera> carreras=new ArrayList<>();//almacenar múltiples carreras.
   
   //constructores//
   public Circuito() { }
   public Circuito(String nombre, int longitud, Pais pais){
       this.nombre=nombre;
       this.longitud=longitud;
       this.pais=pais;
   }
   
   //setters y getters//
    public void setNombre(String nombre){
        this.nombre= nombre;
    }
    public void setLongitud(int longitud){
        this.longitud=longitud;
    }
    public void setPais(Pais pais){
        this.pais=pais;
    }
    
    public String getnombre(){
        return nombre;
    }
    public int getLongitud(){
        return longitud;
    }
    public Pais getPais(){
        return pais;
    }
    
    //metodos
     public void agregarCarrera(Carrera car){
        carreras.add(car);
    }
     public void mostrarInfoCircuito(){
         System.out.println("circuitos:");
         System.out.println( "nombre"+ nombre + ", longitud:"+longitud+", pais:"+ pais);
         System.out.println("carreras:");
         for ( Carrera carrera : carreras){
             carrera.mostrarInformacion();
         }
               
     }
}
