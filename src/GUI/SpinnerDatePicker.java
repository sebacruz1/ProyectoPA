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
        frame = new JFrame("Choose Date"); 
        frame.setSize(200, 200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

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
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, spinner.getValue().toString())); 

        frame.setLayout(new FlowLayout()); 
        frame.add(button); 
        frame.setVisible(true); 
    }
}

