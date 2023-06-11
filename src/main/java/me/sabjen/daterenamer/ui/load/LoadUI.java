package me.sabjen.daterenamer.ui.load;

import javax.swing.*;
import java.awt.*;

public class LoadUI extends JFrame {
    private static final LoadUI INSTANCE = new LoadUI();
    public static LoadUI getInstance() {
        return INSTANCE;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    private JPanel panel;
    private JProgressBar progressBar;

    private LoadUI() {
        super("Loading...");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(200, 100);
            setResizable(false);
            setLocationRelativeTo(null);

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setPreferredSize(new Dimension(150, 50));

            panel.add(progressBar);

            getContentPane().add(panel);
    }

    public LoadUI setValue(int n) {
        if(progressBar != null) progressBar.setValue(n);

        repaint();
        return this;
    }

    public LoadUI setMaximum(int n) {
        if(progressBar != null) progressBar.setMaximum(n);

        repaint();
        return this;
    }
}
