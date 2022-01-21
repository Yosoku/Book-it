/*
 * Created by JFormDesigner on Thu Jan 20 19:26:14 EET 2022
 */

package frontend;

import backend.accommodations.Reservation;
import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.communication.Message;
import backend.users.Broker;
import backend.users.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class ReservationInfo extends JFrame {
    private Reservation reservation;

    public ReservationInfo(Reservation reservation) {
        this.reservation = reservation;
        initComponents();
    }

    public ReservationInfo() {
        initComponents();
    }

    private void cancelReservation(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null, "You're about to cancel the reservation.Sure?", "Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {
            User currentUser = Application.getInstance().getCurrentUser();
            String subject = "Reservation cancel";
            String contents;
            Message message;
            switch (currentUser.getPrivilege()) {
                case ADMIN -> {
                    //send message to User who booked it and Broker who owned it
                    contents = "The reservation you made on accommodation " + reservation.accommodation().getAddress() + "for the period of" + reservation.period().toString() +
                            " is canceled";
                    message = new Message(currentUser, reservation.user(), subject, contents);
                    DatabaseAPI.userMessagesDatabase.insertMessageToUser(reservation.user(), message, true);
                    contents = "The reservation on your accommodation " + reservation.accommodation().getAddress() + "for the period of" + reservation.period().toString() +
                            " is canceled";
                    Broker owner = DatabaseAPI.brokerAccommodationsDatabase.selectBrokerFromAccommodation(reservation.accommodation());
                    message = new Message(currentUser, owner, subject, contents);
                    DatabaseAPI.userMessagesDatabase.insertMessageToUser(owner, message, true);
                }
                case BROKER -> {
                    //send message to User who booked it
                    contents = "The reservation you made on accommodation " + reservation.accommodation().getAddress() + "for the period of" + reservation.period().toString() +
                            " is canceled";
                    message = new Message(currentUser, reservation.user(), subject, contents);
                    DatabaseAPI.userMessagesDatabase.insertMessageToUser(reservation.user(), message, true);
                }
                case CUSTOMER -> {
                    //send message to Broker who owns it only
                    contents = "The reservation on your accommodation " + reservation.accommodation().getAddress() + "for the period of" + reservation.period().toString() +
                            " is canceled";
                    Broker owner = DatabaseAPI.brokerAccommodationsDatabase.selectBrokerFromAccommodation(reservation.accommodation());
                    message = new Message(currentUser, owner, subject, contents);
                    DatabaseAPI.userMessagesDatabase.insertMessageToUser(owner, message, true);
                }
            }
            DatabaseAPI.accommodationsCalendarDatabase.dropTimePeriodFromAccommodation(reservation.accommodation(), reservation.period());
            DatabaseAPI.reservationDatabase.dropReservation(reservation);
            JOptionPane.showMessageDialog(null,
                    "Reservation was canceled,everyone involved received a message", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        usernameLabel = new JLabel();
        accommodationLabel = new JLabel();
        periodLabel = new JLabel();
        cancelButton = new JButton();

        //======== this ========
        setResizable(false);
        setTitle("Reservation");
        setBackground(new Color(0, 21, 33));
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                    .EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax
                    .swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialo\u0067", java.awt.Font.BOLD,
                    12), java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("borde\u0072".equals(e.
                        getPropertyName())) throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Username");
            label1.setForeground(new Color(255, 236, 209));
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
            panel1.add(label1);
            label1.setBounds(45, 15, 175, 25);

            //---- label2 ----
            label2.setText("Accommodation");
            label2.setForeground(new Color(255, 236, 209));
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
            panel1.add(label2);
            label2.setBounds(45, 40, 175, 25);

            //---- label3 ----
            label3.setText("Period");
            label3.setForeground(new Color(255, 236, 209));
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
            panel1.add(label3);
            label3.setBounds(45, 70, 175, 25);

            //======== scrollPane1 ========
            {
                scrollPane1.setBorder(null);

                //---- textArea1 ----
                textArea1.setBackground(new Color(0, 21, 36));
                textArea1.setLineWrap(true);
                textArea1.setBorder(new TitledBorder("Comments"));
                textArea1.setForeground(new Color(255, 236, 209));
                textArea1.setFont(textArea1.getFont().deriveFont(textArea1.getFont().getSize() + 2f));
                scrollPane1.setViewportView(textArea1);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(225, 100, 145, 90);

            //---- usernameLabel ----
            usernameLabel.setForeground(new Color(255, 236, 209));
            usernameLabel.setFont(usernameLabel.getFont().deriveFont(usernameLabel.getFont().getSize() + 2f));
            panel1.add(usernameLabel);
            usernameLabel.setBounds(225, 10, 145, 25);

            //---- accommodationLabel ----
            accommodationLabel.setForeground(new Color(255, 236, 209));
            accommodationLabel.setFont(accommodationLabel.getFont().deriveFont(accommodationLabel.getFont().getSize() + 2f));
            panel1.add(accommodationLabel);
            accommodationLabel.setBounds(225, 40, 145, 25);

            //---- periodLabel ----
            periodLabel.setForeground(new Color(255, 236, 209));
            periodLabel.setFont(periodLabel.getFont().deriveFont(periodLabel.getFont().getSize() + 2f));
            panel1.add(periodLabel);
            periodLabel.setBounds(225, 70, 370, 25);

            //---- cancelButton ----
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/cancel.png")));
            cancelButton.setBorderPainted(false);
            cancelButton.setBorder(null);
            cancelButton.addActionListener(this::cancelReservation);
            panel1.add(cancelButton);
            cancelButton.setBounds(200, 240, 105, 30);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 600, 320);

        contentPane.setPreferredSize(new Dimension(600, 350));
        setSize(600, 350);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        usernameLabel.setText(reservation.user().getName());
        accommodationLabel.setText(reservation.accommodation().getAddress());
        periodLabel.setText(reservation.period().toString());
        textArea1.setText(reservation.comments());

        //show cancel button to customer who made reservation,broker who owns accommodation or an admin
//        cancelButton.setVisible(false);
//        User currentUser = Application.getInstance().getCurrentUser();
//        switch (currentUser.getPrivilege())
//        {
//            case BROKER -> {
//                for(Accommodation ac:DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodationsFromBroker((Broker) currentUser)){
//                    if(ac==reservation.accommodation())
//                        cancelButton.setVisible(true);
//                }
//            }
//            case CUSTOMER -> {
//                if(currentUser==reservation.user())
//                    cancelButton.setVisible(true);
//            }
//            case ADMIN -> cancelButton.setVisible(true);
//        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel usernameLabel;
    private JLabel accommodationLabel;
    private JLabel periodLabel;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
