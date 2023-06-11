package me.sabjen.daterenamer.files;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class RenamableFile extends File {
    private Date date;

    public RenamableFile(String pathname) {
        super(pathname);
    }

    public RenamableFile(File file) {
        this(file.getAbsolutePath());
    }

    public String getNewName() {
        String extension = "";
        if(getName().lastIndexOf('.') != -1) extension = getName().substring(getName().lastIndexOf('.'));
        return FileRenamer.getInstance().getString(date) + extension;
    }

    public String getNewName(int tries) {
        String extension = "";
        if(getName().lastIndexOf('.') != -1) extension = getName().substring(getName().lastIndexOf('.'));
        return FileRenamer.getInstance().getString(date) + "_" + tries + extension;
    }

    public void setDate(Date d) {
        date = d;
    }
}
