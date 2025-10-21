
package com.mycompany.proyectoEscuderiasUnidas;

public enum Puntos {
    
    //puntos por posicion//
   PRIMERO(25),
   SEGUNDO(18),
   TERCERO(15),
   CUARTO(12),
   QUINTO(10),
   SEXTO(8),
   SEPTIMO(6),
   OCTAVO(4),
   NOVENO(2),
   DECIMO(1);
   
   //constructor
   private final int puntos;
   Puntos (int puntos){
       this.puntos= puntos;
   }
   //método para obtener puntos de una posicion//
   public int getPuntos (){
       return puntos;
   }
}
