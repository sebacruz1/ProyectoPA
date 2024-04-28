package app;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Date;

// La clase Curso modela la estructura y comportamiento de un curso académico.
public class Curso {
    // Variables de instancia para el nombre del curso, lista de alumnos, el total de alumnos y el registro de asistencia.
    private String nombre;
    private List<Alumno> alumnos;
    private int totalAlumnos;
    private Map<Date, RegistroAsistencia> asistenciasPorFecha;

    // Constructor que inicializa un objeto Curso con los parámetros dados.
    public Curso(String nombre, List<Alumno> alumnos, int totalAlumnos, Map<Date, RegistroAsistencia> asistenciaPorFecha) {
        this.nombre = nombre; // Nombre del curso.
        this.alumnos = alumnos; // Lista de alumnos inscritos.
        this.totalAlumnos = totalAlumnos; // Número total de alumnos.
        this.asistenciasPorFecha = asistenciaPorFecha; // Mapa para registrar la asistencia con fecha como clave.
    }

    // Setters y Getters para los atributos de la clase.
    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre del curso.
    }

    public String getNombre() {
        return nombre; // Obtiene el nombre del curso.
    }

    public List<Alumno> getAlumnos() {
        return alumnos; // Obtiene la lista de alumnos.
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos; // Asigna una nueva lista de alumnos al curso.
    }

    public int getTotalAlumnos() {
        return totalAlumnos; // Obtiene el total de alumnos inscritos en el curso.
    }

    public void setTotalAlumnos(int alumnos) {
        this.totalAlumnos = alumnos; // Establece el total de alumnos en el curso.
    }

    // Método privado que trunca un objeto Date para eliminar la hora y solo dejar la fecha.
    private Date truncateToDate(Date fecha) {
        Calendar cal = Calendar.getInstance(); // Obtiene una instancia de Calendar para trabajar con fechas.
        cal.setTime(fecha); // Establece la fecha proporcionada en el calendario.
        // Pone a cero las horas, minutos, segundos y milisegundos para truncar la fecha.
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime(); // Retorna la fecha truncada.
    }

    public Map<Date, RegistroAsistencia> getAsistenciasPorFecha() {
        return asistenciasPorFecha; // Obtiene el mapa de asistencias por fecha.
    }

    public void setAsistenciasPorFecha(Map<Date, RegistroAsistencia> asistenciasPorFecha) {
        this.asistenciasPorFecha = asistenciasPorFecha; // Establece un nuevo mapa de asistencias.
    }

    // Método para registrar la asistencia de los alumnos en una fecha específica.
    public void registrarAsistencia(Date fecha, int cantidadAlumnosPresentes) {
        Date truncatedDate = truncateToDate(fecha); // Trunca la fecha para que no contenga la hora.

        // Verifica si ya existe un registro para esa fecha. Si no, crea uno nuevo.
        if (!asistenciasPorFecha.containsKey(truncatedDate)) {
            asistenciasPorFecha.put(truncatedDate, new RegistroAsistencia(truncatedDate, totalAlumnos));
        }
        // Obtiene el registro existente y actualiza la asistencia con la cantidad de alumnos presentes.
        RegistroAsistencia registro = asistenciasPorFecha.get(truncatedDate);
        registro.registrarAsistencia(cantidadAlumnosPresentes);
    }

}
