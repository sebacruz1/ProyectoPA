package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class SpinnerDatePicker {
    private JFrame frame;

    public SpinnerDatePicker() {
        frame = new JFrame("Choose Date");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Date initialDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 50);
        Date latestDate = calendar.getTime();
        SpinnerModel dateModel = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.YEAR);

        JSpinner spinner = new JSpinner(dateModel);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));

        JButton button = new JButton("Elegir día");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, spinner.getValue().toString()));

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.setVisible(true);
    }
}
