package com.decker.clientupdate.util;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUILauncher {
    public static void Run(final JFrame frame)
            throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        final Object lock = new Object();
        frame.setDefaultCloseOperation(1);
        frame.setVisible(true);

        Thread t = new Thread() {
            public void run() {
                synchronized (lock) {
                    while (frame.isVisible()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                synchronized (lock) {
                    frame.setVisible(false);
                    lock.notify();
                }
            }
        });
        t.join();
    }

    public static void Launch(JFrame frame)
            throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        frame.setVisible(true);
    }
}
