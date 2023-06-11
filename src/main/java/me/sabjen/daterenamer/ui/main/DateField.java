package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.FileRenamer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateField extends JTextField {
    private final JLabel label;

    public DateField(JPanel panel) {
        super();
        label = new JLabel("Date Format: ", SwingConstants.CENTER);
        setText(FileRenamer.getInstance().getFormat());

        setPreferredSize(new Dimension(120, 20));
        getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        update();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        update();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        update();
                    }
                });

        label.setLabelFor(this);

        panel.add(label);
        panel.add(this);
    }

    private void update() {
        FileRenamer.getInstance().setFormat(getText());
        MainUI.getInstance().reloadTableSorter();

        try {
            new SimpleDateFormat(FileRenamer.getInstance().getFormat()).format(new Date());
        } catch (IllegalArgumentException e) {
            label.setForeground(Color.RED);
            label.setText("WRONG FORMAT");
            MainUI.getInstance().setRenameButton(false);
            return;
        }

        label.setForeground(Color.BLACK);
        label.setText("Date Format:");
        MainUI.getInstance().setRenameButton(true);
    }
}
