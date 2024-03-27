package app;

import app.Alumno;
import java.util.LinkedList;

public class Curso {
    private String nombre;
    private LinkedList<Alumno> alumnos;
    private int cantidadAlumnos;

       
    public Curso(String nombre, String curso, LinkedList<Alumno> alumnos) {
        this.nombre = nombre;
        this.alumnos = alumnos;
    }

        
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    public String getNombre() {
        return nombre;
    }
        
    public LinkedList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(LinkedList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

   
    
    
   

}
