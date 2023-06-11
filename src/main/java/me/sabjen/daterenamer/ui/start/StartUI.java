package me.sabjen.daterenamer.ui.start;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StartUI extends JFrame {
    private static final StartUI INSTANCE = new StartUI();
    public static StartUI getInstance() {
        return INSTANCE;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    private JPanel panel;

    private StartUI() {
        super("Date Renamer");
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(300, 200);
            setResizable(false);
            setLocationRelativeTo(null);

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("Select the folder to rename files:");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.PLAIN, 16));
            label.setPreferredSize(new Dimension(getWidth()-20, 75));

            panel.add(label);

            SelectFolderButton button = new SelectFolderButton(panel);

            getContentPane().add(panel);
        });
    }
}
