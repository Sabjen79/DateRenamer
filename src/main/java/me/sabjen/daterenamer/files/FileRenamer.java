package me.sabjen.daterenamer.files;

import me.sabjen.daterenamer.ui.load.LoadUI;
import me.sabjen.daterenamer.ui.main.MainUI;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatException;

public class FileRenamer {
    private static final FileRenamer INSTANCE = new FileRenamer();
    public static FileRenamer getInstance() {
        return INSTANCE;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    private String format;

    private FileRenamer() {
        format = "yyyy-MM-dd HH-mm-ss";
    }

    public void renameAll(ArrayList<RenamableFile> files) {
        new Thread(() -> {
            for(int i = 0; i < files.size(); i++) {
                LoadUI.getInstance().setValue(i+1);
                var file = files.get(i);
                String newPath = file.getParentFile().getAbsolutePath() + "\\" + file.getNewName();

                if(file.getNewName().equals(file.getName())) continue;

                boolean ok = file.renameTo(new File(newPath));
                int tries = 0;
                while(!ok && tries < 1000) {
                    tries++;
                    String path = file.getParentFile().getAbsolutePath() + "\\" + file.getNewName(tries);
                    ok = file.renameTo(new File(path));
                }
            }

            LoadUI.getInstance().setVisible(false);
            MainUI.getInstance().setEnabled(true);
        }).start();
    }

    public String getString(Date date) {
        String str = "";
        try {
            str = new SimpleDateFormat(format).format(date);
        } catch (IllegalArgumentException e) {
            return "";
        }

        return str;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
