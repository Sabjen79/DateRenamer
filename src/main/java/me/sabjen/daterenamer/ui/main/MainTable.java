package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.RenamableFile;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.PatternSyntaxException;

class MainTable extends JTable {
    private TableRowSorter<MainTableModel> sorter;
    private final MainTableModel model;

    public MainTable(JPanel panel) {
        super();
        getTableHeader().setReorderingAllowed(false);
        setFillsViewportHeight(true);
        model = new MainTableModel();
        setModel(model);

        sorter = new TableRowSorter<>(model);
        setRowSorter(sorter);

        JScrollPane pane = new JScrollPane(this);
        panel.add(BorderLayout.CENTER, pane);
    }

    public void update(JTextField field) {
        RowFilter<Object, Object> s = null;
        if(field.getText().length() > 0) {
            try {
                s = RowFilter.regexFilter("(?i)" + field.getText(), 0);
            } catch (PatternSyntaxException e) {

            }
        }

        sorter.setRowFilter(s);
    }

    public void reloadSorter() {
        var newSorter = new TableRowSorter<>(model);

        newSorter.setRowFilter(sorter.getRowFilter());
        newSorter.setSortKeys(sorter.getSortKeys());

        sorter = newSorter;
        setRowSorter(sorter);
    }

    @Override
    public MainTableModel getModel() {
        return model;
    }
}
