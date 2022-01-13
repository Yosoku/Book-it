/*
 * Created by JFormDesigner on Thu Dec 30 20:40:02 EET 2021
 */

package GUI.usersgui;

import GUI.AccommodationTablePanel;
import GUI.AccommodationUI.ReservationTablePanel;
import GUI.UserTableModel;
import GUI.UserTablePanel;
import application.DatabaseAPI;
import application.Server;
import communication.Message;
import config.Configurations;
import users.Admin;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Edward
 */
public class AdminHomePage extends JPanel {
    private Admin admin;

    public AdminHomePage(Admin admin) {
        this.admin = admin;
        initComponents();
    }

    private void profile(ActionEvent e) {
        Server.sendRequest("profile");
    }

    private void inbox(ActionEvent e) {
        Server.sendRequest("inbox");
    }

    private void signout(ActionEvent e) {
        Server.sendRequest("signout");
    }

    private void confirmAll(ActionEvent e) {
        Server.sendRequest("confirmall");
        userTablePanel.getTable().setModel(new UserTableModel((ArrayList<User>)
                DatabaseAPI.userConfirmationsDatabase.selectAllUsersWhereConfirmedIs(false)));
    }

    private void viewUsers(ActionEvent e) {
        userTablePanel.getTable().setModel(new UserTableModel(DatabaseAPI.userConfirmationsDatabase.selectAllUsers()));
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, users_panel_name);
    }

    private void viewAccommodations(ActionEvent e) {
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, accommodations_panel_name);
    }

    private void viewReservations(ActionEvent e) {
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, reservations_panel_name);
    }

    private void confirm(ActionEvent e) {
        int row = userTablePanel.getTable().getSelectedRow();
        if (row == -1)
            return;
        User selectedUser = ((UserTableModel) userTablePanel.getTable().getModel()).getUserAt(row);
        DatabaseAPI.userMessagesDatabase.insertMessageToUser
                (selectedUser, new Message(admin, selectedUser, Configurations.CONFIRMATION_MESSAGE_SUBJECT,
                        Configurations.CONFIRMATION_MESSAGE), true);
        JOptionPane.showMessageDialog(null, "The confirmation message was sent successfully",
                "Confirmation sent", JOptionPane.INFORMATION_MESSAGE);
    }

    private void thisMouseClicked(MouseEvent e) {
        reservationTablePanel.getTable().clearSelection();
        userTablePanel.getTable().clearSelection();
        accommodationTablePanel.getTable().clearSelection();
        deleteButton.setVisible(false);
        confirmButton.setVisible(false);
    }

    private void delete(ActionEvent e) {
        int row = userTablePanel.getTable().getSelectedRow();
        if (row == -1) return;
        int response = JOptionPane.showConfirmDialog(null, "This will permanently delete this user,continue?", "Are you sure", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {

            User selectedUser = ((UserTableModel) userTablePanel.getTable().getModel()).getUserAt(row);
            DatabaseAPI.userConfirmationsDatabase.dropUserConfirmation(selectedUser);
            DatabaseAPI.userMessagesDatabase.dropAllMessagesFromUser(selectedUser);
            DatabaseAPI.credentialsUserDatabase.dropUser(selectedUser);
            ((UserTableModel) userTablePanel.getTable().getModel()).removeUser(selectedUser);
            userTablePanel.getTable().clearSelection();
            confirmButton.setVisible(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        signoutButton = new JButton();
        inboxButton = new JButton();
        profileButton = new JButton();
        confirmationsButton = new JButton();
        viewUsersButton = new JButton();
        viewAccommodationsButton = new JButton();
        tablePanel = new JPanel();
        viewReservationsButton = new JButton();
        deleteButton = new JButton();
        confirmButton = new JButton();

        //======== this ========
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder.CENTER, javax.swing
                .border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("bord\u0065r".equals(e.getPropertyName()))
                throw new RuntimeException();
        });
        setLayout(null);

        //---- signoutButton ----
        signoutButton.setText("Sign out");
        signoutButton.setFocusable(false);
        signoutButton.addActionListener(this::signout);
        add(signoutButton);
        signoutButton.setBounds(10, 130, 100, 30);

        //---- inboxButton ----
        inboxButton.setText("Inbox");
        inboxButton.setFocusable(false);
        inboxButton.addActionListener(this::inbox);
        add(inboxButton);
        inboxButton.setBounds(10, 90, 100, 30);

        //---- profileButton ----
        profileButton.setText("Profile");
        profileButton.setFocusable(false);
        profileButton.addActionListener(this::profile);
        add(profileButton);
        profileButton.setBounds(10, 10, 100, 30);

        //---- confirmationsButton ----
        confirmationsButton.setText("Confirm all");
        confirmationsButton.setFont(new Font("Ubuntu", Font.PLAIN, 11));
        confirmationsButton.addActionListener(this::confirmAll);
        add(confirmationsButton);
        confirmationsButton.setBounds(10, 50, 100, confirmationsButton.getPreferredSize().height);

        //---- viewUsersButton ----
        viewUsersButton.setText("View Users");
        viewUsersButton.addActionListener(this::viewUsers);
        add(viewUsersButton);
        viewUsersButton.setBounds(555, 20, 125, 30);

        //---- viewAccommodationsButton ----
        viewAccommodationsButton.setText("View Accommodations");
        viewAccommodationsButton.addActionListener(this::viewAccommodations);
        add(viewAccommodationsButton);
        viewAccommodationsButton.setBounds(695, 20, 175, 30);

        //======== tablePanel ========
        {
            tablePanel.setMinimumSize(new Dimension(500, 500));
            tablePanel.setMaximumSize(new Dimension(500, 500));
            tablePanel.setLayout(new CardLayout());
        }
        add(tablePanel);
        tablePanel.setBounds(265, 65, 670, 405);

        //---- viewReservationsButton ----
        viewReservationsButton.setText("View Reservations");
        viewReservationsButton.addActionListener(this::viewReservations);
        add(viewReservationsButton);
        viewReservationsButton.setBounds(385, 20, viewReservationsButton.getPreferredSize().width, 30);

        //---- deleteButton ----
        deleteButton.setText("Delete");
        deleteButton.addActionListener(this::delete);
        add(deleteButton);
        deleteButton.setBounds(850, 475, 95, 30);

        //---- confirmButton ----
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(this::confirm);
        add(confirmButton);
        confirmButton.setBounds(750, 475, 95, 30);

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

        //card layout
        deleteButton.setVisible(false);
        confirmButton.setVisible(false);
        reservationTablePanel =
                new ReservationTablePanel(DatabaseAPI.reservationDatabase.selectAllReservations());
        accommodationTablePanel =
                new AccommodationTablePanel(DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations());
        userTablePanel = new UserTablePanel(DatabaseAPI.userConfirmationsDatabase.selectAllUsers());
        userTablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            int row = userTablePanel.getTable().getSelectedRow();
            deleteButton.setVisible(row != -1);
            User selectedUser = row != -1 ? ((UserTableModel) userTablePanel.getTable().getModel()).getUserAt(row) : null;
            confirmButton.setVisible(!DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(selectedUser));
        });
        tablePanel.add(reservationTablePanel, reservations_panel_name);
        tablePanel.add(accommodationTablePanel, accommodations_panel_name);
        tablePanel.add(userTablePanel, users_panel_name);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton signoutButton;
    private JButton inboxButton;
    private JButton profileButton;
    private JButton confirmationsButton;
    private JButton viewUsersButton;
    private JButton viewAccommodationsButton;
    private JPanel tablePanel;
    private JButton viewReservationsButton;
    private JButton deleteButton;
    private JButton confirmButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private String users_panel_name = "USER_PANEL";
    private String accommodations_panel_name = "ACCOMMODATION_PANEL";
    private String reservations_panel_name = "RESERVATION_PANEL";
    private UserTablePanel userTablePanel;
    private AccommodationTablePanel accommodationTablePanel;
    private ReservationTablePanel reservationTablePanel;
}
