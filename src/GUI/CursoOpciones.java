package GUI;

import app.*;
import CSV.*;
import Excepciones.ConexionFallidaException;
import Excepciones.ValorInvalidoException;
import javax.swing.*;
import java.awt.*;
import app.Curso;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Clase CursoOpciones que extiende de JDialog para manejar opciones relacionadas con un curso específico.
public class CursoOpciones extends JDialog {

    private final Curso curso;
    private final GestorCSV gestor = new GestorCSV();

    // Constructor de la clase que configura el diálogo.
    public CursoOpciones(JFrame parent, String title, Curso curso) {
        super(parent, title, true);
        this.curso = curso;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setupUI(curso);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void dispose() {
        try {
            cerrar();
        } catch (ConexionFallidaException ex) {
            Logger.getLogger(CursoOpciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.dispose();
    }

    // Método para configurar la interfaz de usuario.
    private void setupUI(Curso curso) {
        setLayout(new GridLayout(3, 1));

        // Botón para ver los alumnos del curso.
        JButton btnVerAlumnos = new JButton("Ver Alumnos");
        btnVerAlumnos.addActionListener(e -> mostrarAlumnos());
        add(btnVerAlumnos);

        // Botón para marcar la asistencia de los alumnos.
        JButton btnMarcarAsistencia = new JButton("Marcar Asistencia");
        btnMarcarAsistencia.addActionListener(e -> marcarAsistencia());
        add(btnMarcarAsistencia);

        // Botón para ver el promedio de asistencia de los alumnos.
        JButton btnVerPromedioAsistencia = new JButton("Ver Promedio de Asistencia");
        btnVerPromedioAsistencia.addActionListener(e -> {
            try {
                verPromedioAsistencia();
            } catch (ValorInvalidoException ex) {
                Logger.getLogger(CursoOpciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(btnVerPromedioAsistencia);

        // Botón para agregar un nuevo alumno al curso.
        JButton btnAgregarAlumno = new JButton("Agregar Alumno");
        btnAgregarAlumno.addActionListener(e -> agregarAlumno(curso));
        add(btnAgregarAlumno);

        // Botón para eliminar un alumno del curso.
        JButton btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.addActionListener(e -> eliminarAlumno());
        add(btnEliminarAlumno);

        // Botón para cerrar el diálogo.
        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar);
        btnCerrar.addActionListener(e -> {
            try {
                cerrar();
            } catch (ConexionFallidaException ex) {
                Logger.getLogger(CursoOpciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
// Métodos privados de la clase para manejar las acciones de los botones.

    private void mostrarAlumnos() { // Implementación para mostrar los alumnos.

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
        scrollPane.setPreferredSize(new Dimension(350, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Estudiantes en " + curso.getNombre(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void marcarAsistencia() {   // Implementación para marcar asistencia.
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

        Date[] fechaContainer = new Date[1];
        fechaContainer[0] = initialDate;

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(spinner);
        JButton btnConfirmDate = new JButton("Confirmar Fecha");
        btnConfirmDate.addActionListener((ActionEvent e) -> {
            fechaContainer[0] = (Date) spinner.getValue();
            JOptionPane.showMessageDialog(frame, "Fecha seleccionada: " + fechaContainer[0]);
            setVisible(true);
        });
        panel.add(btnConfirmDate);

        frame.add(panel);

        JTextField cantidadDePresentes = new JTextField(10);
        panel.add(new JLabel("Cantidad De Presentes:"));
        panel.add(cantidadDePresentes);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Marcar Asistencia: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (cantidadDePresentes.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "La cantidad de presentes no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
    }

    private void verPromedioAsistencia() throws ValorInvalidoException {  // Implementación para calcular y mostrar el promedio de asistencia.
        double promedio = gestor.asistenciaHistorica(curso.getNombre());

        if (promedio == -1) {
            JOptionPane.showMessageDialog(this, "No se encuentra el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(this, "Promedio de asistencia: " + String.format("%.2f", promedio), "Promedio Asistencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarAlumno(Curso curso) {   // Implementación para agregar un nuevo alumno.

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
            if (rutField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Rut esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nombreField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (apellidoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Apellido esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
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
            JOptionPane.showMessageDialog(this, "Alumno agregado: " + nombre + " RUT: " + rut);
        }

    }

    private void eliminarAlumno() {
        String[] options = {"Por RUT", "Por Nombre"};
        int response = JOptionPane.showOptionDialog(this, "Eliminar estudiante por:", "Eliminar Estudiante",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (response == 0) {
            eliminarAlumnoRut();
        } else if (response == 1) {
            eliminarAlumnoNombre();
        }
    }

    private void eliminarAlumnoRut() { // Implementación para eliminar un alumno.
        List<Alumno> auxAlumnos;

        auxAlumnos = curso.getAlumnos();
        JTextField rutField = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("RUT:"));
        panel.add(rutField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese el rut del alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String rut = rutField.getText();
            if (!auxAlumnos.stream().anyMatch(alumno -> alumno.getRut().equalsIgnoreCase(rut))) {
                JOptionPane.showMessageDialog(this, "Alumno con rut: " + rut + " no existe!", "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            curso.getAlumnos().removeIf(alumno -> alumno.getRut().equalsIgnoreCase(rut));
            JOptionPane.showMessageDialog(this, "Alumno eliminado!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void eliminarAlumnoNombre() {
        List<Alumno> auxAlumnos;

        auxAlumnos = curso.getAlumnos();
        JTextField nombreField = new JTextField(10);
        JTextField apellidoField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellido:"));
        panel.add(apellidoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese el nombre del alumno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            boolean exists = auxAlumnos.stream().anyMatch(alumno -> alumno.getNombre().equalsIgnoreCase(nombre) && alumno.getApellido().equalsIgnoreCase(apellido));
            if (!exists) {
                JOptionPane.showMessageDialog(this, "Alumno con nombre: " + nombre + " y apellido" + apellido + " no existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean removed = curso.getAlumnos().removeIf(alumno -> alumno.getNombre().equalsIgnoreCase(nombre) && alumno.getApellido().equalsIgnoreCase(apellido));

            if (removed) {
                JOptionPane.showMessageDialog(this, "Alumno eliminado!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al intentar eliminar al alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void cerrar() throws ConexionFallidaException {
        gestor.actualizarAsistenciasCSV(curso);
        gestor.actualizarCSV(curso);
        gestor.actualizarCSV(curso);
        setVisible(false);

    }

}
