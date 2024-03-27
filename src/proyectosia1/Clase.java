package proyectosia1;

import java.util.LinkedList;

public class Clase {
    private String nombre;
    private String curso;
    private LinkedList<Alumno> alumnos;

    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LinkedList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(LinkedList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Clase(String nombre, String curso, LinkedList<Alumno> alumnos) {
        this.nombre = nombre;
        this.curso = curso;
        this.alumnos = alumnos;
    }
    
    
   

}
