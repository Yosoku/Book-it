/*
 * Created by JFormDesigner on Thu Dec 16 23:20:20 EET 2021
 */

package frontend.ConnectUI;

import backend.application.Application;
import backend.application.Server;
import backend.auth.Credentials;
import backend.users.Broker;
import backend.users.Customer;
import backend.users.Gender;
import backend.users.User;
import config.Configurations;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class SignUpPanel extends JPanel {
    public static User user = null;
    Image background;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel phoneLabel;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JCheckBox brokerCheckbox;
    private JLabel usernameLabel;
    private JFormattedTextField firstnameField;
    private JFormattedTextField lastnameField;
    private JFormattedTextField usernameField;
    private JFormattedTextField emailField;
    private JFormattedTextField phoneField;
    private JSlider ageSlider;
    private JLabel ageLabel;
    private JButton enterButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private ButtonGroup group;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    public SignUpPanel() {
        initComponents();
    }

    private synchronized void signUpButton(ActionEvent e) {
        boolean canSignup = true;
        for (Component component : getComponents()) {
            if (component instanceof JFormattedTextField) {
                if (!((JFormattedTextField) component).isEditValid()) {
                    canSignup = false;
                    break;
                }
            }
        }
        canSignup = getGender() != null; //This forces the User to select a gender
        if (canSignup) {
            String fullname = firstnameField.getText() + lastnameField.getText();
            Credentials credentials =
                    new Credentials(usernameField.getText(), new String(passwordField.getPassword()));
            if (brokerCheckbox.isSelected()) {
                user = new Broker(credentials, fullname, ageSlider.getValue(),
                        emailField.getText(), getGender(), phoneField.getText(), "sda");
            } else {
                user = new Customer(credentials, fullname, ageSlider.getValue(),
                        emailField.getText(), getGender(), phoneField.getText());
            }
            Server.sendRequest("signup");
        } else {
            //TODO expand here a bit
            JOptionPane.showMessageDialog(null, "Red is bad and you should feel bad", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void notify(Application instance) {
        notify();
    }

    private Gender getGender() {
        if (maleButton.isSelected())
            return Gender.MALE;
        else if (femaleButton.isSelected())
            return Gender.FEMALE;
        return null;
    }

    private void firstnameFieldCaretUpdate(CaretEvent e) {
        if (firstnameField.getText().matches(Configurations.NAME_REGEX))
            firstnameLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            firstnameLabel.setForeground(Configurations.INVALID_TEXT_COLOR);

    }

    private void lastnameFieldCaretUpdate(CaretEvent e) {
        if (lastnameField.getText().matches(Configurations.NAME_REGEX))
            lastnameLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            lastnameLabel.setForeground(Configurations.INVALID_TEXT_COLOR);

    }

    private void usernameFieldCaretUpdate(CaretEvent e) {
        if (usernameField.getText().matches(Configurations.USERNAME_REGEX))
            usernameLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            usernameLabel.setForeground(Configurations.INVALID_TEXT_COLOR);

    }

    private void emailFieldCaretUpdate(CaretEvent e) {
        if (emailField.getText().matches(Configurations.EMAIL_REGEX))
            emailLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            emailLabel.setForeground(Configurations.INVALID_TEXT_COLOR);

    }

    private void phoneFieldCaretUpdate(CaretEvent e) {
        if (phoneField.getText().matches(Configurations.PHONE_REGEX))
            phoneLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            phoneLabel.setForeground(Configurations.INVALID_TEXT_COLOR);

    }

    private void passwordFieldCaretUpdate(CaretEvent e) {
        if (new String(passwordField.getPassword()).matches(Configurations.PASSWORD_REGEX))
            passwordLabel.setForeground(Configurations.VALID_TEXT_COLOR);
        else
            passwordLabel.setForeground(Configurations.INVALID_TEXT_COLOR);
    }

    private void ageSliderStateChanged(ChangeEvent e) {
        ageLabel.setText("Age:" + ageSlider.getValue());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        firstnameLabel = new JLabel();
        lastnameLabel = new JLabel();
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        phoneLabel = new JLabel();
        maleButton = new JRadioButton();
        femaleButton = new JRadioButton();
        brokerCheckbox = new JCheckBox();
        usernameLabel = new JLabel();
        firstnameField = new JFormattedTextField();
        lastnameField = new JFormattedTextField();
        usernameField = new JFormattedTextField();
        emailField = new JFormattedTextField();
        phoneField = new JFormattedTextField();
        ageSlider = new JSlider();
        ageLabel = new JLabel();
        enterButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(300, 380));
        setPreferredSize(new Dimension(300, 380));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
                , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                , new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12)
                , java.awt.Color.red), getBorder()));
        addPropertyChangeListener(
                e -> {
                    if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException()
                            ;
                });
        setLayout(null);

        //---- firstnameLabel ----
        firstnameLabel.setText("First name");
        add(firstnameLabel);
        firstnameLabel.setBounds(new Rectangle(new Point(0, 5), firstnameLabel.getPreferredSize()));

        //---- lastnameLabel ----
        lastnameLabel.setText("Last name");
        add(lastnameLabel);
        lastnameLabel.setBounds(150, 5, 90, lastnameLabel.getPreferredSize().height);

        //---- emailLabel ----
        emailLabel.setText("Email");
        add(emailLabel);
        emailLabel.setBounds(new Rectangle(new Point(0, 110), emailLabel.getPreferredSize()));

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        add(passwordLabel);
        passwordLabel.setBounds(new Rectangle(new Point(0, 165), passwordLabel.getPreferredSize()));

        //---- passwordField ----
        passwordField.addCaretListener(this::passwordFieldCaretUpdate);
        add(passwordField);
        passwordField.setBounds(0, 180, 300, 30);

        //---- phoneLabel ----
        phoneLabel.setText("Phone number");
        add(phoneLabel);
        phoneLabel.setBounds(new Rectangle(new Point(0, 215), phoneLabel.getPreferredSize()));

        //---- maleButton ----
        maleButton.setText("Male");
        add(maleButton);
        maleButton.setBounds(new Rectangle(new Point(0, 325), maleButton.getPreferredSize()));

        //---- femaleButton ----
        femaleButton.setText("Female");
        add(femaleButton);
        femaleButton.setBounds(new Rectangle(new Point(60, 325), femaleButton.getPreferredSize()));

        //---- brokerCheckbox ----
        brokerCheckbox.setText("Broker");
        brokerCheckbox.setToolTipText("Check if you want to register as a Broker");
        add(brokerCheckbox);
        brokerCheckbox.setBounds(new Rectangle(new Point(130, 355), brokerCheckbox.getPreferredSize()));

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        add(usernameLabel);
        usernameLabel.setBounds(new Rectangle(new Point(0, 60), usernameLabel.getPreferredSize()));

        //---- firstnameField ----
        firstnameField.addCaretListener(this::firstnameFieldCaretUpdate);
        add(firstnameField);
        firstnameField.setBounds(0, 20, 140, 30);

        //---- lastnameField ----
        lastnameField.addCaretListener(this::lastnameFieldCaretUpdate);
        add(lastnameField);
        lastnameField.setBounds(150, 20, 150, 30);

        //---- usernameField ----
        usernameField.addCaretListener(this::usernameFieldCaretUpdate);
        add(usernameField);
        usernameField.setBounds(0, 75, 300, 30);

        //---- emailField ----
        emailField.addCaretListener(this::emailFieldCaretUpdate);
        add(emailField);
        emailField.setBounds(0, 125, 300, 30);

        //---- phoneField ----
        phoneField.addCaretListener(this::phoneFieldCaretUpdate);
        add(phoneField);
        phoneField.setBounds(0, 230, 300, 30);

        //---- ageSlider ----
        ageSlider.setMajorTickSpacing(15);
        ageSlider.setMinimum(18);
        ageSlider.setMaximum(98);
        ageSlider.setValue(18);
        ageSlider.addChangeListener(this::ageSliderStateChanged);
        add(ageSlider);
        ageSlider.setBounds(0, 290, 255, 30);

        //---- ageLabel ----
        ageLabel.setText("Age:18");
        add(ageLabel);
        ageLabel.setBounds(0, 285, 70, ageLabel.getPreferredSize().height);

        //---- enterButton ----
        enterButton.setText("Sign up");
        enterButton.setIcon(null);
        enterButton.addActionListener(this::signUpButton);
        add(enterButton);
        enterButton.setBounds(215, 335, 70, 40);

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
        group = new ButtonGroup();
        group.add(femaleButton);
        group.add(maleButton);
        setVisible(true);
        ageSlider.setPaintLabels(true);
        //Label tooltips
        firstnameLabel.setToolTipText("Valid first names contain only characters");
        lastnameLabel.setToolTipText("Valid last names contain only characters");
        usernameLabel.setToolTipText("Valid usernames contain only characters and digits");
        emailLabel.setToolTipText("Valid emails contain characters,digits and a valid domain");
        phoneLabel.setToolTipText("Valid phone numbers contain only digits");
        passwordLabel.setToolTipText("Password should be minumum 8 characters");

        Image background = Toolkit.getDefaultToolkit().createImage("background.jpg");
    }


}
