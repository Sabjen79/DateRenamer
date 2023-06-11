package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.FileManager;
import me.sabjen.daterenamer.ui.start.StartUI;

import javax.swing.*;
import java.awt.*;

public class AddFilesButton extends JButton {
    public AddFilesButton(JPanel panel) {
        super("Add files");
        setPreferredSize(new Dimension(90, 30));

        addActionListener((e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Open items");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setMultiSelectionEnabled(true);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                FileManager.getInstance().addFilesFrom(chooser.getSelectedFiles());
            }
        });

        panel.add(this);
    }
}
