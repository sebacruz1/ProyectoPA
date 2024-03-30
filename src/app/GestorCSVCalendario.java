package app;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.Alumno;

public class GestorCSVCalendario {

    
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
    
    public String obtenerRutaArchivoCSV(String nombreCurso) {
        
        switch (nombreCurso) {
            case "Primero Básico":
                return "C:\\Users\\joaki\\Desktop\\ProyectoPA-main\\src\\CSV\\files\\primerobasico.csv";
            case "Segundo Básico":
                return "C:\\Users\\joaki\\Desktop\\ProyectoPA-main\\src\\CSV\\files\\segundobasico.csv";
            case "Tercero Básico":
                return "C:\\Users\\joaki\\Desktop\\ProyectoPA-main\\src\\CSV\\files\\tercerobasico.csv";
            case "Cuarto Básico":
                return "C:\\Users\\joaki\\Desktop\\ProyectoPA-main\\src\\CSV\\files\\cuartobasico.csv";
            default:
                return null;
        }
    }
    public void agregarAlumnoACSV(String nombreCurso, Alumno alumno) throws IOException {
        String rutaArchivo = obtenerRutaArchivoCSV(nombreCurso);
        if (rutaArchivo == null) {
            System.out.println("Curso no encontrado.");
            return;
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String nuevaLinea = alumno.getRut() + ";" + alumno.getNombre() + ";" + alumno.getApellido();
            bw.write(nuevaLinea + "\n");
        }
    }
    public void eliminarAlumnoDeCSV(String nombreCurso, String rutAlumno) throws IOException {
        String rutaArchivo = obtenerRutaArchivoCSV(nombreCurso);
        if (rutaArchivo == null) {
            System.out.println("Curso no encontrado.");
            return;
        }
    
        File archivoOriginal = new File(rutaArchivo);
        File archivoTemporal = new File(archivoOriginal.getAbsolutePath() + ".tmp");
    
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
    
            String linea;
    
            while ((linea = br.readLine()) != null) {
                if (!linea.split(";")[0].trim().equals(rutAlumno)) {
                    bw.write(linea + "\n");
                }
            }
        }
    
        if (!archivoOriginal.delete()) {
            System.out.println("No se pudo eliminar el archivo original.");
            return;
        }
        if (!archivoTemporal.renameTo(archivoOriginal)) {
            System.out.println("No se pudo renombrar el archivo temporal.");
        }
    }
}
