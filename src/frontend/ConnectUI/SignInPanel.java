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
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
                .border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder
                .CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.
                awt.Font.BOLD, 12), java.awt.Color.red), getBorder()))
        ;
        addPropertyChangeListener(e -> {
            if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
        })
        ;
        setLayout(null);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        add(usernameLabel);
        usernameLabel.setBounds(0, 10, 85, usernameLabel.getPreferredSize().height);
        add(usernameField);
        usernameField.setBounds(0, 25, 300, 30);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        add(passwordLabel);
        passwordLabel.setBounds(0, 65, 100, passwordLabel.getPreferredSize().height);
        add(passwordField);
        passwordField.setBounds(0, 80, 300, 30);

        //---- enterButton ----
        enterButton.setText("Sign In");
        enterButton.addActionListener(this::enter);
        add(enterButton);
        enterButton.setBounds(new Rectangle(new Point(220, 155), enterButton.getPreferredSize()));

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
