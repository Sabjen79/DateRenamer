package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.FileManager;

import javax.swing.*;
import java.awt.*;

public class RemoveButton  extends JButton {
    public RemoveButton(JPanel panel, MainTable table) {
        super("Remove");
        setPreferredSize(new Dimension(90, 30));

        table.getSelectionModel().addListSelectionListener(event -> setEnabled(table.getSelectedRow() != -1));
        setEnabled(false);

        addActionListener((e) -> {
            int[] arr = table.getSelectedRows();
            for(int i = 0; i < arr.length; i++) {
                arr[i] = table.convertRowIndexToModel(arr[i]);
            }

            FileManager.getInstance().removeFiles(arr);

            table.reloadSorter();
        });

        panel.add(this);
    }
}
