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
        setBackground(new Color(0, 21, 36));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax
                .swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD,
                12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("bord\u0065r".equals(e.
                    getPropertyName())) throw new RuntimeException();
        });
        setLayout(null);

        //---- firstnameLabel ----
        firstnameLabel.setText("First name");
        firstnameLabel.setForeground(new Color(221, 199, 160));
        firstnameLabel.setBackground(new Color(221, 199, 160));
        firstnameLabel.setFont(firstnameLabel.getFont().deriveFont(firstnameLabel.getFont().getSize() + 5f));
        add(firstnameLabel);
        firstnameLabel.setBounds(new Rectangle(new Point(5, 10), firstnameLabel.getPreferredSize()));

        //---- lastnameLabel ----
        lastnameLabel.setText("Last name");
        lastnameLabel.setForeground(new Color(221, 199, 160));
        lastnameLabel.setBackground(new Color(221, 199, 160));
        lastnameLabel.setFont(lastnameLabel.getFont().deriveFont(lastnameLabel.getFont().getSize() + 5f));
        add(lastnameLabel);
        lastnameLabel.setBounds(155, 10, 130, lastnameLabel.getPreferredSize().height);

        //---- emailLabel ----
        emailLabel.setText("Email");
        emailLabel.setForeground(new Color(221, 199, 160));
        emailLabel.setBackground(new Color(221, 199, 160));
        emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getSize() + 5f));
        add(emailLabel);
        emailLabel.setBounds(5, 125, 75, emailLabel.getPreferredSize().height);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        passwordLabel.setForeground(new Color(221, 199, 160));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(passwordLabel.getFont().getSize() + 5f));
        add(passwordLabel);
        passwordLabel.setBounds(new Rectangle(new Point(5, 200), passwordLabel.getPreferredSize()));

        //---- passwordField ----
        passwordField.setBackground(new Color(0, 21, 36));
        passwordField.setForeground(new Color(255, 236, 209));
        passwordField.setFont(passwordField.getFont().deriveFont(passwordField.getFont().getSize() + 3f));
        passwordField.setCaretColor(new Color(221, 199, 160));
        passwordField.addCaretListener(this::passwordFieldCaretUpdate);
        add(passwordField);
        passwordField.setBounds(0, 220, 300, 30);

        //---- phoneLabel ----
        phoneLabel.setText("Phone number");
        phoneLabel.setForeground(new Color(221, 199, 160));
        phoneLabel.setBackground(new Color(221, 199, 160));
        phoneLabel.setFont(phoneLabel.getFont().deriveFont(phoneLabel.getFont().getSize() + 5f));
        add(phoneLabel);
        phoneLabel.setBounds(new Rectangle(new Point(5, 275), phoneLabel.getPreferredSize()));

        //---- maleButton ----
        maleButton.setText("Male");
        maleButton.setBackground(new Color(0, 21, 36));
        maleButton.setForeground(new Color(221, 199, 160));
        maleButton.setFont(maleButton.getFont().deriveFont(maleButton.getFont().getSize() + 5f));
        add(maleButton);
        maleButton.setBounds(new Rectangle(new Point(5, 410), maleButton.getPreferredSize()));

        //---- femaleButton ----
        femaleButton.setText("Female");
        femaleButton.setBackground(new Color(0, 21, 36));
        femaleButton.setForeground(new Color(221, 199, 160));
        femaleButton.setFont(femaleButton.getFont().deriveFont(femaleButton.getFont().getSize() + 5f));
        add(femaleButton);
        femaleButton.setBounds(new Rectangle(new Point(75, 410), femaleButton.getPreferredSize()));

        //---- brokerCheckbox ----
        brokerCheckbox.setText("Broker");
        brokerCheckbox.setToolTipText("Check if you want to register as a Broker");
        brokerCheckbox.setBackground(new Color(0, 21, 36));
        brokerCheckbox.setForeground(new Color(221, 199, 160));
        brokerCheckbox.setFont(brokerCheckbox.getFont().deriveFont(brokerCheckbox.getFont().getSize() + 5f));
        add(brokerCheckbox);
        brokerCheckbox.setBounds(new Rectangle(new Point(80, 530), brokerCheckbox.getPreferredSize()));

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        usernameLabel.setForeground(new Color(221, 199, 160));
        usernameLabel.setBackground(new Color(221, 199, 160));
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(usernameLabel.getFont().getSize() + 5f));
        add(usernameLabel);
        usernameLabel.setBounds(new Rectangle(new Point(5, 70), usernameLabel.getPreferredSize()));

        //---- firstnameField ----
        firstnameField.setBackground(new Color(0, 21, 36));
        firstnameField.setForeground(new Color(255, 236, 209));
        firstnameField.setFont(firstnameField.getFont().deriveFont(firstnameField.getFont().getSize() + 3f));
        firstnameField.setCaretColor(new Color(221, 199, 160));
        firstnameField.addCaretListener(this::firstnameFieldCaretUpdate);
        add(firstnameField);
        firstnameField.setBounds(0, 30, 140, 30);

        //---- lastnameField ----
        lastnameField.setBackground(new Color(0, 21, 36));
        lastnameField.setForeground(new Color(255, 236, 209));
        lastnameField.setFont(lastnameField.getFont().deriveFont(lastnameField.getFont().getSize() + 3f));
        lastnameField.setCaretColor(new Color(221, 199, 160));
        lastnameField.addCaretListener(this::lastnameFieldCaretUpdate);
        add(lastnameField);
        lastnameField.setBounds(150, 30, 150, 30);

        //---- usernameField ----
        usernameField.setBackground(new Color(0, 21, 36));
        usernameField.setForeground(new Color(255, 236, 209));
        usernameField.setFont(usernameField.getFont().deriveFont(usernameField.getFont().getSize() + 3f));
        usernameField.setCaretColor(new Color(221, 199, 160));
        usernameField.addCaretListener(this::usernameFieldCaretUpdate);
        add(usernameField);
        usernameField.setBounds(0, 90, 300, 30);

        //---- emailField ----
        emailField.setBackground(new Color(0, 21, 36));
        emailField.setForeground(new Color(255, 236, 209));
        emailField.setFont(emailField.getFont().deriveFont(emailField.getFont().getSize() + 3f));
        emailField.setCaretColor(new Color(221, 199, 160));
        emailField.addCaretListener(this::emailFieldCaretUpdate);
        add(emailField);
        emailField.setBounds(0, 150, 300, 30);

        //---- phoneField ----
        phoneField.setBackground(new Color(0, 21, 36));
        phoneField.setForeground(new Color(255, 236, 209));
        phoneField.setFont(phoneField.getFont().deriveFont(phoneField.getFont().getSize() + 3f));
        phoneField.setCaretColor(new Color(221, 199, 160));
        phoneField.addCaretListener(this::phoneFieldCaretUpdate);
        add(phoneField);
        phoneField.setBounds(0, 295, 300, 30);

        //---- ageSlider ----
        ageSlider.setMajorTickSpacing(15);
        ageSlider.setMinimum(18);
        ageSlider.setMaximum(98);
        ageSlider.setValue(18);
        ageSlider.setBackground(new Color(0, 21, 36));
        ageSlider.setForeground(new Color(221, 199, 160));
        ageSlider.addChangeListener(this::ageSliderStateChanged);
        add(ageSlider);
        ageSlider.setBounds(5, 375, 290, 30);

        //---- ageLabel ----
        ageLabel.setText("Age:18");
        ageLabel.setBackground(new Color(221, 199, 160));
        ageLabel.setFont(ageLabel.getFont().deriveFont(ageLabel.getFont().getSize() + 5f));
        ageLabel.setForeground(new Color(221, 199, 160));
        add(ageLabel);
        ageLabel.setBounds(5, 345, 70, ageLabel.getPreferredSize().height);

        //---- enterButton ----
        enterButton.setIcon(new ImageIcon(getClass().getResource("/ConnectPanel/signup.png")));
        enterButton.setBorder(null);
        enterButton.setBorderPainted(false);
        enterButton.addActionListener(this::signUpButton);
        add(enterButton);
        enterButton.setBounds(175, 520, 100, 40);

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
