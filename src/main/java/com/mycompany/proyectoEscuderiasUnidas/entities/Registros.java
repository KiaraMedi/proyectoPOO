
package com.mycompany.proyectoEscuderiasUnidas;
import java.util.ArrayList;
public class Registros { //clase para gestionar registros
    private ArrayList<Auto> autos;
    private ArrayList<Mecanico> mecanicos;
    private ArrayList<Piloto> pilotos;
    private  ArrayList<Circuito> circuitos;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Pais> paises;
    
  // Constructor que recibe las listas ya creadas
    public Registros(ArrayList<Auto> autos, ArrayList<Mecanico> mecanicos, 
                   ArrayList<Piloto> pilotos, ArrayList<Circuito> circuitos, 
                   ArrayList<Escuderia> escuderias, ArrayList<Pais> paises) {
        this.autos = autos;
        this.mecanicos = mecanicos;
        this.pilotos = pilotos;
        this.circuitos = circuitos;
        this.escuderias = escuderias;
        this.paises = paises;
    }
    //métodos agregar
    public void agregarPiloto(Piloto pe){
        pilotos.add(pe) ;
    }
    public void agregarAuto (Auto auto){
        autos.add(auto);
    }
    public void agregarMecanico (Mecanico mecanico){
        mecanicos.add(mecanico);
    }
    public void agregarCircuito (Circuito circuito){
        circuitos.add(circuito);
    }
     public void agregarEscuderia(Escuderia e){
        escuderias.add(e) ;
    }
     public void agregarPais(Pais pais){
        paises.add(pais) ;
    }
    
    
    
    public void mostrarInfoAutos(){
        System.out.println("autos:");
        for(Auto a:autos){
            System.out.println(a.getModelo()+ a.getMotor());
        }
    }
    public void mostrarInfoPilotos(){
        System.out.println("pilotos:");
        for(Piloto pe: pilotos){
            System.out.println(pe);
        }
    }
    public void mostrarInfoMecanicos(){
        System.out.println("mecanicos:");
        for(Mecanico m:mecanicos){
            System.out.println(m.getNombre()+"-"+ m.getEspecialidad());
        }
    }
    public void mostrarInfoCircuitos(){
        System.out.println("circuitos:");
        for(Circuito c: circuitos){
            System.out.println(c);
        }
    }
    public void mostrarInfoEscuderias(){
        System.out.println("Escuderias:");
        for(Escuderia e: escuderias){
            System.out.println(e);
        }
    }
     public void mostrarInfoPaises(){
         System.out.println("paises:");
        for(Pais p: paises){
            System.out.println(p);
        }
    }
}
