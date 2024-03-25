package app;
public class Clase {
    private String nombre;
    private String[] curso;
    private String[] horario;
    private Profesor profesor;

    public Clase(String nombre, String[] curso, String[] horario, Profesor profesor) {
        this.nombre = nombre;
        this.curso = curso;
        this.horario = horario;
        this.profesor = profesor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String[] getCurso() {
        return curso;
    }
    public void setCurso(String[] curso) {
        this.curso = curso;
    }
    public String[] getHorario() {
        return horario;
    }
    public void setHorario(String[] horario) {
        this.horario = horario;
    }
    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}
