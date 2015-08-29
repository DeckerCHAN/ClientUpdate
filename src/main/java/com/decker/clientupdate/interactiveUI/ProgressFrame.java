package com.decker.clientupdate.interactiveUI;

import org.jdesktop.layout.GroupLayout;

import javax.swing.*;
import java.awt.*;

public class ProgressFrame
        extends JFrame {
    private JPanel mainPanel;
    private JProgressBar progress;
    private JLabel status;
    private JLabel statusLabel;

    public ProgressFrame() {
        initComponents();
    }

    private void initComponents() {
        this.mainPanel = new JPanel();
        this.statusLabel = new JLabel();
        this.status = new JLabel();
        this.progress = new JProgressBar();

        setDefaultCloseOperation(3);

        this.mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "BuildingEnvironment"));

        this.statusLabel.setFont(new Font("SansSerif", 1, 18));
        this.statusLabel.setText("Status:");

        this.status.setFont(new Font("SansSerif", 0, 18));

        GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
        this.mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout
                .createParallelGroup(1)
                .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(mainPanelLayout.createParallelGroup(1)
                                .add(mainPanelLayout.createSequentialGroup()
                                        .add(0, 13, 32767)
                                        .add(this.statusLabel)
                                        .addPreferredGap(0)
                                        .add(this.status, -2, 490, -2))
                                .add(this.progress, -1, -1, 32767))
                        .addContainerGap()));

        mainPanelLayout.setVerticalGroup(mainPanelLayout
                .createParallelGroup(1)
                .add(mainPanelLayout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(mainPanelLayout.createParallelGroup(3)
                                .add(this.statusLabel)
                                .add(this.status, -1, -1, 32767))
                        .add(18, 18, 18)
                        .add(this.progress, -2, 33, -2)
                        .addContainerGap()));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(1)
                .add(2, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(this.mainPanel, -1, -1, 32767)
                        .addContainerGap()));

        layout.setVerticalGroup(layout
                .createParallelGroup(1)
                .add(2, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(this.mainPanel, -1, -1, 32767)
                        .addContainerGap()));


        setSize(new Dimension(641, 181));
        setLocationRelativeTo(null);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setProgressBarColor(Color color) {
        this.progress.setForeground(color);
    }

    public void setProgress(Double value) {
        this.progress.setValue((int) Math.round(value.doubleValue() * 100.0D));
    }
}

