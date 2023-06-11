package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class FilterBox extends JTextField {

    public FilterBox(JPanel panel, MainTable table) {
        super();
        JLabel label = new JLabel("Filter: ", SwingConstants.CENTER);

        setPreferredSize(new Dimension(120, 20));
        getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        update(table);
                    }
                    public void insertUpdate(DocumentEvent e) {
                        update(table);
                    }
                    public void removeUpdate(DocumentEvent e) {
                        update(table);
                    }
                });

        label.setLabelFor(this);

        panel.add(label);
        panel.add(this);
    }

    private void update(MainTable table) {
        table.update(this);
    }
}
