/*
 * Created by JFormDesigner on Thu Dec 30 20:40:02 EET 2021
 */

package frontend.usersgui;

import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Admin;
import backend.users.User;
import frontend.AccommodationUI.AccommodationTableModel;
import frontend.AccommodationUI.AccommodationTablePanel;
import frontend.AccommodationUI.ReservationTableModel;
import frontend.AccommodationUI.ReservationTablePanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    private void viewUsers(ActionEvent e) {
        userTablePanel.getTable().setModel(new UserTableModel(DatabaseAPI.userConfirmationsDatabase.selectAllUsers()));
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, users_panel_name);
        ACTIVE = users_panel_name;
    }

    private void viewAccommodations(ActionEvent e) {
        accommodationTable.setModel(new AccommodationTableModel(new ArrayList<>(DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations())));
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, accommodations_panel_name);
        ACTIVE = accommodations_panel_name;
    }

    private void viewReservations(ActionEvent e) {
        reservationTable.setModel(new ReservationTableModel(DatabaseAPI.reservationDatabase.selectAllReservations()));
        CardLayout cl = (CardLayout) tablePanel.getLayout();
        cl.show(tablePanel, reservations_panel_name);
        ACTIVE = reservations_panel_name;
    }


    private void thisMouseClicked(MouseEvent e) {
        reservationTablePanel.getTable().clearSelection();
        userTablePanel.getTable().clearSelection();
        accommodationTablePanel.getTable().clearSelection();
        deleteButton.setVisible(false);
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
        }
    }


    private void add(ActionEvent e) {
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        viewUsersButton = new JButton();
        viewAccommodationsButton = new JButton();
        tablePanel = new JPanel();
        reservationPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        reservationTable = new JTable();
        userPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        userTable = new JTable();
        accommodationPanel = new JPanel();
        addButton = new JButton();
        scrollPane3 = new JScrollPane();
        accommodationTable = new JTable();
        viewReservationsButton = new JButton();
        deleteButton = new JButton();
        profileButton = new JButton();
        inboxButton = new JButton();
        signoutButton = new JButton();

        //======== this ========
        setFocusable(false);
        setBackground(new Color(0, 21, 36));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax
                .swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD,
                12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("borde\u0072".equals(e.
                    getPropertyName())) throw new RuntimeException();
        });
        setLayout(null);

        //---- viewUsersButton ----
        viewUsersButton.setIcon(new ImageIcon(getClass().getResource("/AdminHomePage/users.png")));
        viewUsersButton.setBorderPainted(false);
        viewUsersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewUsersButton.addActionListener(this::viewUsers);
        add(viewUsersButton);
        viewUsersButton.setBounds(695, 20, 95, 30);

        //---- viewAccommodationsButton ----
        viewAccommodationsButton.setIcon(new ImageIcon(getClass().getResource("/BrokerHomePage/accommodations.png")));
        viewAccommodationsButton.setBorderPainted(false);
        viewAccommodationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewAccommodationsButton.addActionListener(this::viewAccommodations);
        add(viewAccommodationsButton);
        viewAccommodationsButton.setBounds(805, 25, 130, 30);

        //======== tablePanel ========
        {
            tablePanel.setMinimumSize(new Dimension(500, 500));
            tablePanel.setMaximumSize(new Dimension(500, 500));
            tablePanel.setBackground(new Color(0, 21, 36));
            tablePanel.setLayout(new CardLayout());

            //======== reservationPanel ========
            {
                reservationPanel.setLayout(null);

                //======== scrollPane2 ========
                {

                    //---- reservationTable ----
                    reservationTable.setBackground(new Color(0, 21, 36));
                    reservationTable.setFillsViewportHeight(true);
                    reservationTable.setForeground(new Color(221, 199, 160));
                    reservationTable.setFont(reservationTable.getFont().deriveFont(reservationTable.getFont().getSize() + 3f));
                    reservationTable.setShowHorizontalLines(false);
                    reservationTable.setShowVerticalLines(false);
                    reservationTable.setFocusable(false);
                    reservationTable.setGridColor(new Color(0, 21, 36));
                    reservationTable.setRowHeight(20);
                    reservationTable.setSelectionBackground(new Color(21, 97, 109));
                    reservationTable.setUpdateSelectionOnSort(false);
                    scrollPane2.setViewportView(reservationTable);
                }
                reservationPanel.add(scrollPane2);
                scrollPane2.setBounds(0, 0, 770, 630);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < reservationPanel.getComponentCount(); i++) {
                        Rectangle bounds = reservationPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = reservationPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    reservationPanel.setMinimumSize(preferredSize);
                    reservationPanel.setPreferredSize(preferredSize);
                }
            }
            tablePanel.add(reservationPanel, "RESERVATIONS");

            //======== userPanel ========
            {
                userPanel.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- userTable ----
                    userTable.setBackground(new Color(0, 21, 36));
                    userTable.setFillsViewportHeight(true);
                    userTable.setForeground(new Color(221, 199, 160));
                    userTable.setFont(userTable.getFont().deriveFont(userTable.getFont().getSize() + 3f));
                    userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    userTable.setShowVerticalLines(false);
                    userTable.setShowHorizontalLines(false);
                    userTable.setFocusable(false);
                    userTable.setRowHeight(20);
                    userTable.setSelectionBackground(new Color(21, 97, 109));
                    userTable.setUpdateSelectionOnSort(false);
                    scrollPane1.setViewportView(userTable);
                }
                userPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 770, 630);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < userPanel.getComponentCount(); i++) {
                        Rectangle bounds = userPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = userPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    userPanel.setMinimumSize(preferredSize);
                    userPanel.setPreferredSize(preferredSize);
                }
            }
            tablePanel.add(userPanel, "card2");

            //======== accommodationPanel ========
            {
                accommodationPanel.setBackground(new Color(0, 21, 36));
                accommodationPanel.setBorder(new TitledBorder(null, "Accommodations", TitledBorder.RIGHT, TitledBorder.TOP,
                        new Font("Ubuntu", Font.PLAIN, 20), new Color(21, 97, 109)));
                accommodationPanel.setMaximumSize(new Dimension(770, 630));
                accommodationPanel.setMinimumSize(new Dimension(770, 630));
                accommodationPanel.setPreferredSize(new Dimension(770, 630));
                accommodationPanel.setLayout(null);

                //---- addButton ----
                addButton.setBorderPainted(false);
                addButton.setBorder(null);
                addButton.setIcon(new ImageIcon(getClass().getResource("/addAccommodation.png")));
                addButton.addActionListener(e -> {
                    add(e);
                    add(e);
                });
                accommodationPanel.add(addButton);
                addButton.setBounds(680, 35, 90, 30);

                //======== scrollPane3 ========
                {
                    scrollPane3.setBackground(new Color(0, 21, 36));

                    //---- accommodationTable ----
                    accommodationTable.setBackground(new Color(0, 21, 36));
                    accommodationTable.setFillsViewportHeight(true);
                    accommodationTable.setFont(accommodationTable.getFont().deriveFont(accommodationTable.getFont().getSize() + 3f));
                    accommodationTable.setForeground(new Color(221, 199, 160));
                    accommodationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    accommodationTable.setShowHorizontalLines(false);
                    accommodationTable.setShowVerticalLines(false);
                    accommodationTable.setFocusable(false);
                    accommodationTable.setGridColor(new Color(0, 21, 36));
                    accommodationTable.setRowMargin(0);
                    accommodationTable.setRowHeight(20);
                    accommodationTable.setSelectionBackground(new Color(21, 97, 109));
                    accommodationTable.setSelectionForeground(new Color(221, 199, 160));
                    accommodationTable.setUpdateSelectionOnSort(false);
                    scrollPane3.setViewportView(accommodationTable);
                }
                accommodationPanel.add(scrollPane3);
                scrollPane3.setBounds(45, 85, 705, 545);
            }
            tablePanel.add(accommodationPanel, "card3");
        }
        add(tablePanel);
        tablePanel.setBounds(265, 65, 770, 630);

        //---- viewReservationsButton ----
        viewReservationsButton.setIcon(new ImageIcon(getClass().getResource("/BrokerHomePage/reservations.png")));
        viewReservationsButton.setBorderPainted(false);
        viewReservationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewReservationsButton.addActionListener(this::viewReservations);
        add(viewReservationsButton);
        viewReservationsButton.setBounds(545, 20, 130, 30);

        //---- deleteButton ----
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/inbox/delete.png")));
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this::delete);
        add(deleteButton);
        deleteButton.setBounds(940, 715, 95, 30);

        //---- profileButton ----
        profileButton.setFocusable(false);
        profileButton.setIcon(new ImageIcon(getClass().getResource("/profileIcon.png")));
        profileButton.setSelectedIcon(new ImageIcon(getClass().getResource("/profileIcon.png")));
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);
        profileButton.setBorder(null);
        profileButton.setBorderPainted(false);
        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileButton.addActionListener(this::profile);
        add(profileButton);
        profileButton.setBounds(10, 10, 130, 40);

        //---- inboxButton ----
        inboxButton.setFocusable(false);
        inboxButton.setIcon(new ImageIcon(getClass().getResource("/inboxIcon.png")));
        inboxButton.setBorder(null);
        inboxButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inboxButton.addActionListener(this::inbox);
        add(inboxButton);
        inboxButton.setBounds(10, 65, 130, 40);

        //---- signoutButton ----
        signoutButton.setFocusable(false);
        signoutButton.setIcon(new ImageIcon(getClass().getResource("/signoutIcon.png")));
        signoutButton.setBorder(null);
        signoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signoutButton.addActionListener(this::signout);
        add(signoutButton);
        signoutButton.setBounds(10, 125, 130, 40);

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
        reservationTablePanel =
                new ReservationTablePanel(DatabaseAPI.reservationDatabase.selectAllReservations());
        accommodationTablePanel =
                new AccommodationTablePanel(DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations());
        userTablePanel = new UserTablePanel(DatabaseAPI.userConfirmationsDatabase.selectAllUsers());


        tablePanel.add(reservationTablePanel, reservations_panel_name);
        tablePanel.add(accommodationTablePanel, accommodations_panel_name);
        tablePanel.add(userTablePanel, users_panel_name);

    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton viewUsersButton;
    private JButton viewAccommodationsButton;
    private JPanel tablePanel;
    private JPanel reservationPanel;
    private JScrollPane scrollPane2;
    private JTable reservationTable;
    private JPanel userPanel;
    private JScrollPane scrollPane1;
    private JTable userTable;
    private JPanel accommodationPanel;
    private JButton addButton;
    private JScrollPane scrollPane3;
    private JTable accommodationTable;
    private JButton viewReservationsButton;
    private JButton deleteButton;
    private JButton profileButton;
    private JButton inboxButton;
    private JButton signoutButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private String users_panel_name = "USER_PANEL";
    private String accommodations_panel_name = "ACCOMMODATION_PANEL";
    private String reservations_panel_name = "RESERVATION_PANEL";
    private String ACTIVE;
    private UserTablePanel userTablePanel;
    private AccommodationTablePanel accommodationTablePanel;
    private ReservationTablePanel reservationTablePanel;
}
