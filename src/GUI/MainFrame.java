package GUI;

import javax.swing.*;
import java.awt.*;
import app.*;
import CSV.GestorCSV;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final GestorCSV gestor = new GestorCSV();
    private JComboBox<String> courseComboBox;

    public MainFrame() {
        setTitle("Gestión de Alumnos por Curso");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupUI();
        loadCourses();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        courseComboBox = new JComboBox<>();
        panel.add(courseComboBox);

        JButton openCursoOptions = new JButton("Open Curso Options");
        openCursoOptions.addActionListener(e -> openCursoOptions());
        panel.add(openCursoOptions);
    }

    private void loadCourses() {
        List<String> courseNames = gestor.obtenerNombresCursos();
        if (courseNames != null) {
            courseNames.forEach(name -> courseComboBox.addItem(name));
        } else {
            JOptionPane.showMessageDialog(this, "Error loading courses", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openCursoOptions() {
        String selectedCourseName = (String) courseComboBox.getSelectedItem();
        if (selectedCourseName == null) {
            JOptionPane.showMessageDialog(this, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Curso curso = fetchCurso(selectedCourseName);  // Implement fetching Curso based on selected name
        if (curso == null) {
            JOptionPane.showMessageDialog(this, "Course data not available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        CursoOpciones opciones = new CursoOpciones(this, "Curso Options", curso);
        opciones.setVisible(true);
    }

    private Curso fetchCurso(String courseName) {
        String rutaArchivo = gestor.obtenerRutaArchivoCSV(courseName);
        List<Alumno> alumnos = gestor.cargarAlumnosDesdeCSV(rutaArchivo);
        int totalAlumnos = alumnos.size();
       
        String rutaAsistencia = gestor.obtenerRutaArchivoAsistencia(courseName);
        Map<LocalDate, RegistroAsistencia> asistenciasPorFecha = gestor.;
        
        
        return new Curso(courseName, alumnos, totalAlumnos, new HashMap());
    }

    
}
