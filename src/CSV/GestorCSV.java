package CSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import app.*;
import java.text.SimpleDateFormat;

// Clase GestorCSV se encarga de la manipulación de archivos CSV para el manejo de datos de cursos y asistencias.
public class GestorCSV {

    // Carga una lista de fechas desde un archivo CSV y la retorna.
    public List<String> cargarFechasDesdeCSV(String rutaArchivo) {
        List<String> fechas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Añade cada fecha a la lista, eliminando el punto y coma.
                fechas.add(linea.replace(";", " ").trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return fechas;
    }

    // Carga una lista de alumnos desde un archivo CSV y la retorna.
    public List<Alumno> cargarAlumnosDesdeCSV(String rutaArchivo) {
        List<Alumno> alumnos = new ArrayList<>();
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            System.out.println("Ruta esta vacia");
            return alumnos;
        }
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            System.out.println("Archivo no existe: " + rutaArchivo);
            return alumnos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    String rut = partes[0].trim();
                    String nombre = partes[1].trim();
                    String apellido = partes[2].trim();
                    // Añade el nuevo alumno a la lista.
                    alumnos.add(new Alumno(rut, nombre, apellido));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
        return alumnos;
    }

    // Obtiene la ruta del archivo CSV para un curso dado.
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

    // Obtiene la ruta del archivo de asistencia CSV para un curso dado.
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

    // Actualiza el archivo CSV del curso con la lista actual de alumnos.
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

        } catch (IOException e) {

            // mensaje de error 
        }
    }

    // Actualiza el archivo CSV de asistencia para un curso con los datos actuales. 
    public void actualizarAsistenciasCSV(Curso curso) {
        String rutaArchivoAsistencia = obtenerRutaArchivoAsistencia(curso.getNombre());

        if (rutaArchivoAsistencia == null) {
            // mensaje de error 

            return;
        }

        Map<String, String> asistenciasActualizadas = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy", Locale.forLanguageTag("es"));

        for (Map.Entry<Date, RegistroAsistencia> entry : curso.getAsistenciasPorFecha().entrySet()) {
            String fecha = formatter.format(entry.getKey());
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
            // mensaje de error 

            return;
        }

        lineasActualizadas.addAll(asistenciasActualizadas.values());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoAsistencia, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                bw.write(lineaActualizada);
                bw.newLine();
            }

        } catch (IOException e) {
            // mensaje de error 

        }
    }

    // Calcula el promedio de asistencia histórica para un curso basado en su archivo CSV.
    public double asistenciaHistorica(String nombreCurso) {

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
                return promedio;
            }

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }

        return -1;

    }

    // Obtiene una lista de nombres de cursos desde un archivo CSV.
    public List<String> obtenerNombresCursos() {
        List<String> aux = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/CSV/files/nombrecursos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                aux.add(linea);

            }

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            return null;

        }
        return aux;

    }

    public List asistenciaMayor() {

        List<String> nombreCursos = obtenerNombresCursos();
        List<String> cursosFiltrados = new ArrayList();

        if (nombreCursos != null) {
            for (int i = 0; i < nombreCursos.size(); i++) {
                double auxAsistencia = asistenciaHistorica(nombreCursos.get(i));
               
                if (auxAsistencia < 20) {
                    continue;
                }
                cursosFiltrados.add(nombreCursos.get(i));
            }

        }
        return cursosFiltrados;
    }
}
