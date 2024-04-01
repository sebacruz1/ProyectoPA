package app;

import CSV.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class PROYECTOSIA1 {

    public static void main(String[] args) throws IOException {

        GestorCSV gestor = new GestorCSV();
        List<String> fechasCalendario = gestor.cargarFechasDesdeCSV("src/CSV/files/fechas.csv");
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.forLanguageTag("es")); // Asegúrate de que este formato coincida con el de tu CSV

        // Encuentra el índice de la fecha de hoy en la lista de fechas
        int indiceFechaActual = -1;
        for (int i = 0; i < fechasCalendario.size(); i++) {
            if (LocalDate.parse(fechasCalendario.get(i), formatter).isEqual(fechaActual)) {
                indiceFechaActual = i;
                break;
            }
        }

        // Verifica si se encontró la fecha de hoy en la lista
        if (indiceFechaActual == -1) {
            System.out.println("La fecha de hoy no se encuentra en el calendario.");
            return; // Termina la ejecución si hoy no está en el calendario
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String input;  // Cambiado a String para manejar entradas vacías
        String dia;
        while (true) {
            // Asegurarse de que el índice esté dentro de los límites de la lista
            if (indiceFechaActual >= 0 && indiceFechaActual < fechasCalendario.size()) {
                LocalDate fecha = LocalDate.parse(fechasCalendario.get(indiceFechaActual), formatter); // Parsear la fecha actual del calendario
                dia = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")); // Obtener el día de la semana para esta fecha
                System.out.println("Día: " + dia + " " + fechasCalendario.get(indiceFechaActual));
            } else {
                System.out.println("La fecha está fuera del rango del calendario.");
                break;

            }

            System.out.println("\nElija un curso:");
            System.out.println("1. Primero Basico");
            System.out.println("2. Segundo Basico");
            System.out.println("3. Tercero Basico");
            System.out.println("4. Cuarto Basico");
            System.out.println("8. Dia anterior");
            System.out.println("9. Dia siguiente");
            System.out.println("0. Salir");

            System.out.print("Ingrese una opción: ");
            input = lector.readLine();  // Leer la entrada como una cadena

            // Convertir la entrada en un entero si no está vacía
            int opcion = input.isEmpty() ? -1 : Integer.parseInt(input);

            switch (opcion) {
                case 1:
                    menuCurso("Primero Básico", indiceFechaActual);
                    break;
                case 2:
                    menuCurso("Segundo Básico", indiceFechaActual);
                    break;
                case 3:
                    menuCurso("Tercero Básico", indiceFechaActual);
                    break;
                case 4:
                    menuCurso("Cuarto Básico", indiceFechaActual);
                    break;
                case 8:
                    if (indiceFechaActual - 1 < fechasCalendario.size()) {
                        indiceFechaActual--;
                    } else {
                        System.out.println("No hay más fechas en el calendario.");
                    }
                    break;

                case 9: // Siguiente día
                    if (indiceFechaActual + 1 < fechasCalendario.size()) {
                        indiceFechaActual++;
                    } else {
                        System.out.println("No hay más fechas en el calendario.");
                    }
                    break;
                case 0: // Salir
                    System.out.print("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static void menuCurso(String nombreCurso, int indiceFechaActual) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int opcion;
        EsperaEnter espera = new EsperaEnter();
        GestorCSV gestorCSV = new GestorCSV();
        String rutaArchivo = gestorCSV.obtenerRutaArchivoCSV(nombreCurso);
        GestorAlumnos gestorAlumnos = new GestorAlumnos();
        List<String> fechasCalendario = gestorCSV.cargarFechasDesdeCSV("src/CSV/files/fechas.csv");

        Map<LocalDate, RegistroAsistencia> asistenciasPorFecha = new HashMap<>();

        List<Alumno> alumnosCurso = gestorCSV.cargarAlumnosDesdeCSV(rutaArchivo);
        

        Curso clase = new Curso(nombreCurso, alumnosCurso, alumnosCurso.size(), asistenciasPorFecha);

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
                    System.out.println("Ingrese la cantidad de alumnos presentes: ");
                    int presentes = Integer.parseInt(lector.readLine());

                    // Obtiene la fecha como String y la convierte a LocalDate
                    String fechaStr = fechasCalendario.get(indiceFechaActual);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.forLanguageTag("es"));
                    LocalDate fechaAsistencia = LocalDate.parse(fechaStr, formatter);

                    // Verifica si ya existe un registro de asistencia para esta fecha
                    RegistroAsistencia registro = clase.getAsistenciasPorFecha().get(fechaAsistencia);

                    // Si existe, pide confirmación para sobrescribir
                    if (registro != null) {
                        System.out.println("Ya existe un registro de asistencia para esta fecha. ¿Desea sobrescribirlo? (S/N)");
                        String confirmacion = lector.readLine();
                        if (!confirmacion.equalsIgnoreCase("S")) {
                            System.out.println("Operación cancelada. No se sobrescribió el registro de asistencia.");
                            break; // Salir del case si el usuario no confirma
                        }
                    }

                    // Si el usuario confirma la sobrescritura o no existe registro previo, procede a actualizar o crear el registro
                    registro = new RegistroAsistencia(fechaAsistencia, clase.getTotalAlumnos()); // Inicializa un nuevo registro o reutiliza el existente
                    registro.registrarAsistencia(presentes); // Actualiza la asistencia
                    clase.getAsistenciasPorFecha().put(fechaAsistencia, registro); // Guarda el registro en el mapa

                    System.out.println("Registro de asistencia actualizado correctamente.");
                    break;

                case 2:
                    gestorAlumnos.imprimirListaAlumnos(alumnosCurso);

                    espera.esperarPorEnter();
                    break;

                case 3:
                    gestorCSV.asistenciaHistorica(nombreCurso);
                    espera.esperarPorEnter();
                    break;
                case 4:
                    gestorAlumnos.agregarAlumno(clase, alumnosCurso, nombreCurso, rutaArchivo);

                    break;
                case 5:
                    gestorAlumnos.eliminarAlumno(clase, alumnosCurso);
                    break;
                case 0:
                    gestorCSV.actualizarCSV(clase);
                    gestorCSV.actualizarAsistenciasCSV(clase);
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }
        }
    }
}
