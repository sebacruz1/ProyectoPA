package app;

// Clase Alumno representa a un estudiante con su RUT, nombre y apellido.
public class Alumno {
    // Atributos privados para el RUT, nombre y apellido del alumno.
    private String rut;
    private String nombre;
    private String apellido;

    // Constructor que inicializa un nuevo alumno con los valores proporcionados.
    public Alumno(String rut, String nombre, String apellido) {
        this.rut = rut;           // Establece el RUT del alumno.
        this.nombre = nombre;     // Establece el nombre del alumno.
        this.apellido = apellido; // Establece el apellido del alumno.
    }

    // Getter para obtener el RUT del alumno.
    public String getRut() {
        return rut;
    }

    // Getter para obtener el nombre del alumno.
    public String getNombre() {
        return nombre;
    }
    
    // Getter para obtener el apellido del alumno.
    public String getApellido() {
        return apellido;
    }

    // Setter para establecer o cambiar el apellido del alumno.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Setter para establecer o cambiar el RUT del alumno.
    public void setRut(String rut) {
        this.rut = rut;
    }

    // Setter para establecer o cambiar el nombre del alumno.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
