package GUI;

import javax.swing.*;
import java.awt.*;
import app.Curso;
import CSV.GestorCSV;

public class CursoOpciones extends JDialog {
    private Curso curso;

    public CursoOpciones(JFrame parent, String title, Curso curso) {
        super(parent, title, true);
        this.curso = curso;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(5, 1));  // 5 opciones

        JButton btnVerAlumnos = new JButton("Ver Alumnos");
        btnVerAlumnos.addActionListener(e -> mostrarAlumnos());
        add(btnVerAlumnos);

        JButton btnMarcarAsistencia = new JButton("Marcar Asistencia");
        btnMarcarAsistencia.addActionListener(e -> marcarAsistencia());
        add(btnMarcarAsistencia);

        JButton btnAgregarAlumno = new JButton("Agregar Alumno");
        btnAgregarAlumno.addActionListener(e -> agregarAlumno());
        add(btnAgregarAlumno);

        JButton btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.addActionListener(e -> eliminarAlumno());
        add(btnEliminarAlumno);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> setVisible(false));
        add(btnCerrar);
    }

    private void mostrarAlumnos() {
        StringBuilder sb = new StringBuilder("Alumnos en " + curso.getNombre() + ":\n");
        curso.getAlumnos().forEach(alumno -> sb.append(alumno.getNombre()).append(" ").append(alumno.getApellido()).append("\n"));
        JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Alumnos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void marcarAsistencia() {
        // Implementación de la lógica para marcar asistencia
    }

    private void agregarAlumno() {
        // Implementación de la lógica para agregar un alumno
    }

    private void eliminarAlumno() {
        // Implementación de la lógica para eliminar un alumno
    }
}
