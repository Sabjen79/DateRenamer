package me.sabjen.daterenamer;

import me.sabjen.daterenamer.ui.UILook;
import me.sabjen.daterenamer.ui.start.StartUI;

public class Main {
    public static void main(String[] args) {
        UILook.setLook();
        StartUI.getInstance().setVisible(true);
    }
}
