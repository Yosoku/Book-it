/*
 * Created by JFormDesigner on Thu Dec 30 23:13:25 EET 2021
 */

package frontend.usersgui;

import backend.application.DatabaseAPI;
import backend.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Edward
 */
public class UserTablePanel extends JPanel {
    public UserTablePanel(ArrayList<User> users) {
        initComponents();
    }


    public JTable getTable() {
        return userTable;
    }

    private void userTableMouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        row = userTable.convertRowIndexToModel(row);
        if (table.getSelectedRow() != -1 && row != -1) {
            if (e.getClickCount() == 2) {
                deleteButton.setVisible(true);
                User selectedUser = ((UserTableModel) userTable.getModel()).getUserAt(row);
                ProfileView.getInstance(selectedUser).setVisible(true);
            }
            if (e.getClickCount() == 1)
                deleteButton.setVisible(true);
        } else
            deleteButton.setVisible(false);
    }


    private void delete(ActionEvent e) {
        int row = userTable.getSelectedRow();
        if (row == -1) return;
        int response = JOptionPane.showConfirmDialog(null, "This will permanently delete this user,continue?", "Are you sure", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {

            User selectedUser = ((UserTableModel) userTable.getModel()).getUserAt(row);
            DatabaseAPI.userConfirmationsDatabase.dropUserConfirmation(selectedUser);
            DatabaseAPI.userMessagesDatabase.dropAllMessagesFromUser(selectedUser);
            DatabaseAPI.credentialsUserDatabase.dropUser(selectedUser);
            ((UserTableModel) userTable.getModel()).removeUser(selectedUser);
            userTable.clearSelection();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        scrollPane1 = new JScrollPane();
        userTable = new JTable();
        deleteButton = new JButton();

        //======== this ========
        setBackground(new Color(0, 21, 36));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing
                .border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("\u0062order".equals(e.getPropertyName()))
                throw new RuntimeException();
        });
        setLayout(null);

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
            userTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    userTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(userTable);
        }
        add(scrollPane1);
        scrollPane1.setBounds(0, 40, 820, 490);

        //---- deleteButton ----
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/inbox/delete.png")));
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(this::delete);
        add(deleteButton);
        deleteButton.setBounds(710, 5, 100, 31);

        setPreferredSize(new Dimension(820, 530));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        userTable.setModel(new UserTableModel(DatabaseAPI.userConfirmationsDatabase.selectAllUsers()));

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JScrollPane scrollPane1;
    private JTable userTable;
    private JButton deleteButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
