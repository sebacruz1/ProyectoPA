package app;

import CSV.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PROYECTOSIA1 {

    public static void main(String[] args) throws IOException {

        GestorCSV gestor = new GestorCSV();
        List<String> fechasCalendario = gestor.cargarFechasDesdeCSV("src/CSV/files/CalendarioGeneral.csv");
        int indiceFechaActual = 0;
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("es")); // Ajusta este formato según el usado en tu CSV

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String input;  // Cambiado a String para manejar entradas vacías

        while (true) {
            System.out.println("Día: " + fechasCalendario.get(indiceFechaActual));
            System.out.println("\nElija un curso:");
            System.out.println("1. Primero Basico");
            System.out.println("2. Segundo Basico");
            System.out.println("3. Tercero Basico");
            System.out.println("4. Cuarto Basico");
            System.out.println("9. Siguiente dia");
            System.out.println("0. Salir");

            System.out.print("Ingrese una opción: ");
            input = lector.readLine();  // Leer la entrada como una cadena

            // Convertir la entrada en un entero si no está vacía
            int opcion = input.isEmpty() ? -1 : Integer.parseInt(input);

            switch (opcion) {
                case 1:
                    menuCurso("Primero Básico");
                    break;
                case 2:
                    menuCurso("Segundo Básico");
                    break;
                case 3:
                    menuCurso("Tercero Básico");
                    break;
                case 4:
                    menuCurso("Cuarto Básico");
                    break;
                case 9: // Siguiente día
                    if (indiceFechaActual + 1 < fechasCalendario.size()) {
                        indiceFechaActual++;
                    } else {
                        System.out.println("No hay más fechas en el calendario.");
                    }
                    break;
                case 0: // Salir
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static void menuCurso(String nombreCurso) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int opcion;
        EsperaEnter espera = new EsperaEnter();
        GestorCSV gestorCSV = new GestorCSV();

        String rutaArchivo = gestorCSV.obtenerRutaArchivoCSV(nombreCurso);

        while (true) {
            System.out.println("\nMenu Curso - " + nombreCurso + ":");
            System.out.println("1. Marcar asistencia");
            System.out.println("2. Ver alumnos");
            System.out.println("3. Ver asistencia histórico");
            System.out.println("4. Agregar alumno");
            System.out.println("5. Eliminar alumno");
            System.out.println("0. Volver al menú principal");

            System.out.print("Ingrese una opción: ");
            input = lector.readLine();  // Leer la entrada como una cadena

            // Convertir la entrada en un entero si no está vacía
            opcion = input.isEmpty() ? -1 : Integer.parseInt(input);

            switch (opcion) {
                case 1:
                    // Implementar lógica específica para marcar asistencia en este curso
                    break;
                case 2:
                    if (rutaArchivo != null) {
                        List<Alumno> alumnosCurso = gestorCSV.cargarAlumnosDesdeCSV(rutaArchivo);
                        if (alumnosCurso.isEmpty()) {
                            System.out.println("No se encontraron alumnos para " + nombreCurso + ".");
                        } else {
                            System.out.println("Alumnos en " + nombreCurso + ":");
                            int contador = 0; // Inicia un contador para enumerar los alumnos
                            for (Alumno alumno : alumnosCurso) {
                                System.out.println(contador + ". " + alumno.getRut() + " - " + alumno.getNombre() + " " + alumno.getApellido());
                                contador++; // Incrementa el contador para el próximo alumno
                            }
                            // Al final, imprime la cantidad total de alumnos
                            System.out.println("Cantidad total de alumnos: " + alumnosCurso.size());
                        }
                    } else {
                        System.out.println("Archivo del curso no encontrado.");
                    }
                    espera.esperarPorEnter();
                    break;

                case 3:
                    // Implementar lógica para ver asistencia histórica
                    break;
                case 4:
                    // Implementar lógica para agregar alumno
                    break;
                case 5:
                    // Implementar lógica para eliminar alumno
                    break;
                case 0:
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }
        }
    }
}
