package GUI;

import javax.swing.*;
import java.awt.*;
import app.Curso;

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
        setLayout(new GridLayout(5, 1));  // 5 options

        JButton btnVerAlumnos = new JButton("Ver Alumnos");
        btnVerAlumnos.addActionListener(e -> mostrarAlumnos());
        add(btnVerAlumnos);

        JButton btnMarcarAsistencia = new JButton("Marcar Asistencia");
        btnMarcarAsistencia.addActionListener(e -> marcarAsistencia());
        add(btnMarcarAsistencia);

        JButton btnVerPromedioAsistencia = new JButton("Ver Promedio de Asistencia");
        btnVerPromedioAsistencia.addActionListener(e -> verPromedioAsistencia());
        add(btnVerPromedioAsistencia);

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
        // Implementation to show students
    }

    private void marcarAsistencia() {
        // Implementation to mark attendance
    }

    private void verPromedioAsistencia() {
        // Implementation to calculate and show average attendance
    }

    private void agregarAlumno() {
        // Implementation to add a student
    }

    private void eliminarAlumno() {
        // Implementation to remove a student
    }
}
