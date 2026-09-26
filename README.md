# Taller ONE - Sistema de Control del Grupo POO

## Descripción del proyecto

Este proyecto es una aplicación de consola desarrollada en Java que permite gestionar la inscripción de alumnos a los paralelos (C1 y C2) de un curso de Programación Orientada a Objetos (POO).

El sistema permite:

- Cargar datos de alumnos y solicitudes de inscripción desde archivos de texto.
- Procesar automáticamente las solicitudes, filtrando entre alumnos admitidos y rechazados.
- Inscribir manualmente a un alumno (por nombre completo o por RUT).
- Administrar el curso: cambiar de paralelo, eliminar o inscribir alumnos.
- Generar reportes de los alumnos por paralelo (C1/C2) y de las solicitudes rechazadas, guardándolos en archivos de texto.
- Realizar un análisis estadístico del proceso (tasa de admisión, porcentaje de rechazados, distribución por paralelo, etc.).

## Integrantes

| Nombre | RUT | Usuario GitHub |
|---|---|---|
| Renato Argandoña | 22.290.100-6 | rennaNVM |
| Dominique Montiel | 22.325.504-3 | xdomminik |


## Estructura del proyecto

```
Taller-1-POO/
├── src/
│   └── Talleres/
│       └── TallerONE.java
├── textos/
│   ├── Alumnos.txt
│   └── Solicitudes.txt
└── Reportes/          (se genera automáticamente al crear reportes)
```

- **Paquete `Talleres`**: contiene toda la lógica del sistema.
  - **Clase `TallerONE`**: clase principal del proyecto, contiene el método `main` y todos los métodos de apoyo:
    - `main`: despliega el menú principal y controla el flujo del programa.
    - `cargarAlumnos()`: lee el archivo `textos/Alumnos.txt` y carga los datos de los alumnos.
    - `cargarSolicitudes()`: lee el archivo `textos/Solicitudes.txt` y carga las solicitudes de inscripción.
    - `eliminarAlumno()`: elimina un alumno de la lista y actualiza los registros asociados.
    - `guardarAlumnos()`: guarda los datos actualizados de los alumnos en el archivo correspondiente.
    - `generarReporteParalelo()`: genera un reporte de texto con los alumnos admitidos en un paralelo (C1 o C2).
    - `generarReporteRechazados()`: genera un reporte de texto con las solicitudes rechazadas.
    - `analisisEstadistico()`: calcula y muestra estadísticas del proceso de inscripción.

### Archivos de datos necesarios

El programa espera encontrar los siguientes archivos en la carpeta `textos/` (relativa a la ubicación de ejecución):

- `textos/Alumnos.txt`: formato `NOMBRE;APELLIDO;RUT;PARALELO` por línea.
- `textos/Solicitudes.txt`: formato `NOMBRE-APELLIDO` por línea.

Los reportes generados se guardan automáticamente en una carpeta `Reportes/`, la cual se crea si no existe.

## Instrucciones de ejecución

1. Clonar o descargar el repositorio.
2. Asegurarse de tener **Java JDK 8 o superior** instalado.
3. Crear la carpeta `textos/` en el directorio raíz del proyecto (al mismo nivel desde donde se ejecutará el programa) y agregar los archivos `Alumnos.txt` y `Solicitudes.txt` con el formato indicado arriba.
4. Compilar el programa desde la raíz del proyecto:

   ```bash
   javac -d bin src/Talleres/TallerONE.java
   ```

5. Ejecutar el programa:

   ```bash
   java -cp bin Talleres.TallerONE
   ```

6. Usar el menú interactivo para:
   1. Cargar archivos (Alumnos y Solicitudes).
   2. Procesar solicitudes (filtrado automático).
   3. Inscribir manualmente a un alumno.
   4. Administrar el curso (cambiar paralelo, eliminar o inscribir alumnos).
   5. Generar reportes.
   6. Ver el análisis estadístico.
   7. Salir del programa.



