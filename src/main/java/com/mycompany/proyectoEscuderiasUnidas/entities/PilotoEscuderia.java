
package com.mycompany.proyectoEscuderiasUnidas;


public class PilotoEscuderia {
   private String desdeFecha; 
   private String hastaFecha;
   private Escuderia escuderia;
   private Piloto piloto;
   //constructores//
    public PilotoEscuderia(String desdeFecha, String hastaFecha, Escuderia escuderia,Piloto piloto ){
         this.desdeFecha=desdeFecha;
         this.hastaFecha=hastaFecha;
         this.escuderia=escuderia;
         this.piloto=piloto;
    }
    public PilotoEscuderia(){ }
    
 //setters y getters//
    public void setDesdeFecha(String desdeFecha){
        this.desdeFecha=desdeFecha;
    }
    public void setHastaFecha(String hastaFecha){
        this.hastaFecha=hastaFecha;
    }
    public void setPiloto(Piloto piloto){
        this.piloto=piloto;
    }
    public void setEscuderia(Escuderia escuderia){
        this.escuderia=escuderia;
    }
    public String getDesdeFecha(){
        return desdeFecha;
    }
     public String getHastaFecha(){
        return hastaFecha;
    }
     
    public Piloto getPiloto(){
        return piloto;
    }
     public Escuderia getEscuderia(){
        return escuderia;
    }
    
     //mostrar info
     public void mostrarInfoPE(){
         System.out.println("historial de escuderias");
         System.out.println("desde la fecha:"+desdeFecha+", hasta la fecha:"+hastaFecha+ ", escuderia:"+ escuderia+ ", piloto:"+piloto);

     }
}
