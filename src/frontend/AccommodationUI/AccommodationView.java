/*
 * Created by JFormDesigner on Tue Dec 28 10:39:18 EET 2021
 */

package frontend.AccommodationUI;

import backend.accommodations.Accommodation;
import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Broker;
import backend.users.Privilege;
import config.Configurations;
import frontend.ReviewView;

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
public class AccommodationView extends JFrame {
    public static Accommodation accommodation;

    public AccommodationView(Accommodation accommodation) {
        AccommodationView.accommodation = accommodation;
        initComponents();
    }


    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setAllFieldsEditable(boolean editable) {
        addressField.setEditable(editable);
        cityField.setEditable(editable);
        descriptionField.setEditable(editable);
        priceField.setEditable(editable);
        spaceField.setEditable(editable);
        saveEditsButton.setVisible(editable);
    }

    private void reservation(ActionEvent e) {
        MakeReservationView reservationView = new MakeReservationView(accommodation);
        reservationView.setVisible(true);
        dispose();
    }

    private void review(ActionEvent e) {
        JFrame reviewView = new ReviewView(accommodation);
        reviewView.setVisible(true);
    }

    private void edit(ActionEvent e) {
        Server.sendRequest("edit_accommodation");
        setAllFieldsEditable(true);
    }


    private void saveEdits(ActionEvent e) {
        if (Server.getCurrentRequest().equals("add_new_accommodation")) {
            saveNewAccommodation(e);
            return;
        }
        String message = "";
        Accommodation temp = accommodation;
        if (addressField.getText().matches(Configurations.ADDRESS_REGEX)) {
            //message+="Address saved succesfully\n";
            accommodation.setAddress(addressField.getText());
        } else
            message += "Address invalid,no changes were made\n";
        if (cityField.getText().matches(Configurations.ANY_WORD)) {
            //message+="City saved successfully\n";
            accommodation.setCity(cityField.getText());
        } else
            message += "City invalid,no changes were made\n";
        if (descriptionField.getText().matches(Configurations.ANY_TEXT_REGEX)) {
            // message+="Description saved successfully\n";
            accommodation.setDescription(descriptionField.getText());
        } else
            message += "Description invalid,no changes were made\n";
        if (priceField.getText().matches(Configurations.POSITIVE_INTEGER_REGEX)) {
            accommodation.setPrice(Integer.parseInt(priceField.getText()));
            // message+="Price saved successfully\n";
        } else
            message += "Price invalid,no changes were made\n";
        if (spaceField.getText().matches(Configurations.POSITIVE_INTEGER_REGEX)) {
            accommodation.setSpace(Integer.parseInt(spaceField.getText()));
            //message+="Space saved successfully\n";
        } else
            message += "Space invalid,no changes were made";
        if (Server.getCurrentRequest().equals("edit_accommodation")) {
            JOptionPane.showMessageDialog(null, message, "Changes", JOptionPane.INFORMATION_MESSAGE);
            setAllFieldsEditable(false);
        }
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Writing database" + this);
                DatabaseAPI.brokerAccommodationsDatabase.write();
                return null;
            }
        }.execute();
    }

    private void saveNewAccommodation(ActionEvent e) {

        String message = "";
        boolean canSave = true;

        if (spaceField.getText().matches(Configurations.POSITIVE_INTEGER_REGEX)) {
            accommodation.setSpace(Integer.parseInt(spaceField.getText()));

        } else {
            canSave = false;
            message += "Space invalid,no changes were made";
        }
        if (cityField.getText().matches(Configurations.CITY_REGEX)) {
            //message+="City saved successfully\n";
            accommodation.setCity(cityField.getText());
        } else {
            canSave = false;
            message += "City invalid,no saves were made\n";
        }
        if (priceField.getText().matches(Configurations.POSITIVE_INTEGER_REGEX)) {
            accommodation.setPrice(Integer.parseInt(priceField.getText()));
            // message+="Price saved successfully\n";
        } else {
            canSave = false;
            message += "Price invalid,no saves were made\n";
        }
        if (addressField.getText().matches(Configurations.ADDRESS_REGEX)) {
            // message+="Address saved succesfully\n";
            accommodation.setAddress(addressField.getText());
        } else {
            canSave = false;
            message += "Address invalid,no saves were made\n";
        }
        if (descriptionField.getText().matches(Configurations.ANY_TEXT_REGEX)) {
            //message+="Description saved successfully\n";
            accommodation.setDescription(descriptionField.getText());
        } else {
            canSave = false;
            message += "Description invalid,no saves were made\n";
        }
        if (canSave) {
            JOptionPane.showMessageDialog(null, "All changes were saved,happy earnings.", "Save success", JOptionPane.INFORMATION_MESSAGE);
            DatabaseAPI.brokerAccommodationsDatabase.insertAccommodation((Broker) Application.getInstance().getCurrentUser(), accommodation);
            dispose();
        } else {

            JOptionPane.showMessageDialog(null, message, "Changes", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null, "This will delete the accommodation and any reviews/reservations with it. Are you sure?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {
            Server.sendRequest("delete");
            JOptionPane.showMessageDialog(null, "Accommodation was deleted successfully,this window will now close.", "Deletion successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        imageLabel = new JLabel();
        reservationButton = new JButton();
        reviewButton = new JButton();
        spaceField = new JTextField();
        cityField = new JTextField();
        priceField = new JTextField();
        addressField = new JTextField();
        saveEditsButton = new JButton();
        editButton = new JButton();
        descriptionLabel = new JLabel();
        addressLabel = new JLabel();
        priceLabel = new JLabel();
        cityLabel = new JLabel();
        spaceLabel = new JLabel();
        descriptionField = new JTextArea();
        deleteButton = new JButton();
        ratingsLabel = new JLabel();
        reviewLabel = new JLabel();

        //======== this ========
        setTitle("Accommodation");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                    EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing
                    .border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("\u0062ord\u0065r".equals(e.getPropertyName()))
                    throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- imageLabel ----
            imageLabel.setIcon(null);
            panel1.add(imageLabel);
            imageLabel.setBounds(465, 0, 430, 480);

            //---- reservationButton ----
            reservationButton.setIcon(new ImageIcon(getClass().getResource("/reservationButton.png")));
            reservationButton.setBorderPainted(false);
            reservationButton.setBorder(null);
            reservationButton.addActionListener(this::reservation);
            panel1.add(reservationButton);
            reservationButton.setBounds(140, 380, 130, 30);

            //---- reviewButton ----
            reviewButton.setActionCommand(null);
            reviewButton.setBorderPainted(false);
            reviewButton.setBorder(null);
            reviewButton.setIcon(new ImageIcon(getClass().getResource("/reviewButton.png")));
            reviewButton.addActionListener(this::review);
            panel1.add(reviewButton);
            reviewButton.setBounds(25, 380, 100, 30);

            //---- spaceField ----
            spaceField.setEditable(false);
            spaceField.setPreferredSize(new Dimension(80, 30));
            spaceField.setForeground(new Color(255, 236, 209));
            spaceField.setBackground(new Color(0, 21, 36));
            spaceField.setSelectedTextColor(new Color(21, 97, 109));
            spaceField.setCaretColor(new Color(221, 199, 160));
            spaceField.setFont(spaceField.getFont().deriveFont(spaceField.getFont().getSize() + 3f));
            panel1.add(spaceField);
            spaceField.setBounds(140, 40, 155, 30);

            //---- cityField ----
            cityField.setEditable(false);
            cityField.setPreferredSize(new Dimension(80, 30));
            cityField.setForeground(new Color(255, 236, 209));
            cityField.setBackground(new Color(0, 21, 36));
            cityField.setSelectedTextColor(new Color(21, 97, 109));
            cityField.setCaretColor(new Color(221, 199, 160));
            cityField.setFont(cityField.getFont().deriveFont(cityField.getFont().getSize() + 3f));
            panel1.add(cityField);
            cityField.setBounds(140, 75, 155, 30);

            //---- priceField ----
            priceField.setEditable(false);
            priceField.setPreferredSize(new Dimension(80, 30));
            priceField.setForeground(new Color(255, 236, 209));
            priceField.setBackground(new Color(0, 21, 36));
            priceField.setSelectedTextColor(new Color(21, 97, 109));
            priceField.setCaretColor(new Color(221, 199, 160));
            priceField.setFont(priceField.getFont().deriveFont(priceField.getFont().getSize() + 3f));
            panel1.add(priceField);
            priceField.setBounds(140, 120, 155, 30);

            //---- addressField ----
            addressField.setEditable(false);
            addressField.setPreferredSize(new Dimension(80, 30));
            addressField.setForeground(new Color(255, 236, 209));
            addressField.setBackground(new Color(0, 21, 36));
            addressField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            addressField.setSelectedTextColor(new Color(21, 97, 109));
            addressField.setCaretColor(new Color(221, 199, 160));
            addressField.setFont(addressField.getFont().deriveFont(addressField.getFont().getSize() + 3f));
            panel1.add(addressField);
            addressField.setBounds(140, 160, 155, 30);

            //---- saveEditsButton ----
            saveEditsButton.setIcon(new ImageIcon(getClass().getResource("/profile/save.png")));
            saveEditsButton.setBorderPainted(false);
            saveEditsButton.setBorder(null);
            saveEditsButton.addActionListener(this::saveEdits);
            panel1.add(saveEditsButton);
            saveEditsButton.setBounds(190, 280, 80, 30);

            //---- editButton ----
            editButton.setIcon(new ImageIcon(getClass().getResource("/profile/edit.png")));
            editButton.setBorderPainted(false);
            editButton.setBorder(null);
            editButton.addActionListener(this::edit);
            panel1.add(editButton);
            editButton.setBounds(110, 280, 80, 30);

            //---- descriptionLabel ----
            descriptionLabel.setText("Description");
            descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
            descriptionLabel.setForeground(new Color(221, 199, 160));
            descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(descriptionLabel.getFont().getSize() + 5f));
            panel1.add(descriptionLabel);
            descriptionLabel.setBounds(25, 205, 120, 30);

            //---- addressLabel ----
            addressLabel.setText("Address");
            addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
            addressLabel.setForeground(new Color(221, 199, 160));
            addressLabel.setFont(addressLabel.getFont().deriveFont(addressLabel.getFont().getSize() + 5f));
            panel1.add(addressLabel);
            addressLabel.setBounds(25, 165, 80, 30);

            //---- priceLabel ----
            priceLabel.setText("Price");
            priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
            priceLabel.setForeground(new Color(221, 199, 160));
            priceLabel.setFont(priceLabel.getFont().deriveFont(priceLabel.getFont().getSize() + 5f));
            panel1.add(priceLabel);
            priceLabel.setBounds(25, 120, 80, 30);

            //---- cityLabel ----
            cityLabel.setText("City");
            cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
            cityLabel.setForeground(new Color(221, 199, 160));
            cityLabel.setFont(cityLabel.getFont().deriveFont(cityLabel.getFont().getSize() + 5f));
            panel1.add(cityLabel);
            cityLabel.setBounds(25, 80, 80, 30);

            //---- spaceLabel ----
            spaceLabel.setText("Space");
            spaceLabel.setHorizontalAlignment(SwingConstants.LEFT);
            spaceLabel.setForeground(new Color(221, 199, 160));
            spaceLabel.setFont(spaceLabel.getFont().deriveFont(spaceLabel.getFont().getSize() + 5f));
            panel1.add(spaceLabel);
            spaceLabel.setBounds(25, 45, 80, 30);

            //---- descriptionField ----
            descriptionField.setBackground(new Color(0, 21, 36));
            descriptionField.setForeground(new Color(255, 236, 209));
            descriptionField.setLineWrap(true);
            descriptionField.setCaretColor(new Color(221, 199, 160));
            descriptionField.setBorder(new TitledBorder(""));
            panel1.add(descriptionField);
            descriptionField.setBounds(140, 195, 150, 70);

            //---- deleteButton ----
            deleteButton.setIcon(new ImageIcon(getClass().getResource("/inbox/delete.png")));
            deleteButton.setBorderPainted(false);
            deleteButton.setBorder(null);
            deleteButton.addActionListener(this::delete);
            panel1.add(deleteButton);
            deleteButton.setBounds(270, 280, 100, 30);

            //---- ratingsLabel ----
            ratingsLabel.setText("Ratings");
            ratingsLabel.setFont(ratingsLabel.getFont().deriveFont(ratingsLabel.getFont().getSize() + 5f));
            ratingsLabel.setForeground(new Color(255, 236, 209));
            panel1.add(ratingsLabel);
            ratingsLabel.setBounds(25, 315, 70, ratingsLabel.getPreferredSize().height);

            //---- reviewLabel ----
            reviewLabel.setForeground(new Color(171, 212, 52));
            reviewLabel.setFont(reviewLabel.getFont().deriveFont(reviewLabel.getFont().getSize() + 3f));
            panel1.add(reviewLabel);
            reviewLabel.setBounds(115, 315, 130, 25);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 895, 485);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(900, 510);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        priceField.setText(String.valueOf(accommodation.getPrice()));
        spaceField.setText(String.valueOf(accommodation.getSpace()));
        descriptionField.setText(accommodation.getDescription());
        addressField.setText(accommodation.getAddress());
        cityField.setText(accommodation.getCity());

        reviewButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.CUSTOMER);
        reservationButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.CUSTOMER);
        editButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER ||
                Application.getInstance().getCurrentUser().getPrivilege() == Privilege.ADMIN);
        descriptionField.setEditable(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER);
        deleteButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER ||
                Application.getInstance().getCurrentUser().getPrivilege() == Privilege.ADMIN);
        saveEditsButton.setVisible(false);


        imageLabel.setIcon(accommodation.getImage());
        if (imageLabel.getIcon() == null && Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER) {
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
                        accommodation.setImage(imageIcon);
                        DatabaseAPI.brokerAccommodationsDatabase.write();
                    }
                }
            });

        }
        int totalReviews = DatabaseAPI.reviewsDatabase.selectReviewsByAccommodation(accommodation).size();
        float rating = DatabaseAPI.reviewsDatabase.getAverageRatingForAccommodation(accommodation);
        reviewLabel.setText(rating + " (" + totalReviews + ")");

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JLabel imageLabel;
    private JButton reservationButton;
    private JButton reviewButton;
    private JTextField spaceField;
    private JTextField cityField;
    private JTextField priceField;
    private JTextField addressField;
    private JButton saveEditsButton;
    private JButton editButton;
    private JLabel descriptionLabel;
    private JLabel addressLabel;
    private JLabel priceLabel;
    private JLabel cityLabel;
    private JLabel spaceLabel;
    private JTextArea descriptionField;
    private JButton deleteButton;
    private JLabel ratingsLabel;
    private JLabel reviewLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JButton getEditButton() {
        return editButton;
    }

}
