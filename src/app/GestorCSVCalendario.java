package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


}
