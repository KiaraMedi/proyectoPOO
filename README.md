# Sistema de Gestión F1 - Escuderías Unidas

## Descripción del Proyecto

Sistema completo de gestión para competencias de Fórmula 1, desarrollado en Java con interfaz gráfica Swing. Permite gestionar pilotos, escuderías, autos, circuitos, carreras y resultados con cálculo automático de puntos según el sistema oficial de F1.

**Versión:** v2.2
**Autor:** Proyecto POO - Universidad
**Fecha:** Octubre 2024

---

## Características Principales

### Gestión de Entidades
- ✅ **Pilotos** - Registro completo con estadísticas
- ✅ **Escuderías** - Gestión de equipos y países
- ✅ **Autos** - Registro de vehículos por escudería
- ✅ **Mecánicos** - Asignación por especialidad
- ✅ **Circuitos** - Circuitos de carrera con longitud
- ✅ **Carreras** - Creación con nombre, fecha, hora y circuito
- ✅ **Resultados** - Registro de posiciones y puntos

### Funcionalidades Avanzadas
- ✅ **Cálculo automático de puntos** según sistema F1 oficial
- ✅ **Inscripción de pilotos** a carreras específicas
- ✅ **Validaciones** - Previene duplicados y datos inconsistentes
- ✅ **Persistencia** - Guardado automático en archivos
- ✅ **8 tipos de informes** - Rankings, históricos, estadísticas
- ✅ **Interfaz gráfica completa** con 10 pestañas

---

## Estructura del Proyecto

```
proyectoPOO/
├── src/main/java/com/mycompany/proyectoEscuderiasUnidas/
│   ├── entities/              # Entidades del dominio
│   │   ├── Pais.java
│   │   ├── Escuderia.java
│   │   ├── Piloto.java
│   │   ├── Auto.java
│   │   ├── Mecanico.java
│   │   ├── Circuito.java
│   │   ├── Carrera.java
│   │   ├── AutoPiloto.java
│   │   ├── ResultadoCarrera.java
│   │   ├── PilotoEscuderia.java
│   │   └── Registros.java
│   ├── persistence/           # Sistema de persistencia
│   │   └── DataPersistence.java
│   └── gui/                   # Interfaz gráfica
│       ├── SistemaF1GUI.java
│       └── dialogos/
│           ├── DialogoCrearCarrera.java
│           └── DialogoInscribirPiloto.java
├── data/                      # Datos persistentes (auto-generado)
│   ├── paises.dat
│   ├── escuderias.dat
│   ├── pilotos.dat
│   ├── autos.dat
│   ├── circuitos.dat
│   ├── carreras.dat
│   └── mecanicos.dat
├── .gitignore
└── README.md
```

---

## Compilación y Ejecución

### Compilar

```bash
javac -d target/classes -cp "src/main/java" \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/entities/*.java \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/persistence/*.java \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/dialogos/*.java \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/SistemaF1GUI.java
```

### Ejecutar GUI

```bash
cd target/classes
java com.mycompany.proyectoEscuderiasUnidas.gui.SistemaF1GUI
```

### Ejecutar ejemplo de consola

```bash
cd target/classes
java com.mycompany.proyectoEscuderiasUnidas.ProyectoPOO
```

---

## Guía de Uso

### 1. Crear una Carrera

1. Abre la aplicación
2. Ve a la pestaña **"Carreras"**
3. Click en **"+ Crear Carrera"**
4. Llena el formulario:
   - **Nombre:** GP Monaco 2024
   - **Fecha:** 2024-12-01
   - **Hora:** 15:00
   - **Circuito:** (selecciona del combo)
   - **Vueltas:** 78
5. Click **"Crear Carrera"**

### 2. Inscribir Piloto a Carrera

1. En pestaña **"Carreras"**
2. Click en **"+ Inscribir Piloto"**
3. Selecciona:
   - **Carrera:** GP Monaco 2024
   - **Piloto:** Lewis Hamilton
   - **Auto:** W15 (Mercedes AMG)
4. Click **"Inscribir"**

### 3. Registrar Resultado

1. Ve a pestaña **"Resultados"**
2. Click en **"[Actualizar]"** si no aparece tu carrera
3. Selecciona:
   - **Carrera:** GP Monaco 2024
   - **Piloto:** Lewis Hamilton
   - **Posición:** 1
   - **Vuelta Rápida:** (marca si aplica)
4. Click **"Registrar Resultado"**
5. Los puntos se calculan automáticamente (1° = 25 pts + 1 pt vuelta rápida)

### 4. Ver Ranking

1. Ve a pestaña **"Ranking"**
2. Visualiza la tabla ordenada por puntos
3. Columnas: Posición, Piloto, Puntos, Victorias, Podios, Vueltas Rápidas

### 5. Guardar Datos

- **Auto-guardado:** Los datos se guardan automáticamente al crear carreras o inscribir pilotos
- **Manual:** Click en **"[Guardar] Guardar Datos"** en la pestaña Inicio
- **Ubicación:** Carpeta `data/` en formato binario serializado

---

## Sistema de Puntos F1

El sistema implementa el reglamento oficial de F1 2024:

| Posición | Puntos |
|----------|--------|
| 1°       | 25     |
| 2°       | 18     |
| 3°       | 15     |
| 4°       | 12     |
| 5°       | 10     |
| 6°       | 8      |
| 7°       | 6      |
| 8°       | 4      |
| 9°       | 2      |
| 10°      | 1      |

**Bonus:** +1 punto por vuelta rápida (solo si termina en top 10)

---

## Informes Disponibles

Accede desde la pestaña **"Informes"**:

1. **Ranking de Pilotos** - Ordenado por puntos acumulados
2. **Resultados por Fechas** - Filtra carreras en rango de fechas
3. **Histórico de Podios y Victorias** - Estadísticas generales
4. **Histórico de Piloto** - Resultados específicos de un piloto
5. **Autos por Escudería** - Lista de vehículos por equipo
6. **Mecánicos por Escudería** - Personal técnico por equipo
7. **Piloto en Circuito** - Rendimiento de piloto en circuito específico
8. **Carreras por Circuito** - Historial de carreras en un circuito

---

## Persistencia de Datos

### Carga Automática
Al iniciar la aplicación, se cargan automáticamente los datos guardados de `data/`.
Si no existen datos, se inicializan valores por defecto.

### Guardado Automático
Los datos se guardan automáticamente en estas acciones:
- Crear carrera
- Inscribir piloto a carrera

### Guardado Manual
Click en **"[Guardar] Guardar Datos"** en la pestaña Inicio.

### Resetear Datos
Para volver a datos iniciales:
```bash
rm -rf data/
```

---

## Validaciones Implementadas

- ✅ No permite posiciones duplicadas en una carrera
- ✅ No permite registrar resultado de piloto no inscrito
- ✅ No permite inscribir piloto dos veces en la misma carrera
- ✅ Valida campos obligatorios en formularios
- ✅ Valida formato de números (vueltas, posiciones)

---

## Tecnologías Utilizadas

- **Lenguaje:** Java 17+
- **GUI:** Swing (JFrame, JTabbedPane, JTable, JDialog)
- **Persistencia:** Serialización Java
- **Arquitectura:** MVC-like (Model-View-Controller)
- **Patrones:** DAO, Builder, Observer

---

## Requisitos del Sistema

- **JDK:** 17 o superior
- **RAM:** Mínimo 512 MB
- **Disco:** 50 MB para datos
- **OS:** Windows, macOS, Linux (compatible con cualquier SO con JVM)

---

## Características Técnicas

### Entidades Serializables
Todas las entidades implementan `Serializable` para persistencia:
- Pais, Escuderia, Piloto, Auto, Mecanico, Circuito
- Carrera, AutoPiloto, ResultadoCarrera, PilotoEscuderia

### Arquitectura de GUI
- **Panel Principal:** JTabbedPane con 10 pestañas
- **Diálogos Modulares:** Clases separadas para formularios
- **Actualización Reactiva:** Botones de actualizar en listas dinámicas
- **Navegación:** Tarjetas clickeables en inicio

---

## Solución de Problemas

### La GUI no muestra emojis correctamente
Los emojis han sido reemplazados por texto plano compatible:
- ✓ Usado: `[Guardar]`, `[Actualizar]`, `+ Agregar`

### No aparecen las carreras en Resultados
1. Click en el botón **"[Actualizar]"** junto al combo de carreras
2. Verifica que la carrera tenga pilotos inscritos

### Error al cargar datos
Si hay error de serialización:
```bash
rm -rf data/
```
Y reinicia la aplicación.

---

## Notas de Versión

### v2.2 (Actual)
- ✅ Campo "nombre" en carreras
- ✅ Inscripción de pilotos a carreras
- ✅ Diálogos modulares separados
- ✅ Persistencia completa con auto-guardado

### v2.1
- ✅ Sistema de persistencia implementado
- ✅ Botones de guardar/cargar datos
- ✅ Auto-guardado en operaciones críticas

### v2.0
- ✅ 10 pestañas funcionales
- ✅ Formularios para todas las entidades
- ✅ Tarjetas clickeables en inicio

### v1.0
- ✅ Modelo de entidades completo
- ✅ Cálculo de puntos F1
- ✅ 8 tipos de informes

---

## Licencia

Este proyecto es de uso académico para la materia de Programación Orientada a Objetos.

---

## Contacto y Soporte

Para dudas o problemas:
- Revisar esta documentación
- Verificar que todos los archivos `.java` estén compilados
- Eliminar carpeta `data/` si hay problemas de persistencia
