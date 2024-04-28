package GUI;

import app.*;
import CSV.*;
import javax.swing.*;
import java.awt.*;
import app.Curso;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CursoOpciones extends JDialog {

    private Curso curso;
    private final GestorCSV gestor = new GestorCSV();

    public CursoOpciones(JFrame parent, String title, Curso curso) {
        super(parent, title, true);
        this.curso = curso;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setupUI(curso);
    }

    private void setupUI(Curso curso) {
        setLayout(new GridLayout(3, 1));

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
        btnCerrar.addActionListener(e -> setVisible(false));
        add(btnCerrar);

    }

    private void mostrarAlumnos() {

        if (curso == null || curso.getAlumnos() == null) {
            JOptionPane.showMessageDialog(this, "No estudiantes disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("<html><table><tr><th>Nombre</th><th>Rut</th></tr>");
        for (Alumno alumno : curso.getAlumnos()) {
            sb.append("<tr><td>").append(alumno.getNombre()).append(" ").append(alumno.getApellido())
                    .append("</td><td>").append(alumno.getRut()).append("</td></tr>");
        }
        sb.append("</table></html>");
        JLabel label = new JLabel(sb.toString());
        label.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(350, 200));  // Set the preferred size of the scroll pane

        JOptionPane.showMessageDialog(this, scrollPane, "Estudiantes en " + curso.getNombre(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void marcarAsistencia() {
        JFrame frame = new JFrame("Marcar Asistencia");
        frame.setLayout(new GridLayout(3, 1));
        Date initialDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel model = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));

        Date[] fechaContainer = new Date[1];  // Mutable container
        fechaContainer[0] = initialDate;

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(spinner);
        JButton btnConfirmDate = new JButton("Confirmar Fecha");
        btnConfirmDate.addActionListener((ActionEvent e) -> {
            fechaContainer[0] = (Date) spinner.getValue(); // Update the container's content
            JOptionPane.showMessageDialog(frame, "Fecha seleccionada: " + fechaContainer[0]);
        });
        panel.add(btnConfirmDate);

        frame.add(panel);

        JTextField cantidadDePresentes = new JTextField(10);
        panel.add(new JLabel("Cantidad De Presentes:"));
        panel.add(cantidadDePresentes);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Marcar Asistencia: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String cantidadPresente = cantidadDePresentes.getText();

        if (result == JOptionPane.OK_OPTION) {
            try {
                Integer.valueOf(cantidadPresente);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Se admiten solo números enteros", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int presentes = Integer.parseInt(cantidadPresente);
        curso.registrarAsistencia(fechaContainer[0], presentes);
        gestor.actualizarAsistenciasCSV(curso);
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

            if (auxAlumnos.stream().anyMatch(alumno -> alumno.getRut().equalsIgnoreCase(rut))) {
                JOptionPane.showMessageDialog(this, "Alumno: " + nombre + " RUT: " + rut + " ya existe!", "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            Alumno nuevoAlumno = new Alumno(rut, nombre, apellido);

            auxAlumnos.add(nuevoAlumno);

            curso.setAlumnos(auxAlumnos);
            gestor.actualizarCSV(curso);

            JOptionPane.showMessageDialog(this, "Alumno agregado: " + nombre + " RUT: " + rut);
        }

    }

    private void eliminarAlumno() {
        List<Alumno> auxAlumnos;

        auxAlumnos = curso.getAlumnos();
        JTextField rutField = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("RUT:"));
        panel.add(rutField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese los datos del alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String rut = rutField.getText();
            if (!auxAlumnos.stream().anyMatch(alumno -> alumno.getRut().equalsIgnoreCase(rut))) {
                JOptionPane.showMessageDialog(this, "Alumno con rut: " + rut + " no existe!", "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            curso.getAlumnos().removeIf(alumno -> alumno.getRut().equalsIgnoreCase(rut));
        }

        curso.setAlumnos(auxAlumnos);
        gestor.actualizarCSV(curso);

    }

}
