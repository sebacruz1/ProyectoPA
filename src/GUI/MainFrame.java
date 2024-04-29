package GUI;

import javax.swing.*;
import java.awt.*;
import app.*;
import CSV.GestorCSV;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clase MainFrame que extiende de JFrame, actúa como la ventana principal de la aplicación.
public class MainFrame extends JFrame {
    private final GestorCSV gestor = new GestorCSV();  // Instancia del gestor de CSV para manejar datos de cursos y alumnos.
    private JComboBox<String> courseComboBox;  // ComboBox para seleccionar un curso.

    // Constructor de MainFrame que configura la ventana principal.
    public MainFrame() {
        setTitle("Gestión de Alumnos por Curso");  // Título de la ventana.
        setSize(500, 300);  // Tamaño de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Comportamiento al cerrar la ventana.
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla.
        setupUI();  // Método para configurar la interfaz de usuario.
        loadCourses();  // Método para cargar los nombres de los cursos en el ComboBox.
    }

    // Método para configurar los componentes de la interfaz de usuario.
    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));  // Panel con un GridLayout.
        JScrollPane scrollPane = new JScrollPane(panel);  // JScrollPane que contiene el panel.
        add(scrollPane, BorderLayout.CENTER);  // Añade el scrollPane al centro del JFrame.

        courseComboBox = new JComboBox<>();  // Inicialización del JComboBox.
        panel.add(courseComboBox);  // Añade el JComboBox al panel.

        JButton openCursoOptions = new JButton("Open Curso Options");  // Botón para abrir opciones del curso seleccionado.
        openCursoOptions.addActionListener(e -> openCursoOptions());  // Listener para manejar el evento del botón.
        panel.add(openCursoOptions);  // Añade el botón al panel.
        
        JButton btnMostrarCursos = new JButton("Mostrar cursos con asistencia mayor a 20");  // Botón para abrir opciones del curso seleccionado.
        btnMostrarCursos.addActionListener(e -> mostrar());  // Listener para manejar el evento del botón.
        panel.add(btnMostrarCursos);  // Añade el botón al panel.
        
    }

    // Método para cargar los nombres de los cursos desde el gestor CSV y añadirlos al ComboBox.
    private void loadCourses() {
        List<String> courseNames = gestor.obtenerNombresCursos();  // Obtiene los nombres de los cursos.
        if (courseNames != null) {
            courseNames.forEach(name -> courseComboBox.addItem(name));  // Añade cada nombre al ComboBox.
        } else {
            JOptionPane.showMessageDialog(this, "Error loading courses", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir el diálogo de opciones del curso seleccionado.
    private void openCursoOptions() {
        String selectedCourseName = (String) courseComboBox.getSelectedItem();  // Obtiene el nombre del curso seleccionado.
        if (selectedCourseName == null) {
            JOptionPane.showMessageDialog(this, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Curso curso = fetchCurso(selectedCourseName);  // Obtiene los datos del curso.
        if (curso == null) {
            JOptionPane.showMessageDialog(this, "Course data not available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        CursoOpciones opciones = new CursoOpciones(this, "Curso Options", curso);  // Crea una instancia de CursoOpciones.
        opciones.setVisible(true);  // Hace visible el diálogo de opciones del curso.
    }

    // Método para obtener los datos de un curso específico.
    private Curso fetchCurso(String courseName) {
        String rutaArchivo = gestor.obtenerRutaArchivoCSV(courseName);  // Obtiene la ruta del archivo CSV del curso.        
        List<Alumno> alumnos = gestor.cargarAlumnosDesdeCSV(rutaArchivo);   // Carga los alumnos desde el archivo CSV.
        int totalAlumnos = alumnos.size();  // Calcula el total de alumnos.
        
  
        Map<Date, RegistroAsistencia> asistenciasPorFecha = new HashMap();  // Mapa para almacenar las asistencias por fecha.
        
        // Crea y retorna una nueva instancia de Curso con los datos obtenidos.
        return new Curso(courseName, alumnos, totalAlumnos, asistenciasPorFecha);
    }
    
    private void mostrar() {
  
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
}
