package me.sabjen.daterenamer.ui.main;

import me.sabjen.daterenamer.files.FileManager;
import me.sabjen.daterenamer.files.RenamableFile;

import javax.swing.table.AbstractTableModel;

class MainTableModel extends AbstractTableModel {
    private static final String[] columns = {"Old name", "New name", "Folder"};

    public MainTableModel() {

    }

    @Override
    public int getRowCount() {
        return FileManager.getInstance().getFiles().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RenamableFile file = FileManager.getInstance().getFiles().get(rowIndex);

        return switch (columnIndex) {
            case 0 -> file.getName();
            case 1 -> file.getNewName();
            case 2 -> file.getParentFile().getAbsolutePath();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        //table[row][col] = (String) value;
        fireTableCellUpdated(row, col);
    }
}
