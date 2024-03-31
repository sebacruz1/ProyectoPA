package CSV;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.Alumno;

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

    public String obtenerRutaArchivoCSV(String nombreCurso) {

        switch (nombreCurso) {
            case "Primero Básico":
                return "src/CSV/files/primeroBasico.csv";
            case "Segundo Básico":
                return "src/CSV/files/segundoBasico.csv";
            case "Tercero Básico":
                return "src/CSV/files/tercerobasico.csv";
            case "Cuarto Básico":
                return "src/CSV/files/cuartobasico.csv";
            default:
                return null;
        }
    }

    public void agregarAlumnoACSV(String nombreCurso, String rutaArchivo) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingresa el RUT del alumno: ");
        String rut = lector.readLine();

        System.out.print("Ingresa el nombre del alumno: ");
        String nombre = lector.readLine();

        System.out.print("Ingresa el apellido del alumno: ");
        String apellido = lector.readLine();

        // Suponiendo que tienes una clase Alumno que acepta rut, nombre y apellido en su constructor
        Alumno alumno = new Alumno(rut, nombre, apellido);

        if (rutaArchivo == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String nuevaLinea = alumno.getRut() + ";" + alumno.getNombre() + ";" + alumno.getApellido();
            bw.write(nuevaLinea + System.lineSeparator());
            System.out.println("Alumno agregado exitosamente.");
        }
    }


    public void eliminarAlumnoDeCSV(String nombreCurso, String rutaArchivo) throws IOException {
        
        if (rutaArchivo == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        File archivoCSV = new File(rutaArchivo);
        List<String> lineas = new ArrayList<>();
        boolean alumnoEncontrado = false;
        String rutAlumno;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        

        // Leer el contenido del archivo y almacenar las líneas, excepto la del alumno a eliminar
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            System.out.println("Ingrese el rut del alumno a eliminar (Formato 12345678-9: ");
            rutAlumno = lector.readLine();
            while ((linea = br.readLine()) != null) {
                // Suponiendo que el RUT es el primer elemento en cada línea
                if (!linea.split(";")[0].trim().equals(rutAlumno)) {
                    lineas.add(linea);
                } else {
                    alumnoEncontrado = true;
                }
            }
        }

        // Reescribir el archivo sin la línea del alumno eliminado
        if (alumnoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV, false))) { // false para sobrescribir el archivo
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
            System.out.println("Alumno con RUT " + rutAlumno + " eliminado exitosamente.");
        } else {
            System.out.println("Alumno con RUT " + rutAlumno + " no encontrado.");
        }
    }
}
