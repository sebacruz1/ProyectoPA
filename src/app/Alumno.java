package app;

public class Alumno {

    private int curso;
    private Paralelo paralelo;
    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private boolean presente;



    public void setCurso(int curso) {
        this.curso = curso;
    }

    public enum Paralelo {
        A, B, C
    }

    public Alumno(String rut, String nombre, String apellido, int curso, boolean presente) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellido;
        this.curso = curso;
        this.presente = presente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }
    public int getCurso() {
    return curso;
    }
}
