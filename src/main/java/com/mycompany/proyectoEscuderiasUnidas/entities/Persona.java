package com.mycompany.proyectoEscuderiasUnidas.entities;

public class Persona {
     private String dni;
     private String nombre;
     private String apellido;
     private Pais pais;
     //constructores//
     public Persona(){}
     public Persona (String dni, String nombre, String apellido, Pais pais){
        this.apellido=apellido;
        this.nombre=nombre;
        this.dni=dni;
        this.pais=pais;
     }
     //setters y getters//
    public void setNombre(String nombre){
        this.nombre= nombre;
    }
    public void setApellido(String apellido){
        this.apellido= apellido;
    }
    public void setDni(String dni){
        this.dni=dni;
    }
    public void setPais(Pais pais){
        this.pais=pais;
    }
    
    public String getNombre(){
        return nombre;
    }
     public String getApellido(){
        return apellido;
    }
    public String getDni(){
        return dni;
    }
    public Pais getPais(){
        return pais;
    }
    //métood mostrar info
    public String toString(){
        return "apellido y nombre" + apellido+nombre+",dni:"+dni+ ", pais:"+pais;
    }
}
