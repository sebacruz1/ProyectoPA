package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import CSV.*;
import app.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    public GestorCSV gestor = new GestorCSV();  // Suponiendo que esta clase puede cargar cursos y alumnos

    public MainFrame() {
        setTitle("Gestión de Alumnos por Curso");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));  // Una columna, múltiples filas
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        // Suponemos que tenemos una manera de obtener una lista de nombres de cursos
        List<String> nombresCursos = gestor.obtenerNombresCursos(); // Debes implementar este método

        for (String nombreCurso : nombresCursos) {
            JButton button = new JButton(nombreCurso);
            button.addActionListener(e -> mostrarAlumnos(nombreCurso));
            panel.add(button);
        }
    }

   
    private void mostrarAlumnos(String nombreCurso) {
        String rutaArchivo = gestor.obtenerRutaArchivoCSV(nombreCurso);
        List<Alumno> alumnos = gestor.cargarAlumnosDesdeCSV(rutaArchivo);
        Curso curso = new Curso(nombreCurso, alumnos, alumnos.size(), new HashMap<>());  // Asume que Curso tiene este constructor

        CursoOpciones dialog = new CursoOpciones(this, "Opciones del Curso: " + nombreCurso, curso);
        dialog.setVisible(true);
    }

}
