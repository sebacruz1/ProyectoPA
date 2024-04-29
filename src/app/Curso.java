package app;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Date;

// La clase Curso modela la estructura y comportamiento de un curso académico.
public class Curso {
    private String nombre;
    private List<Alumno> alumnos;
    private int totalAlumnos;
    private Map<Date, RegistroAsistencia> asistenciasPorFecha;

    public Curso(String nombre, List<Alumno> alumnos, int totalAlumnos, Map<Date, RegistroAsistencia> asistenciaPorFecha) {
        this.nombre = nombre; 
        this.alumnos = alumnos;
        this.totalAlumnos = totalAlumnos; 
        this.asistenciasPorFecha = asistenciaPorFecha; 
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }

    public String getNombre() {
        return nombre; 
    }

    public List<Alumno> getAlumnos() {
        return alumnos; 
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos; 
    }

    public int getTotalAlumnos() {
        return totalAlumnos; 
    }

    public void setTotalAlumnos(int alumnos) {
        this.totalAlumnos = alumnos; 
    }

    // Método privado que trunca un objeto Date para eliminar la hora y solo dejar la fecha.
    private Date truncateToDate(Date fecha) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(fecha); 
        // Pone a cero las horas, minutos, segundos y milisegundos para truncar la fecha.
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime(); 
    }

    public Map<Date, RegistroAsistencia> getAsistenciasPorFecha() {
        return asistenciasPorFecha; 
    }

    public void setAsistenciasPorFecha(Map<Date, RegistroAsistencia> asistenciasPorFecha) {
        this.asistenciasPorFecha = asistenciasPorFecha; 
    }

    // Método para registrar la asistencia de los alumnos en una fecha específica.
    public void registrarAsistencia(Date fecha, int cantidadAlumnosPresentes) {
        Date truncatedDate = truncateToDate(fecha);
       
        if (!asistenciasPorFecha.containsKey(truncatedDate)) {
            asistenciasPorFecha.put(truncatedDate, new RegistroAsistencia(truncatedDate, totalAlumnos));
        }
        
        RegistroAsistencia registro = asistenciasPorFecha.get(truncatedDate);
        registro.registrarAsistencia(cantidadAlumnosPresentes);
    }

}
