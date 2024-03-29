package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PROYECTOSIA1 {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MMMM", Locale.forLanguageTag("es")); // Asumiendo español
    
    public static void main(String[] args) throws IOException {
        
        GestorCSVCalendario gestor = new GestorCSVCalendario();
        
        List<String> fechasCalendario = gestor.cargarFechasDesdeCSV("C:\\Users\\joaki\\Desktop\\ProyectoPA-main\\src\\CSV\\files\\CalendarioGeneral.csv");
           
        int indiceFechaActual = 0;

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        while (true) {
            System.out.println("Día: " + fechasCalendario.get(indiceFechaActual));


            System.out.println("\nElija un curso:");
            System.out.println("\n");
            System.out.println("1. Primero Basico");
            System.out.println("2. Segundo Basico");
            System.out.println("3. Tercero Basico");
            System.out.println("4. Cuarto Basico");
            System.out.println("\n9. Siguiente dia");
            System.out.println("0. Salir");

            opcion = Integer.parseInt(lector.readLine());
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
    int opcion;
    GestorCSVCalendario gestorCSV = new GestorCSVCalendario();    
    
    String rutaArchivo = gestorCSV.obtenerRutaArchivoCSV(nombreCurso);

    
    while (true) {
        System.out.println("\nMenu Curso - " + nombreCurso + ":");
        System.out.println("1. Marcar asistencia");
        System.out.println("2. Ver alumnos");
        System.out.println("3. Ver asistencia histórico");
        System.out.println("4. Agregar alumno");
        System.out.println("5. Eliminar alumno");
        System.out.println("\n0. Volver al menú principal");

        System.out.print("Ingrese una opción: ");
        opcion = Integer.parseInt(lector.readLine());
        

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
                     int contador = 1; // Inicia un contador para enumerar los alumnos
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
             break;
            
            case 0:
                return; // Regresa al menú principal
            default:
                System.out.println("Opción no válida. Por favor, intente nuevamente.");
                break;
        }
    }
}

    
}
