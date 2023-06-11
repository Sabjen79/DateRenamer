package me.sabjen.daterenamer.files;

import me.sabjen.daterenamer.ui.load.LoadUI;
import me.sabjen.daterenamer.ui.main.MainUI;
import me.sabjen.daterenamer.util.DateFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileManager {
    private static final FileManager INSTANCE = new FileManager();
    public static FileManager getInstance() {
        return INSTANCE;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    private final ArrayList<RenamableFile> files, filesToAdd;
    private final List<Thread> threadList;
    private int progress = 0;
    public ArrayList<RenamableFile> getFiles() { return files; }

    private FileManager() {
        files = new ArrayList<>();
        filesToAdd = new ArrayList<>();
        threadList = new LinkedList<>();
    }

    public void addFilesFrom(File[] selection) {
        for(var f : selection) addFiles(f);

        progress = 0;
        MainUI.getInstance().setVisible(false);
        LoadUI.getInstance().setVisible(true);
        LoadUI.getInstance().setValue(0).setMaximum(filesToAdd.size());

        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                computeDate();
            }

            try {
                for (var t : threadList) t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            files.addAll(filesToAdd);
            filesToAdd.clear();
            MainUI.getInstance().reloadTableSorter();

            LoadUI.getInstance().setVisible(false);
            MainUI.getInstance().setVisible(true);
            progress = 0;
        }).start();
    }

    public void removeFiles(int[] rows) {
        ArrayList<RenamableFile> filesToRemove = new ArrayList<>();

        for(int i : rows) {
            filesToRemove.add(files.get(i));
        }

        files.removeAll(filesToRemove);
    }

    public void removeFiles(ArrayList<RenamableFile> filesToRemove) {
        files.removeAll(filesToRemove);
    }

    private void computeDate() {
        Thread t = new Thread(() -> {
            while(true) {
                int start = 0, end = 0;
                synchronized (this) {
                    if(progress == filesToAdd.size()) return;

                    start = progress;
                    end = Math.min(filesToAdd.size(), progress + 50);
                    progress += end-start;

                    LoadUI.getInstance().setValue(progress);
                }

                for(int i = start; i < end; i++) {
                    var item = filesToAdd.get(i);

                    item.setDate(DateFinder.getDate(item));
                }
            }
        });

        threadList.add(t);
        t.start();
    }

    private void addFiles(File folder) {
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles == null) {
            if(!files.contains(folder)) filesToAdd.add(new RenamableFile(folder));
            return;
        }

        for(File file : listOfFiles) {
            if(file.isDirectory()) {
                addFiles(file);
            } else {
                if(!files.contains(file)) filesToAdd.add(new RenamableFile(file));
            }
        }
    }
}
