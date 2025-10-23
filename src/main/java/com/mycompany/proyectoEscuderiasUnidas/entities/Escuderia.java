
package com.mycompany.proyectoEscuderiasUnidas.entities;
import java.util.ArrayList;
public class Escuderia {
    private String nombre;
    private Pais pais;
    private ArrayList<Auto> autos=new ArrayList<>();
    private ArrayList<Mecanico> mecanicos= new ArrayList<>();// mecanicos que trabajan en la escuderia
    private ArrayList<PilotoEscuderia> pilotosEscuderias=new ArrayList<>(); //pilotos que pasaron por la escuderia

    
    //constructores
    public Escuderia (){}
    public Escuderia (String nombre, Pais pais){
        this.nombre=nombre;
        this.pais=pais;
    }
    
    public void setEscuderia( String nombre){
        this.nombre=nombre;
    }
     public void setPais( Pais pais){
        this.pais=pais;
    }
    
    public String getEscuderia(){
        return nombre;
    }
    public Pais getPais(){
        return pais;
    }
    
    // métodos agregar
    public void agregarPiloto( String desdeFecha, String hastaFecha, Piloto piloto, Escuderia escuderia){
        PilotoEscuderia pe= new PilotoEscuderia(desdeFecha, hastaFecha,escuderia, piloto );
        pilotosEscuderias.add(pe);
    }
    
    public void agregarAuto (Auto auto){
        autos.add(auto);
    }
    public void agregarMecanico (Mecanico mecanico){
        mecanicos.add(mecanico);
    }
    
    
    public void mostrarInfoEscuderia(){
        System.out.println("nombre de la escuderia:"+ nombre);
        System.out.println("autos:");
        for(Auto a:autos){
            System.out.println(a.getModelo()+ a.getMotor());
        }
        System.out.println("pilotos:");
        for(PilotoEscuderia pe: pilotosEscuderias){
            System.out.println(pe.getPiloto().getNombre());
        }
        for(Mecanico m:mecanicos){
            System.out.println(m.getNombre()+ m.getEspecialidad());
        }
    }
    
    
}
    