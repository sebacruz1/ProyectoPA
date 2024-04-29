package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

// Clase SpinnerDatePicker para crear una interfaz que permite al usuario seleccionar una fecha mediante un JSpinner.
public class SpinnerDatePicker {
    private JFrame frame; 

    // Constructor que configura el marco y sus componentes.
    public SpinnerDatePicker() {
        frame = new JFrame("Choose Date"); // Título del marco.
        frame.setSize(200, 200); // Tamaño del marco.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamiento al cerrar.

        // Configuración inicial de fecha para el spinner.
        Date initialDate = new Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(initialDate); 
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 50);
        Date latestDate = calendar.getTime();

        // Modelo de datos para el spinner, configurado con las fechas establecidas.
        SpinnerModel dateModel = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.YEAR);

        // Creación del spinner y configuración de su formato de visualización.
        JSpinner spinner = new JSpinner(dateModel);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy")); // Formato de fecha.

        JButton button = new JButton("Elegir día");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, spinner.getValue().toString())); // Muestra la fecha seleccionada.

        frame.setLayout(new FlowLayout()); // Usa FlowLayout para la disposición de componentes.
        frame.add(button); // Añade el botón al marco.
        frame.setVisible(true); // Hace visible el marco.
    }
}

