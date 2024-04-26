package GUI;

import app.*;
import CSV.*;
import javax.swing.*;
import java.awt.*;
import app.Curso;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CursoOpciones extends JDialog {

    private Curso curso;
    private JSpinner dateSpinner;

    public CursoOpciones(JFrame parent, String title, Curso curso) {
        super(parent, title, true);
        this.curso = curso;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setupUI(curso);
    }

    private void setupUI(Curso curso) {
        setLayout(new GridLayout(5, 1)); 

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
        btnAgregarAlumno.addActionListener(e -> agregarAlumno(curso));
        add(btnAgregarAlumno);

        JButton btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.addActionListener(e -> eliminarAlumno());
        add(btnEliminarAlumno);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> cerrar(curso));
        add(btnCerrar);

        Date initialDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel model = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(model);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));

        add(dateSpinner);

        JButton btnConfirmDate = new JButton("Confirmar Fecha");
        btnConfirmDate.addActionListener(e -> {
            Date selectedDate = (Date) dateSpinner.getValue();
            // Handle the selected date
            JOptionPane.showMessageDialog(this, "Fecha seleccionada: " + selectedDate);
        });
        add(btnConfirmDate);

    }

    private void mostrarAlumnos() {
        // Check if curso and alumnos list are not null
        if (curso == null || curso.getAlumnos() == null) {
            JOptionPane.showMessageDialog(this, "No students data available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Building the student list string in HTML format
        StringBuilder sb = new StringBuilder("<html><table><tr><th>Nombre</th><th>Rut</th></tr>");
        for (Alumno alumno : curso.getAlumnos()) {
            sb.append("<tr><td>").append(alumno.getNombre()).append(" ").append(alumno.getApellido())
                    .append("</td><td>").append(alumno.getRut()).append("</td></tr>");
        }
        sb.append("</table></html>");

        // Creating a JLabel with the student list
        JLabel label = new JLabel(sb.toString());
        label.setVerticalAlignment(SwingConstants.TOP);

        // Making the label scrollable
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(350, 200));  // Set the preferred size of the scroll pane

        // Displaying in a dialog with a scroll pane
        JOptionPane.showMessageDialog(this, scrollPane, "Estudiantes en " + curso.getNombre(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void marcarAsistencia() {
        // Implementation to mark attendance
    }

    private void verPromedioAsistencia() {
        // Implementation to calculate and show average attendance
    }

    private void agregarAlumno(Curso curso) {
        List<Alumno> auxAlumnos;
        auxAlumnos = curso.getAlumnos();

        JTextField rutField = new JTextField(10);
        JTextField nombreField = new JTextField(20);
        JTextField apellidoField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("RUT:"));
        panel.add(rutField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellido:"));
        panel.add(apellidoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese los datos del alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String rut = rutField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();

            Alumno nuevoAlumno = new Alumno(rut, nombre, apellido);

            auxAlumnos.add(nuevoAlumno);

            curso.setAlumnos(auxAlumnos);

            JOptionPane.showMessageDialog(this, "Alumno agregado: " + nombre + " RUT: " + rut);
        }

    }

    private void eliminarAlumno() {
        // Implementation to remove a student
    }
    
    private void cerrar(Curso nombreCurso){
        GestorCSV gestor = new GestorCSV();
        gestor.actualizarCSV(curso);
        setVisible(false);
        
        
    }

}
