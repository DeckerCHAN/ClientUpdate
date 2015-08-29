package com.decker.clientupdate.interactiveUI;

import org.jdesktop.layout.GroupLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ConfigFrame
        extends JFrame {
    private JButton confirmButton;
    private JLabel instructionListUrlFIxLabel;
    private JTextField instructionListUrlField;
    private JPanel mainPanel;

    public ConfigFrame() {
        initComponents();
    }

    public String getIListUrl() {
        return this.instructionListUrlField.getText();
    }

    public void setIListUrl(String iListUrl) {
        this.instructionListUrlField.setText(iListUrl);
    }

    private void initComponents() {
        this.mainPanel = new JPanel();
        this.instructionListUrlFIxLabel = new JLabel();
        this.instructionListUrlField = new JTextField();
        this.confirmButton = new JButton();

        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(641, 181));

        this.mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Url Config"));

        this.instructionListUrlFIxLabel.setFont(new Font("Ubuntu", 1, 18));
        this.instructionListUrlFIxLabel.setText("I List Url:");
        this.instructionListUrlFIxLabel.setPreferredSize(new Dimension(61, 21));

        this.confirmButton.setLabel("Confirm");
        this.confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ConfigFrame.this.confirmButtonActionPerformed(evt);
            }
        });
        GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
        this.mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout
                .createParallelGroup(1)
                .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(38, 32767)
                        .add(mainPanelLayout.createParallelGroup(1)
                                .add(2, mainPanelLayout.createSequentialGroup()
                                        .add(this.instructionListUrlFIxLabel, -2, 107, -2)
                                        .addPreferredGap(0)
                                        .add(this.instructionListUrlField, -2, 364, -2)
                                        .add(86, 86, 86))
                                .add(2, mainPanelLayout.createSequentialGroup()
                                        .add(this.confirmButton, -2, 114, -2)
                                        .add(46, 46, 46)))));

        mainPanelLayout.setVerticalGroup(mainPanelLayout
                .createParallelGroup(1)
                .add(mainPanelLayout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(mainPanelLayout.createParallelGroup(3)
                                .add(this.instructionListUrlFIxLabel, -2, 28, -2)
                                .add(this.instructionListUrlField, -2, 28, -2))
                        .addPreferredGap(0, -1, 32767)
                        .add(this.confirmButton)
                        .addContainerGap()));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(1)
                .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(this.mainPanel, -1, -1, 32767)
                        .addContainerGap()));

        layout.setVerticalGroup(layout
                .createParallelGroup(1)
                .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(this.mainPanel, -1, -1, 32767)
                        .addContainerGap()));


        setSize(new Dimension(681, 200));
        setLocationRelativeTo(null);
    }

    private void confirmButtonActionPerformed(ActionEvent evt) {
        if ((this.instructionListUrlField.getText() != null) && (this.instructionListUrlField.getText().length() > 0)) {
            Window w = this;
            w.getToolkit().getSystemEventQueue().postEvent(new WindowEvent(w, 201));
        }
    }
}