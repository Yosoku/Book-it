/*
 * Created by JFormDesigner on Sun Jan 16 15:02:39 EET 2022
 */

package frontend.usersgui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Edward
 */
public class HelpPanel extends JPanel {
    public HelpPanel() {
        initComponents();
    }

    private void help(ActionEvent e) {
        readmeButton.setVisible(!readmeButton.isVisible());
        aboutButton.setVisible(!aboutButton.isVisible());
        contactButton.setVisible(!contactButton.isVisible());
        exitButton.setVisible(!exitButton.isVisible());
    }

    private void readme(ActionEvent e) {
        try {

            Desktop.getDesktop().browse(new URI("https://github.com/auth-csd-oop-2021/mybooking-team-rocket/blob/main/README.md"));
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    private void about(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/Yosoku"));

        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    private void contact(ActionEvent e) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection("Edouardos@csd.auth.gr"),
                        null
                );
        JOptionPane.showMessageDialog(null,
                "Email address has been copied to clipboard,you will be redirected to a contact page", "Redirect", JOptionPane.INFORMATION_MESSAGE);
        try {
            Desktop.getDesktop().browse(new URI(("https://webmail.auth.gr/imp/dynamic.php?page=compose")));
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    private void exit(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null, "You are about to exit the program", "Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION)
            System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        readmeButton = new JButton();
        aboutButton = new JButton();
        contactButton = new JButton();
        exitButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        setBackground(new Color(21, 97, 109));
        setMinimumSize(new Dimension(140, 370));
        setMaximumSize(new Dimension(140, 370));
        setPreferredSize(new Dimension(140, 370));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax
                .swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD,
                12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("\u0062order".equals(e.
                    getPropertyName())) throw new RuntimeException();
        });
        setLayout(null);

        //---- readmeButton ----
        readmeButton.setBorder(null);
        readmeButton.setIcon(new ImageIcon(getClass().getResource("/readme.png")));
        readmeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        readmeButton.addActionListener(this::readme);
        add(readmeButton);
        readmeButton.setBounds(5, 90, 130, 40);

        //---- aboutButton ----
        aboutButton.setIcon(new ImageIcon(getClass().getResource("/about.png")));
        aboutButton.setBorder(null);
        aboutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutButton.addActionListener(this::about);
        add(aboutButton);
        aboutButton.setBounds(5, 170, 130, 40);

        //---- contactButton ----
        contactButton.setIcon(new ImageIcon(getClass().getResource("/contact.png")));
        contactButton.setBorder(null);
        contactButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contactButton.addActionListener(this::contact);
        add(contactButton);
        contactButton.setBounds(5, 250, 130, 40);

        //---- exitButton ----
        exitButton.setBorder(null);
        exitButton.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(this::exit);
        add(exitButton);
        exitButton.setBounds(5, 330, 130, 40);

        //---- helpButton ----
        helpButton.setFocusable(false);
        helpButton.setIcon(new ImageIcon(getClass().getResource("/helpIcon.png")));
        helpButton.setSelectedIcon(new ImageIcon(getClass().getResource("/helpIcon.png")));
        helpButton.setContentAreaFilled(false);
        helpButton.setFocusPainted(false);
        helpButton.setBorder(null);
        helpButton.setBorderPainted(false);
        helpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        helpButton.addActionListener(this::help);
        add(helpButton);
        helpButton.setBounds(5, 15, 130, helpButton.getPreferredSize().height);

        setPreferredSize(new Dimension(140, 490));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        exitButton.setVisible(false);
        aboutButton.setVisible(false);
        contactButton.setVisible(false);
        readmeButton.setVisible(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton readmeButton;
    private JButton aboutButton;
    private JButton contactButton;
    private JButton exitButton;
    private JButton helpButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
