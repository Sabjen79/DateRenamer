package me.sabjen.daterenamer.ui.main;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    private static final MainUI INSTANCE = new MainUI();
    public static MainUI getInstance() {
        return INSTANCE;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    private JPanel mainPanel, centerPanel, topPanel, botPanel;
    private MainTable table;
    public RenameButton renameButton;

    private MainUI() {
        super("Select your items");
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(700, 600);
            setResizable(false);
            setLocationRelativeTo(null);

            mainPanel = new JPanel(new BorderLayout());

            centerPanel = new JPanel(new BorderLayout());
            table = new MainTable(centerPanel);

            topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            topPanel.setPreferredSize(new Dimension(getWidth(), 40));

            new AddFilesButton(topPanel);
            new RemoveButton(topPanel, table);
            new FilterBox(topPanel, table);

            botPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            botPanel.setPreferredSize(new Dimension(getWidth(), 40));

            new DateField(botPanel);
            renameButton = new RenameButton(botPanel, table);

            mainPanel.add(BorderLayout.NORTH, topPanel);
            mainPanel.add(BorderLayout.CENTER, centerPanel);
            mainPanel.add(BorderLayout.SOUTH, botPanel);
            getContentPane().add(mainPanel);
        });
    }

    public void reloadTableSorter() {
        if(table != null) table.reloadSorter();
    }

    public void reloadTable() {
        if(table != null) table.reloadSorter();
    }

    public void setRenameButton(boolean b) {
        renameButton.setEnabled(b);
    }
}
