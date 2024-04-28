package app;

import java.util.Date;

public class RegistroAsistencia {
    private Date fecha;
    private int alumnosPresentes;
    private int totalAlumnos;

    public RegistroAsistencia(Date fecha, int totalAlumnos) {
        this.fecha = fecha;
        this.totalAlumnos = totalAlumnos;
        this.alumnosPresentes = 0;  
    }

    public void registrarAsistencia(int cantidadAlumnosPresentes) {
        this.alumnosPresentes = cantidadAlumnosPresentes;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
