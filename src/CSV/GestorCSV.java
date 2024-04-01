package CSV;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GestorCSV {

    public List<String> cargarFechasDesdeCSV(String rutaArchivo) {
        List<String> fechas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                fechas.add(linea.replace(";", " ").trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return fechas;
    }

    public List<Alumno> cargarAlumnosDesdeCSV(String rutaArchivo) {
        List<Alumno> alumnos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3) {

                    String rut = partes[0].trim();
                    String nombre = partes[1].trim();
                    String apellido = partes[2].trim();

                    alumnos.add(new Alumno(rut, nombre, apellido));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return alumnos;
    }

    public Alumno cargarAlumnoPorRUT(String rutaArchivo, String rutBuscado) {

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3 && partes[0].trim().equals(rutBuscado)) {
                    return new Alumno(partes[0].trim(), partes[1].trim(), partes[2].trim());
                }
            }
        } catch (IOException e) {

            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return null;
    }

    public String obtenerRutaArchivoCSV(String nombreCurso) {

        switch (nombreCurso) {
            case "Primero Básico":
                return "src/CSV/files/primerobasico.csv";
            case "Segundo Básico":
                return "src/CSV/files/segundobasico.csv";
            case "Tercero Básico":
                return "src/CSV/files/tercerobasico.csv";
            case "Cuarto Básico":
                return "src/CSV/files/cuartobasico.csv";
            default:
                return null;
        }
    }

    public String obtenerRutaArchivoAsistencia(String nombreCurso) {

        switch (nombreCurso) {
            case "Primero Básico":
                return "src/CSV/files/asistencias/asistenciaPrimero.csv";
            case "Segundo Básico":
                return "src/CSV/files/asistencias/asistenciaSegundo.csv";
            case "Tercero Básico":
                return "src/CSV/files/asistencias/asistenciaTercero.csv";
            case "Cuarto Básico":
                return "src/CSV/files/asistencias/asistenciaCuarto.csv";
            default:
                return null;
        }
    }

    public void agregarAsistencia(String rutaArchivo, int presentes, int indiceFechaActual) {
        if (rutaArchivo == null) {
            System.err.println("La ruta del archivo no puede ser null.");
            return;
        }

        List<String> lineas = new ArrayList<>();
        int lineaActual = 0; // Para llevar la cuenta de la línea actual mientras leemos el archivo

        // Leer el contenido actual del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (lineaActual == indiceFechaActual) {
                    // Obtenemos la fecha de la línea actual para mantenerla
                    String fecha = linea.split(",")[0];
                    // Reconstruimos la línea con la fecha y los presentes actualizados
                    linea = fecha + "," + presentes;
                }
                lineas.add(linea);
                lineaActual++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Reescribir el archivo con el contenido actualizado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void actualizarCSV(Curso clase) {
        String rutaArchivo = obtenerRutaArchivoCSV(clase.getNombre());

        if (rutaArchivo == null) {
            System.err.println("No se pudo encontrar la ruta del archivo para el curso: " + clase.getNombre());
            return;
        }

        // Lista que contendrá las líneas a escribir en el archivo CSV
        List<String> lineasNuevas = new ArrayList<>();

        // Construir las líneas a partir de la lista de alumnos actual
        for (Alumno alumno : clase.getAlumnos()) {
            String linea = String.format("%s;%s;%s", alumno.getRut(), alumno.getNombre(), alumno.getApellido());
            lineasNuevas.add(linea);
        }

        // Sobrescribir el archivo CSV con el contenido actualizado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) { // 'false' para sobrescribir
            for (String linea : lineasNuevas) {
                bw.write(linea);
                bw.newLine(); // Añade una nueva línea después de cada entrada
            }
            System.out.println("Archivo CSV actualizado con éxito para el curso: " + clase.getNombre());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void actualizarAsistenciasCSV(Curso curso) {
        String rutaArchivoAsistencia = obtenerRutaArchivoAsistencia(curso.getNombre());

        if (rutaArchivoAsistencia == null) {
            System.err.println("No se pudo encontrar la ruta del archivo de asistencia para el curso: " + curso.getNombre());
            return;
        }

        Map<String, String> asistenciasActualizadas = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.forLanguageTag("es"));

        // Preparar las asistencias actualizadas en un mapa
        for (Map.Entry<LocalDate, RegistroAsistencia> entry : curso.getAsistenciasPorFecha().entrySet()) {
            String fecha = entry.getKey().format(formatter);
            String asistencia = String.valueOf(entry.getValue().getAlumnosPresentes());
            asistenciasActualizadas.put(fecha, fecha + ";" + asistencia);
        }

        // Leer el contenido existente y actualizarlo
        List<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoAsistencia))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length > 0 && asistenciasActualizadas.containsKey(partes[0])) {
                    // Actualizar la línea con los datos nuevos
                    lineasActualizadas.add(asistenciasActualizadas.get(partes[0]));
                    asistenciasActualizadas.remove(partes[0]); // Evitar duplicados al añadir nuevas líneas
                } else {
                    // Mantener la línea si no necesita actualización
                    lineasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Añadir líneas para las nuevas fechas de asistencia
        lineasActualizadas.addAll(asistenciasActualizadas.values());

        // Reescribir el archivo con las líneas actualizadas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoAsistencia, false))) { // 'false' para sobrescribir
            for (String lineaActualizada : lineasActualizadas) {
                bw.write(lineaActualizada);
                bw.newLine();
            }
            System.out.println("Archivo CSV de asistencia actualizado con éxito para el curso: " + curso.getNombre());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
}