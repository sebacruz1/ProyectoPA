package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Curso {

    private String nombre;
    private List<Alumno> alumnos;
    private int totalAlumnos;
    private Map<LocalDate, RegistroAsistencia> asistenciasPorFecha;

    public Curso(String nombre, List<Alumno> alumnos, int totalAlumnos, Map<LocalDate, RegistroAsistencia> asistenciaPorFecha) {
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

    public void registrarAsistencia(LocalDate fecha, int cantidadAlumnosPresentes) {
        if (!asistenciasPorFecha.containsKey(fecha)) {
            asistenciasPorFecha.put(fecha, new RegistroAsistencia(fecha, totalAlumnos));
        }
        RegistroAsistencia registro = asistenciasPorFecha.get(fecha);
        registro.registrarAsistencia(cantidadAlumnosPresentes);
    }

    public Map<LocalDate, RegistroAsistencia> getAsistenciasPorFecha() {
        return asistenciasPorFecha;
    }

    public void setAsistenciasPorFecha(Map<LocalDate, RegistroAsistencia> asistenciasPorFecha) {
        this.asistenciasPorFecha = asistenciasPorFecha;
    }
}
