package me.sabjen.daterenamer.ui.start;

import me.sabjen.daterenamer.files.FileManager;
import me.sabjen.daterenamer.ui.main.MainUI;

import javax.swing.*;
import java.awt.*;

class SelectFolderButton extends JButton {
    public SelectFolderButton(JPanel panel) {
        super("Select Files");
        setPreferredSize(new Dimension(120, 40));

        addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Open items");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setMultiSelectionEnabled(true);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                StartUI.getInstance().setVisible(false);
                MainUI.getInstance().setVisible(true);
                FileManager.getInstance().addFilesFrom(chooser.getSelectedFiles());
            }
        });

        panel.add(this);
    }
}
