package GUI;

import app.Alumno;
import javax.swing.*;
import java.awt.*;
import app.Curso;
import java.util.Calendar;
import java.util.Date;

public class CursoOpciones extends JDialog {

    private Curso curso;
    private JSpinner dateSpinner;
            
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

        // Building the student list string
        StringBuilder sb = new StringBuilder("<html><table><tr><th>Nombre</th><th>Rut</th></tr>");
        for (Alumno alumno : curso.getAlumnos()) {
            sb.append("<tr><td>").append(alumno.getNombre()).append(" ").append(alumno.getApellido())
                    .append("</td><td>").append(alumno.getRut()).append("</td></tr>");
        }
        sb.append("</table></html>");

        // Displaying in a dialog
        JOptionPane.showMessageDialog(this, sb.toString(), "Estudiantes en " + curso.getNombre(), JOptionPane.INFORMATION_MESSAGE);
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
