/*
 * Created by JFormDesigner on Tue Dec 28 23:31:46 EET 2021
 */

package frontend.usersgui;

import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.users.User;
import config.Configurations;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * @author Edward
 */
public class ProfileView extends JFrame {
    private User user;
    private static ProfileView instance = null;

    public static ProfileView getInstance() {
        if (instance == null)
            instance = new ProfileView(Application.getInstance().getCurrentUser());
        return instance;
    }

    public static ProfileView getInstance(User user) {
        if (instance == null)
            instance = new ProfileView(user);
        return instance;
    }

    private ProfileView(User user) {
        this.user = user;
        initComponents();
    }

    public static void closeView() {
        ProfileView.getInstance().dispose();
        instance = null;
    }

    private void edit(ActionEvent e) {
        saveEditsButton.setVisible(true);
        nameField.setEditable(true);
        emailField.setEditable(true);
        phoneField.setEditable(true);
        ageField.setEditable(true);

    }

    private void saveEdits(ActionEvent e) {
        String message = "";
        if (nameField.getText().matches(Configurations.NAME_REGEX)) {
            message += "Name saved succesfully\n";
            user.setName(nameField.getText());
        } else
            message += "Name invalid,no changes were made\n";
        if (emailField.getText().matches(Configurations.EMAIL_REGEX)) {
            message += "Email saved successfully\n";
            user.setEmail(emailField.getText());
        } else
            message += "Email invalid,no changes were made\n";
        if (phoneField.getText().matches(Configurations.PHONE_REGEX)) {
            message += "Phone saved successfully\n";
            user.setPhone(phoneField.getText());
        } else
            message += "Phone invalid,no changes were made\n";
        if (ageField.getText().matches(Configurations.AGE_REGEX)) {
            user.setAge(Integer.parseInt(ageField.getText()));
            message += "Age saved successfully\n";
        } else
            message += "Age invalid,no changes were made\n";

        JOptionPane.showMessageDialog(null, message, "Changes", JOptionPane.INFORMATION_MESSAGE);
        saveEditsButton.setVisible(false);
        nameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);
        ageField.setEditable(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        ageField = new JFormattedTextField();
        genderField = new JFormattedTextField();
        genderLabel = new JLabel();
        privilegeLabel = new JLabel();
        privilegeField = new JFormattedTextField();
        ageLabel = new JLabel();
        phoneLabel = new JLabel();
        phoneField = new JFormattedTextField();
        emailField = new JFormattedTextField();
        emailLabel = new JLabel();
        nameLabel = new JLabel();
        nameField = new JFormattedTextField();
        editButton = new JButton();
        saveEditsButton = new JButton();
        imageLabel = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(550, 400));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("Profile");
        setVisible(true);
        setBackground(new Color(0, 21, 36));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                    .swing.border.EmptyBorder(0, 0, 0, 0), "", javax.swing
                    .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                    Font("", java.awt.Font.BOLD, 12), java.awt.Color.red
            ), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("borde\u0072".equals(e.getPropertyName(
                ))) throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- ageField ----
            ageField.setEditable(false);
            ageField.setBackground(new Color(0, 21, 36));
            ageField.setForeground(new Color(221, 199, 160));
            ageField.setFont(ageField.getFont().deriveFont(ageField.getFont().getSize() + 2f));
            panel1.add(ageField);
            ageField.setBounds(160, 160, 220, 30);

            //---- genderField ----
            genderField.setEditable(false);
            genderField.setBackground(new Color(0, 21, 36));
            genderField.setForeground(new Color(221, 199, 160));
            genderField.setFont(genderField.getFont().deriveFont(genderField.getFont().getSize() + 2f));
            panel1.add(genderField);
            genderField.setBounds(160, 205, 220, 30);

            //---- genderLabel ----
            genderLabel.setText("Gender");
            genderLabel.setForeground(new Color(221, 199, 160));
            genderLabel.setFont(genderLabel.getFont().deriveFont(genderLabel.getFont().getSize() + 2f));
            panel1.add(genderLabel);
            genderLabel.setBounds(50, 205, 60, 30);

            //---- privilegeLabel ----
            privilegeLabel.setText("Account Type");
            privilegeLabel.setForeground(new Color(221, 199, 160));
            privilegeLabel.setFont(privilegeLabel.getFont().deriveFont(privilegeLabel.getFont().getSize() + 2f));
            panel1.add(privilegeLabel);
            privilegeLabel.setBounds(50, 250, 110, 30);

            //---- privilegeField ----
            privilegeField.setEditable(false);
            privilegeField.setBackground(new Color(0, 21, 36));
            privilegeField.setForeground(new Color(221, 199, 160));
            privilegeField.setFont(privilegeField.getFont().deriveFont(privilegeField.getFont().getSize() + 2f));
            panel1.add(privilegeField);
            privilegeField.setBounds(160, 250, 220, 30);

            //---- ageLabel ----
            ageLabel.setText("Age");
            ageLabel.setForeground(new Color(221, 199, 160));
            ageLabel.setFont(ageLabel.getFont().deriveFont(ageLabel.getFont().getSize() + 2f));
            panel1.add(ageLabel);
            ageLabel.setBounds(50, 160, 60, 30);

            //---- phoneLabel ----
            phoneLabel.setText("Phone");
            phoneLabel.setForeground(new Color(221, 199, 160));
            phoneLabel.setFont(phoneLabel.getFont().deriveFont(phoneLabel.getFont().getSize() + 2f));
            panel1.add(phoneLabel);
            phoneLabel.setBounds(50, 115, 60, 30);

            //---- phoneField ----
            phoneField.setEditable(false);
            phoneField.setBackground(new Color(0, 21, 36));
            phoneField.setForeground(new Color(221, 199, 160));
            phoneField.setFont(phoneField.getFont().deriveFont(phoneField.getFont().getSize() + 2f));
            panel1.add(phoneField);
            phoneField.setBounds(160, 115, 220, 30);

            //---- emailField ----
            emailField.setEditable(false);
            emailField.setBackground(new Color(0, 21, 36));
            emailField.setForeground(new Color(221, 199, 160));
            emailField.setFont(emailField.getFont().deriveFont(emailField.getFont().getSize() + 2f));
            panel1.add(emailField);
            emailField.setBounds(160, 70, 220, 30);

            //---- emailLabel ----
            emailLabel.setText("Email");
            emailLabel.setForeground(new Color(221, 199, 160));
            emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getSize() + 2f));
            panel1.add(emailLabel);
            emailLabel.setBounds(50, 70, 60, 30);

            //---- nameLabel ----
            nameLabel.setText("Name");
            nameLabel.setForeground(new Color(221, 199, 160));
            nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getSize() + 2f));
            panel1.add(nameLabel);
            nameLabel.setBounds(50, 25, 60, 30);

            //---- nameField ----
            nameField.setEditable(false);
            nameField.setBackground(new Color(0, 21, 36));
            nameField.setForeground(new Color(221, 199, 160));
            nameField.setFont(nameField.getFont().deriveFont(nameField.getFont().getSize() + 2f));
            panel1.add(nameField);
            nameField.setBounds(160, 25, 220, 30);

            //---- editButton ----
            editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            editButton.setBorderPainted(false);
            editButton.setIcon(new ImageIcon(getClass().getResource("/profile/edit.png")));
            editButton.addActionListener(this::edit);
            panel1.add(editButton);
            editButton.setBounds(225, 305, 80, 30);

            //---- saveEditsButton ----
            saveEditsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            saveEditsButton.setBorderPainted(false);
            saveEditsButton.setIcon(new ImageIcon(getClass().getResource("/profile/save.png")));
            saveEditsButton.setVisible(false);
            saveEditsButton.addActionListener(this::saveEdits);
            panel1.add(saveEditsButton);
            saveEditsButton.setBounds(300, 305, 80, 30);

            //---- imageLabel ----
            imageLabel.setForeground(new Color(221, 199, 160));
            imageLabel.setText("Add an Image");
            imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            imageLabel.setBorder(new TitledBorder(""));
            panel1.add(imageLabel);
            imageLabel.setBounds(435, 15, 100, 100);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 550, 370);

        contentPane.setPreferredSize(new Dimension(550, 400));
        setSize(550, 400);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        ageField.setText(String.valueOf(user.getAge()));
        genderField.setText(user.getGender().toString());
        privilegeField.setText(user.getPrivilege().toString());


        //Profile pic stuff
        imageLabel.setIcon(user.getIcon());
        if (imageLabel.getIcon() == null) {
            imageLabel.setText("Add an image by clicking here");
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (imageLabel.getIcon() != null)
                        return;
                    System.err.println("Adding image");
                    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    FileFilter imageFilter = new FileNameExtensionFilter(
                            "Image files", ImageIO.getReaderFileSuffixes());
                    jfc.setFileFilter(imageFilter);
                    int returnValue = jfc.showOpenDialog(null);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = jfc.getSelectedFile();
                        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image image = imageIcon.getImage(); // transform it
                        Image newimg = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        imageIcon = new ImageIcon(newimg);  // transform it back
                        imageLabel.setIcon(imageIcon);
                        user.setIcon(imageIcon);
                        DatabaseAPI.credentialsUserDatabase.write();
                        DatabaseAPI.userConfirmationsDatabase.write();
                    }
                }
            });
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JFormattedTextField ageField;
    private JFormattedTextField genderField;
    private JLabel genderLabel;
    private JLabel privilegeLabel;
    private JFormattedTextField privilegeField;
    private JLabel ageLabel;
    private JLabel phoneLabel;
    private JFormattedTextField phoneField;
    private JFormattedTextField emailField;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JFormattedTextField nameField;
    private JButton editButton;
    private JButton saveEditsButton;
    private JLabel imageLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
