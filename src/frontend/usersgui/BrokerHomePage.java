/*
 * Created by JFormDesigner on Thu Dec 30 20:53:58 EET 2021
 */

package frontend.usersgui;

import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Broker;
import frontend.AccommodationSearchPanel;
import frontend.AccommodationUI.ReservationTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class BrokerHomePage extends JPanel {
    private Broker broker;
    public BrokerHomePage(Broker broker) {
        this.broker = broker;
        initComponents();
    }

    private void profile(ActionEvent e) {
        // TODO add your code here
        Server.sendRequest("profile");
    }

    private void inbox(ActionEvent e) {
        // TODO add your code here
        Server.sendRequest("inbox");
    }

    private void signout(ActionEvent e) {
        Server.sendRequest("signout");
    }


    private void reservation(ActionEvent e) {
        // TODO add your code here
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, reservation_panel_name);
        System.out.println(reservation_panel_name);
    }

    private void accommodations(ActionEvent e) {
        // TODO add your code here
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, accommodation_panel_name);
        System.out.println(accommodation_panel_name);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        signoutButton = new JButton();
        inboxButton = new JButton();
        profileButton = new JButton();
        reservationButton = new JButton();
        accommodationsButton = new JButton();
        cardsPanel = new JPanel();

        //======== this ========
        setFocusable(false);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
                .border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder
                .CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.
                awt.Font.BOLD, 12), java.awt.Color.red), getBorder()))
        ;
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e
            ) {
                if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException();
            }
        })
        ;
        setLayout(null);

        //---- signoutButton ----
        signoutButton.setText("Sign out");
        signoutButton.setFocusable(false);
        signoutButton.addActionListener(e -> signout(e));
        add(signoutButton);
        signoutButton.setBounds(10, 165, 100, 30);

        //---- inboxButton ----
        inboxButton.setText("Inbox");
        inboxButton.setFocusable(false);
        inboxButton.addActionListener(e -> inbox(e));
        add(inboxButton);
        inboxButton.setBounds(10, 125, 100, 30);

        //---- profileButton ----
        profileButton.setText("Profile");
        profileButton.setFocusable(false);
        profileButton.addActionListener(e -> profile(e));
        add(profileButton);
        profileButton.setBounds(10, 10, 100, 30);

        //---- reservationButton ----
        reservationButton.setText("Reservations");
        reservationButton.addActionListener(e -> reservation(e));
        add(reservationButton);
        reservationButton.setBounds(10, 45, 100, reservationButton.getPreferredSize().height);

        //---- accommodationsButton ----
        accommodationsButton.setText("Accommodations");
        accommodationsButton.setFont(new Font("Ubuntu", Font.PLAIN, 11));
        accommodationsButton.addActionListener(e -> accommodations(e));
        add(accommodationsButton);
        accommodationsButton.setBounds(10, 85, 100, accommodationsButton.getPreferredSize().height);

        //======== cardsPanel ========
        {
            cardsPanel.setLayout(new CardLayout());
        }
        add(cardsPanel);
        cardsPanel.setBounds(260, 75, 660, 430);

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

        //card layout shit
        cardsPanel.add(new AccommodationSearchPanel(), accommodation_panel_name);
        cardsPanel.add(new ReservationTablePanel(DatabaseAPI.reservationDatabase.selectAllReservations()), reservation_panel_name);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton signoutButton;
    private JButton inboxButton;
    private JButton profileButton;
    private JButton reservationButton;
    private JButton accommodationsButton;
    private JPanel cardsPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private static String reservation_panel_name = "RESERVATION_PANEL";
    private static String accommodation_panel_name = "ACCOMMODATION_PANEL";
}
