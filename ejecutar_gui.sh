#!/bin/bash
# Script para ejecutar la GUI del Sistema F1

echo "====================================="
echo "  Sistema de Gestión F1 - GUI"
echo "====================================="
echo ""
echo "Compilando proyecto..."

javac -d target/classes src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/SistemaF1GUI.java src/main/java/com/mycompany/proyectoEscuderiasUnidas/entities/*.java

if [ $? -eq 0 ]; then
    echo "Compilación exitosa!"
    echo "Ejecutando GUI..."
    cd target/classes
    java com.mycompany.proyectoEscuderiasUnidas.gui.SistemaF1GUI
else
    echo "Error en la compilación"
    exit 1
fi
