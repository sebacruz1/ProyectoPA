package GUI;

import javax.swing.*;
import java.awt.*;
import app.*;
import CSV.GestorCSV;
import Excepciones.ValorInvalidoException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Clase MainFrame que extiende de JFrame, actúa como la ventana principal de la aplicación.
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
    
 
    // Método para configurar los componentes de la interfaz de usuario.
    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10)); 
        JScrollPane scrollPane = new JScrollPane(panel);  
        add(scrollPane, BorderLayout.CENTER); 


        courseComboBox = new JComboBox<>();  
        panel.add(courseComboBox);  

        JButton openCursoOptions = new JButton("Abrir opciones del curso");
        openCursoOptions.addActionListener(e -> openCursoOptions());  
        panel.add(openCursoOptions);  

        
        JButton btnMostrarCursos = new JButton("Mostrar cursos con asistencia mayor a 20");  
        btnMostrarCursos.addActionListener(e -> {
            try {
                mostrar();
            } catch (ValorInvalidoException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        panel.add(btnMostrarCursos); 
        
        
        JButton btnSalir = new JButton("Salir");  
        btnSalir.addActionListener(e -> salir());  
        panel.add(btnSalir); 
        
    }

    // Método para cargar los nombres de los cursos desde el gestor CSV y añadirlos al ComboBox.
    private void loadCourses() {
        List<String> courseNames = gestor.obtenerNombresCursos();
        if (courseNames != null) {
            courseNames.forEach(name -> courseComboBox.addItem(name)); 
        } else {
            JOptionPane.showMessageDialog(this, "Error loading courses", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir el diálogo de opciones del curso seleccionado.
    private void openCursoOptions() {
        String selectedCourseName = (String) courseComboBox.getSelectedItem();  
        if (selectedCourseName == null) {
            JOptionPane.showMessageDialog(this, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Curso curso = fetchCurso(selectedCourseName);  
        if (curso == null) {
            JOptionPane.showMessageDialog(this, "Course data not available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        CursoOpciones opciones = new CursoOpciones(this, "Curso Options", curso);  
        opciones.setVisible(true);  
    }

    // Método para obtener los datos de un curso específico.
    private Curso fetchCurso(String courseName) {
        String rutaArchivo = gestor.obtenerRutaArchivoCSV(courseName);     
        List<Alumno> alumnos = gestor.cargarAlumnosDesdeCSV(rutaArchivo);   
        int totalAlumnos = alumnos.size(); 
        
  
        Map<Date, RegistroAsistencia> asistenciasPorFecha = new HashMap(); 
        
        // Crea y retorna una nueva instancia de Curso con los datos obtenidos.
        return new Curso(courseName, alumnos, totalAlumnos, asistenciasPorFecha);
    }
    
    private void mostrar() throws ValorInvalidoException {
  
        List<String> cursos = gestor.asistenciaMayor();
        JList<String> list = new JList<>(cursos.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(list);
        JFrame frame = new JFrame("Cursos con asistencia mayor a 20%");
        frame.add(scrollPane);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    private void salir() {
        dispose();
        System.exit(0);
        
    }
}
