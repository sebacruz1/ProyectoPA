package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Seba Cruz
 */
public class GestorAlumnos {

    public void agregarAlumno(Curso clase, List<Alumno> alumnos, String nombreCurso, String rutaArchivo) throws IOException {
        // !! falta buscar si ya existe 
        
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingresa el RUT del alumno: ");
        String rut = lector.readLine();

        System.out.print("Ingresa el nombre del alumno: ");
        String nombre = lector.readLine();

        System.out.print("Ingresa el apellido del alumno: ");
        String apellido = lector.readLine();

        // Suponiendo que tienes una clase Alumno que acepta rut, nombre y apellido en su constructor
        Alumno alumno = new Alumno(rut, nombre, apellido);
        
        alumnos.add(alumno);
        
        clase.setAlumnos(alumnos);
                

        System.out.println("Alumno agregado exitosamente.");
    }

    public void imprimirListaAlumnos(List<Alumno> alumnos) {
        int cont = 1;
        for (Alumno alumno : alumnos) {

            System.out.println(cont + ". " + alumno.getRut() + " - " + alumno.getNombre() + " " + alumno.getApellido());
            cont++;
        }
    }

    public void eliminarAlumno(Curso clase, List<Alumno> alumnos) throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("¿Deseas eliminar por RUT o por nombre y apellido? (R/N)");
        String eleccion = lector.readLine();

        if ("R".equalsIgnoreCase(eleccion)) {
            System.out.println("Ingresa el RUT del alumno a eliminar:");
            String rut = lector.readLine();

            // Encuentra el alumno con el RUT especificado y elimínalo de la lista
            clase.getAlumnos().removeIf(alumno -> alumno.getRut().equalsIgnoreCase(rut));
            System.out.println("Alumno eliminado.");

        } else if ("N".equalsIgnoreCase(eleccion)) {
            System.out.println("Ingresa el nombre del alumno a eliminar:");
            String nombre = lector.readLine();
            System.out.println("Ingresa el apellido del alumno a eliminar:");
            String apellido = lector.readLine();

            // Encuentra el alumno con el nombre y apellido especificados y elimínalo de la lista
            clase.getAlumnos().removeIf(alumno -> alumno.getNombre().equalsIgnoreCase(nombre) && alumno.getApellido().equalsIgnoreCase(apellido));
            System.out.println("Alumno eliminado.");

        } else {
            System.out.println("Opción no válida.");
        }

    }

}
