# 🏎️ Sistema de Gestión F1 - Interfaz Gráfica

## 📋 Descripción

Interfaz gráfica completa para el Sistema de Gestión de Escuderías Unidas de Fórmula 1, desarrollada con Java Swing.

## 🚀 Cómo Ejecutar la GUI

### Opción 1: Usando el script (macOS/Linux)
```bash
./ejecutar_gui.sh
```

### Opción 2: Compilación y ejecución manual
```bash
# Compilar
javac -d target/classes \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/SistemaF1GUI.java \
  src/main/java/com/mycompany/proyectoEscuderiasUnidas/entities/*.java

# Ejecutar
cd target/classes
java com.mycompany.proyectoEscuderiasUnidas.gui.SistemaF1GUI
```

### Opción 3: Desde un IDE (NetBeans, IntelliJ, Eclipse)
1. Abrir el proyecto
2. Navegar a `src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/SistemaF1GUI.java`
3. Click derecho → Run File (o presionar Shift+F6)

## 📱 Funcionalidades de la GUI

### 🏠 Panel de Inicio
- **Vista general** del sistema con tarjetas estadísticas
- Contador de pilotos, escuderías, circuitos, autos, carreras y mecánicos
- Diseño con colores distintivos para cada categoría

### 👥 Gestión de Pilotos
- **Tabla interactiva** con todos los pilotos registrados
- Visualización de:
  - DNI, Nombre, Apellido
  - País de origen
  - Victorias acumuladas
  - Podios totales
  - Puntos del campeonato
- Botón **"Agregar Piloto"** para registrar nuevos pilotos
- Formulario con validación de datos
- Actualización en tiempo real

### 🏎️ Gestión de Autos
- Tabla con todos los autos del campeonato
- Información: Modelo, Motor, Escudería
- Vista clara de la relación auto-escudería

### 🏁 Gestión de Carreras
- **Crear nuevas carreras** con formulario intuitivo
- Selección de:
  - Fecha (formato YYYY-MM-DD)
  - Hora (formato HH:MM)
  - Circuito disponible
  - Número de vueltas
- Tabla con todas las carreras planificadas
- Contador de participantes por carrera

### 📊 Registro de Resultados
- **Interfaz paso a paso** para registrar resultados
- Selección de carrera activa
- Selección de piloto participante
- Spinner para posición (1-20)
- Checkbox para vuelta rápida
- **Validaciones automáticas:**
  - Verifica que el piloto esté inscrito
  - No permite duplicar posiciones
  - Calcula puntos automáticamente
- Actualiza estadísticas del piloto inmediatamente
- Vista previa de resultados de carrera

### 📈 Ranking de Pilotos
- **Tabla ordenada** por puntos acumulados
- Muestra:
  - Posición en el campeonato
  - Nombre del piloto
  - Puntos totales
  - Victorias
  - Podios
  - Vueltas rápidas
- Botón de actualización en tiempo real
- Ordenamiento automático

### 📋 Generación de Informes
- **8 tipos de informes** disponibles:

#### 1. 📊 Ranking General
- Clasificación completa de pilotos
- Formato tabular con estadísticas

#### 2. 🏆 Histórico de Podios
- Todos los podios de todos los pilotos
- Detalle de victorias y posiciones

#### 3. 🏎️ Autos por Escudería
- Lista de autos utilizados
- Carreras donde participaron
- Input: Nombre de escudería

#### 4. 🔧 Mecánicos por Escudería
- Personal técnico de cada equipo
- Especialidades y años de experiencia
- Input: Nombre de escudería

#### 5. 🏁 Carreras por Circuito
- Histórico de carreras en un circuito
- Fechas, horas y detalles
- Input: Nombre del circuito

#### 6. 📅 Resultados por Fechas
- Filtro de carreras por período
- Input: Fecha desde y hasta

#### 7. 👤 Histórico de Piloto
- Carrera por carrera de un piloto
- Posiciones y puntos obtenidos
- Input: DNI del piloto

#### 8. 🌍 Piloto en Circuito
- Veces que un piloto corrió en un circuito específico
- Input: DNI y nombre de circuito

## 🎨 Características de Diseño

### Interfaz Moderna
- ✅ Sistema de pestañas (JTabbedPane) para navegación
- ✅ Colores distintivos por funcionalidad
- ✅ Iconos emoji para mejor UX
- ✅ Tarjetas de estadísticas con colores diferenciados
- ✅ Tablas con formato profesional

### Usabilidad
- ✅ Formularios con validación de datos
- ✅ Mensajes de error y éxito claros
- ✅ ComboBox para selección guiada
- ✅ Spinners para valores numéricos
- ✅ Checkboxes para opciones booleanas
- ✅ Botones con descripciones claras

### Responsive
- ✅ Ventana de 1200x700 píxeles
- ✅ Componentes con scroll automático
- ✅ GridLayout para organización
- ✅ BorderLayout para paneles principales

## 🔧 Arquitectura Técnica

### Componentes Utilizados
- **JFrame**: Ventana principal
- **JTabbedPane**: Sistema de pestañas
- **JTable**: Tablas de datos
- **JComboBox**: Selección desplegable
- **JTextField**: Entrada de texto
- **JSpinner**: Selección numérica
- **JCheckBox**: Opciones booleanas
- **JButton**: Acciones
- **JTextArea**: Visualización de informes
- **JDialog**: Ventanas modales

### Patrón de Diseño
- Separación de responsabilidades
- Paneles independientes por funcionalidad
- Reutilización de métodos de actualización
- Sistema centralizado de registros

## 📦 Datos Pre-cargados

Al iniciar la GUI, el sistema incluye:
- **7 países**: Argentina, Brasil, Italia, Alemania, Mónaco, España, Reino Unido
- **3 escuderías**: Ferrari, Red Bull Racing, Mercedes AMG
- **4 pilotos**: Hamilton, Verstappen, Leclerc, Pérez
- **3 autos**: SF-24, RB20, W15
- **4 circuitos**: Monza, Monaco, Interlagos, Silverstone
- **3 mecánicos**: Rossi, Schmidt, Dubois

## 🎯 Flujo de Trabajo Recomendado

1. **Inicio**: Revisar estadísticas generales
2. **Pilotos**: Verificar o agregar pilotos
3. **Autos**: Revisar equipamiento disponible
4. **Carreras**: Crear una nueva carrera
5. **Resultados**: Registrar resultados de la carrera
6. **Ranking**: Ver clasificación actualizada
7. **Informes**: Generar reportes personalizados

## 💡 Tips de Uso

- Los pilotos deben estar en el sistema antes de registrar resultados
- Las carreras deben crearse antes de registrar resultados
- El ranking se actualiza automáticamente al registrar resultados
- Los informes capturan el estado actual del sistema
- Usa el botón "Actualizar" para refrescar las tablas

## 🐛 Solución de Problemas

### La ventana no se abre
```bash
# Verificar que Java esté instalado
java -version
javac -version
```

### Error de compilación
```bash
# Limpiar y recompilar
rm -rf target/classes
mkdir -p target/classes
javac -d target/classes src/main/java/com/mycompany/proyectoEscuderiasUnidas/gui/SistemaF1GUI.java src/main/java/com/mycompany/proyectoEscuderiasUnidas/entities/*.java
```

### La GUI se ve mal en macOS
La GUI usa el look and feel nativo del sistema operativo, debería verse bien en cualquier plataforma.

## 📸 Capturas de Pantalla

La GUI incluye:
- Panel de inicio con 6 tarjetas de estadísticas coloridas
- Tablas profesionales con headers en negrita
- Formularios organizados con GridLayout
- Botones con iconos emoji para mejor identificación
- Área de informes con tipografía monoespaciada

## 🚀 Próximas Mejoras (Opcional)

- [ ] Persistencia de datos (guardar/cargar desde archivo)
- [ ] Exportar informes a PDF
- [ ] Gráficos estadísticos (JFreeChart)
- [ ] Modo oscuro
- [ ] Filtros avanzados en tablas
- [ ] Búsqueda en tiempo real
- [ ] Imágenes de pilotos y escuderías
- [ ] Calendario visual de carreras

## 👨‍💻 Autor

Sistema desarrollado como proyecto académico POO (Programación Orientada a Objetos)

---

**¡Disfruta gestionando tu campeonato de Fórmula 1!** 🏁
