package app;

import java.time.LocalDate;

public class RegistroAsistencia {
    private LocalDate fecha;
    private int alumnosPresentes;
    private int totalAlumnos;

    public RegistroAsistencia(LocalDate fecha, int totalAlumnos) {
        this.fecha = fecha;
        this.totalAlumnos = totalAlumnos;
        this.alumnosPresentes = 0;  
    }

    public void registrarAsistencia(int cantidadAlumnosPresentes) {
        this.alumnosPresentes = cantidadAlumnosPresentes;
    }

    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getAlumnosPresentes() {
        return alumnosPresentes;
    }

    public void setAlumnosPresentes(int alumnosPresentes) {
        this.alumnosPresentes = alumnosPresentes;
    }

    public int getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }
}
