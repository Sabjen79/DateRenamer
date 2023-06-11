package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.FileManager;
import me.sabjen.daterenamer.files.FileRenamer;
import me.sabjen.daterenamer.files.RenamableFile;
import me.sabjen.daterenamer.ui.load.LoadUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenameButton extends JButton {
    public RenameButton(JPanel panel, MainTable table) {
        super("Rename All");
        setPreferredSize(new Dimension(90, 30));

        addActionListener((e) -> {
            ArrayList<RenamableFile> files = new ArrayList<>();
            for(int i = 0; i < table.getRowCount(); i++) {
                files.add(FileManager.getInstance().getFiles().get(table.convertRowIndexToModel(i)));
            }

            MainUI.getInstance().setEnabled(false);
            LoadUI.getInstance().setValue(0).setMaximum(table.getRowCount()).setVisible(true);
            FileRenamer.getInstance().renameAll(files);
            FileManager.getInstance().removeFiles(files);

            MainUI.getInstance().reloadTableSorter();
        });

        panel.add(this);
    }
}
