/*
 * Created by JFormDesigner on Thu Dec 16 23:11:25 EET 2021
 */

package frontend.ConnectUI;

import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.auth.Credentials;
import backend.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Edward
 */
public class SignInPanel extends JPanel {
    public SignInPanel() {
        initComponents();
    }

    public static User user;

    private void enter(ActionEvent e) {
        Credentials credentials = new Credentials
                (usernameField.getText(), new String(passwordField.getPassword()));
        user = DatabaseAPI.credentialsUserDatabase.selectUser(credentials);
        Server.sendRequest("signin");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        usernameLabel = new JLabel();
        usernameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        enterButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(300, 300));
        setMaximumSize(new Dimension(300, 300));
        setPreferredSize(new Dimension(300, 300));
        setBackground(new Color(0, 21, 36));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                .TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD, 12), java.awt
                .Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException()
                    ;
        });
        setLayout(null);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        usernameLabel.setBackground(new Color(221, 199, 160));
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(usernameLabel.getFont().getSize() + 5f));
        usernameLabel.setForeground(new Color(221, 199, 160));
        add(usernameLabel);
        usernameLabel.setBounds(5, 5, 110, usernameLabel.getPreferredSize().height);

        //---- usernameField ----
        usernameField.setBackground(new Color(0, 21, 36));
        usernameField.setFont(usernameField.getFont().deriveFont(usernameField.getFont().getSize() + 3f));
        usernameField.setForeground(new Color(255, 236, 209));
        usernameField.setCaretColor(new Color(255, 236, 209));
        add(usernameField);
        usernameField.setBounds(0, 30, 300, 30);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        passwordLabel.setForeground(new Color(221, 199, 160));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(passwordLabel.getFont().getSize() + 5f));
        add(passwordLabel);
        passwordLabel.setBounds(5, 70, 100, passwordLabel.getPreferredSize().height);

        //---- passwordField ----
        passwordField.setBackground(new Color(0, 21, 36));
        passwordField.setFont(passwordField.getFont().deriveFont(passwordField.getFont().getSize() + 3f));
        passwordField.setForeground(new Color(255, 236, 209));
        passwordField.setCaretColor(new Color(255, 236, 209));
        add(passwordField);
        passwordField.setBounds(0, 90, 300, 30);

        //---- enterButton ----
        enterButton.setIcon(new ImageIcon(getClass().getResource("/ConnectPanel/signin.png")));
        enterButton.setBorder(null);
        enterButton.setBorderPainted(false);
        enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        enterButton.addActionListener(this::enter);
        add(enterButton);
        enterButton.setBounds(190, 140, 100, 40);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    enter(null);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton enterButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
