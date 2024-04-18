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

    public void actualizarCSV(Curso clase) {
        String rutaArchivo = obtenerRutaArchivoCSV(clase.getNombre());

        if (rutaArchivo == null) {
            System.err.println("No se pudo encontrar la ruta del archivo para el curso: " + clase.getNombre());
            return;
        }

        List<String> lineasNuevas = new ArrayList<>();

        for (Alumno alumno : clase.getAlumnos()) {
            String linea = String.format("%s;%s;%s", alumno.getRut(), alumno.getNombre(), alumno.getApellido());
            lineasNuevas.add(linea);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineasNuevas) {
                bw.write(linea);
                bw.newLine();
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

        for (Map.Entry<LocalDate, RegistroAsistencia> entry : curso.getAsistenciasPorFecha().entrySet()) {
            String fecha = entry.getKey().format(formatter);
            String asistencia = String.valueOf(entry.getValue().getAlumnosPresentes());
            asistenciasActualizadas.put(fecha, fecha + ";" + asistencia);
        }

        List<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoAsistencia))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length > 0 && asistenciasActualizadas.containsKey(partes[0])) {

                    lineasActualizadas.add(asistenciasActualizadas.get(partes[0]));
                    asistenciasActualizadas.remove(partes[0]);
                } else {

                    lineasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        lineasActualizadas.addAll(asistenciasActualizadas.values());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoAsistencia, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                bw.write(lineaActualizada);
                bw.newLine();
            }
            System.out.println("Archivo CSV de asistencia actualizado con éxito para el curso: " + curso.getNombre());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void asistenciaHistorica(String nombreCurso) {

        String rutaArchivo = obtenerRutaArchivoAsistencia(nombreCurso);
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int suma = 0;
            int contador = 0;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                if (valores.length > 1) {
                    try {
                        int valor = Integer.parseInt(valores[1].trim());
                        suma += valor;
                        contador++;
                    } catch (NumberFormatException e) {
                        System.err.println("No se pudo convertir el valor a entero: " + valores[1]);
                    }
                }
            }

            if (contador > 0) {
                double promedio = (double) suma / contador;
                System.out.println("El promedio de asistencia de alumnos es: " + promedio);
            } else {
                System.out.println("No se encontraron valores para calcular el promedio.");
            }

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }

    }

    public List<String> obtenerNombresCursos() {
        List<String> aux = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/CSV/files/nombrecursos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null){
                aux.add(linea);
               
            }

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            return null;

        }
        return aux;
        
    }
}
