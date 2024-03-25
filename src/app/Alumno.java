package app;

public class Alumno {

    private int curso;
    private char paralelo;
    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private boolean presente;



    public Alumno(int curso, char paralelo, String rut, String nombre, String apellido, boolean presente) {
        this.curso = curso;
        this.paralelo = paralelo;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellido;
        this.presente = presente;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public char getParalelo() {
        return paralelo;
    }

    public void setParalelo(char paralelo) {
        this.paralelo = paralelo;
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

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }


}
