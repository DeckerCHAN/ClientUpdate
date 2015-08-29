package com.decker.clientupdate.interactiveUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ReportFrame
        extends JFrame {
    private JButton confirmButton;
    private JPanel mainPanel;
    private JLabel reportLabel;

    public ReportFrame() {
        initComponents();
    }

    private void initComponents() {
        this.mainPanel = new JPanel();
        this.reportLabel = new JLabel();
        this.confirmButton = new JButton();

        setDefaultCloseOperation(3);

        this.mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Report"));

        this.reportLabel.setHorizontalAlignment(0);
        this.reportLabel.setHorizontalTextPosition(4);

        this.confirmButton.setText("Confirm");
        this.confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ReportFrame.this.confirmButtonActionPerformed(evt);
            }
        });
        GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
        this.mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addContainerGap(473, 32767)
                                        .addComponent(this.confirmButton, -2, 112, -2))
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(this.reportLabel, -1, -1, 32767)))
                        .addContainerGap()));

        mainPanelLayout.setVerticalGroup(mainPanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(25, 32767)
                        .addComponent(this.reportLabel, -2, 32, -2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.confirmButton)
                        .addGap(6, 6, 6)));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.mainPanel, -1, -1, 32767)
                        .addContainerGap()));

        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.mainPanel, -2, -1, -2)
                        .addContainerGap(-1, 32767)));


        setSize(new Dimension(681, 200));
        setLocationRelativeTo(null);
    }

    private void confirmButtonActionPerformed(ActionEvent evt) {
        Window w = this;
        w.getToolkit().getSystemEventQueue().postEvent(new WindowEvent(w, 201));
    }

    public void setReport(String report)
            throws IOException {
        this.reportLabel.setText(report);
    }
}
