package ru.vsu.cs.course1;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new FrameMain().setVisible(true);
            }
        });
    }
}