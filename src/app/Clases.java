package app;

import java.util.LinkedList;
import app.Alumno;
import app.Profesor;



public class Clases {
    private Clase clase;
    private LinkedList<Alumno> alumnos;
    private Profesor profesor;

    public Clases(Clase clase, LinkedList<Alumno> alumnos, Profesor profesor) {
        this.clase = clase;
        this.alumnos = alumnos;
        this.profesor = profesor;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public LinkedList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(LinkedList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
