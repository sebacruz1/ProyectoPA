package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import app.Alumno;
        
public class GestorCSVAlumnos {

    public LinkedList<Alumno> cargarAlumnosDesdeCSV(String rutaArchivo) {
        LinkedList<Alumno> alumnos = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // br.readLine(); // Omitir la cabecera del CSV si existe
            
            while ((linea = br.readLine()) != null) {
                String[] datosAlumno = linea.split(",");
                if (datosAlumno.length == 3) {
                    String rut = datosAlumno[0].trim();
                    String nombre = datosAlumno[1].trim();
                    String apellido = datosAlumno[2].trim();
                    Alumno alumno = new Alumno(rut, nombre, apellido);
                    alumnos.add(alumno);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        
        return alumnos;
    }
}
