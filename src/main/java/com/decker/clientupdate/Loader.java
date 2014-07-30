package com.decker.clientupdate;

import com.decker.clientupdate.interactiveUI.MainFrame;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author decker
 */
public class Loader {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MainFrame().setVisible(true);
                }
            });

           

        } catch (Exception ex) {
        }
       
        return;
    }
}
