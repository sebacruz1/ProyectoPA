package app;

import java.util.Date;

// La clase RegistroAsistencia representa el registro de asistencia de los alumnos en una fecha específica.
public class RegistroAsistencia {
    // Variables de instancia para la fecha, la cantidad de alumnos presentes y el total de alumnos.
    private Date fecha;
    private int alumnosPresentes;
    private int totalAlumnos;

    // Constructor que inicializa un objeto RegistroAsistencia con la fecha y el total de alumnos.
    public RegistroAsistencia(Date fecha, int totalAlumnos) {
        this.fecha = fecha; // Establece la fecha del registro.
        this.totalAlumnos = totalAlumnos; // Establece el total de alumnos del curso.
        this.alumnosPresentes = 0; // Inicializa la cantidad de alumnos presentes como cero.
    }

    // Método para registrar la cantidad de alumnos presentes en el registro.
    public void registrarAsistencia(int cantidadAlumnosPresentes) {
        this.alumnosPresentes = cantidadAlumnosPresentes; // Establece la cantidad de alumnos presentes.
    }

    // Getters y setters para los atributos de la clase.
    public Date getFecha() {
        return fecha; // Obtiene la fecha del registro.
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha; // Establece una nueva fecha para el registro.
    }

    public int getAlumnosPresentes() {
        return alumnosPresentes; // Obtiene la cantidad de alumnos presentes en el registro.
    }

    public void setAlumnosPresentes(int alumnosPresentes) {
        this.alumnosPresentes = alumnosPresentes; // Establece la cantidad de alumnos presentes en el registro.
    }

    public int getTotalAlumnos() {
        return totalAlumnos; // Obtiene el total de alumnos del curso.
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos; // Establece un nuevo total de alumnos del curso.
    }
}
