# Proyecto Programación Avanzada Primer Semestre 2024
---
## Alumnos
 - Sebastián Cruz
 - Joaquín Fuenzalida
 - Maximiliano Bustamante  

## Sistema de Gestión Escolar

Este proyecto es un sistema de gestión escolar desarrollado en Java 17, diseñado para manejar la asistencia de alumnos y la organización de cursos. Permite registrar la asistencia diaria de un colegio, agregar o eliminar alumnos, y navegar a través de un calendario escolar.

## Características

- Gestión de cursos y alumnos.
- Registro de asistencia diaria.
- Navegación por un calendario escolar con fechas específicas.
- Capacidad de agregar y eliminar alumnos de los cursos.

## Cómo Ejecutar

Para ejecutar este proyecto, necesitas tener instalado Java 17 en tu sistema. Sigue estos pasos:

1. Descarga o clona este repositorio en tu sistema local.
2. Navega hasta la carpeta del proyecto desde tu terminal o línea de comandos.
3. Compila el proyecto utilizando el comando `javac` seguido del nombre del archivo principal (por ejemplo, `javac PROYECTOSIA1.java`).
4. Ejecuta el archivo compilado con `java PROYECTOSIA1`.

## Como Ejecutar 2
1. Descargar o clona este repositorio.
2. Abre Netbeans.
3. Abre el proyecto en Netbeans.
4. Compilalo.
5. Ejecutalo.

## Recursos Usados.
- JDK 17.
- Netbeans 12.
- ChatGPT.
- Trello
- Git y Github.
  

## Estructura de Archivos.

- `PROYECTOSIA1.java`: Archivo principal que contiene la lógica de navegación del menú y la interacción con el usuario.
- `GestorAlumnos.java`: Archivo encargado de hacer los cambios dentro de los cursos.
- `GestorCSV.java`: Encargado de manejar la lectura y escritura de archivos CSV, utilizados para almacenar información sobre fechas, alumnos y asistencia.
- `Alumno.java`: Define la estructura y propiedades de un alumno, como su nombre, apellido y RUT.
- `Curso.java`: Gestiona la información relacionada con los cursos, incluidos los alumnos inscritos y el registro de asistencia.
- `EsperaEnter.java`: Utilidad para pausar la ejecución del programa y esperar a que el usuario presione Enter.
- `RegistroAsistencia.java`: Maneja el registro de la asistencia de alumnos para fechas específicas.
- Carpeta `files` : Contiene los archivos generales de tipo .CSV.
- Carpeta `CSVAsistencias` : Almacena las asitencias diarias por curso.
  

---
