/*
 * Created by JFormDesigner on Fri Jan 07 15:35:25 EET 2022
 */

package frontend.usersgui;

import backend.application.Server;
import backend.users.Customer;
import frontend.AccommodationSearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class CustomerHomePage extends JPanel {
    private Customer customer;
    public CustomerHomePage(Customer customer) {
        this.customer = customer;
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
        // TODO add your code here
        Server.sendRequest("signout");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        signoutButton = new JButton();
        inboxButton = new JButton();
        profileButton = new JButton();
        accommodationSearchPanel1 = new AccommodationSearchPanel();

        //======== this ========
        setFocusable(false);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing
                .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
        ), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062ord\u0065r".equals(e.getPropertyName(
                ))) throw new RuntimeException();
            }
        });
        setLayout(null);

        //---- signoutButton ----
        signoutButton.setText("Sign out");
        signoutButton.setFocusable(false);
        signoutButton.addActionListener(e -> signout(e));
        add(signoutButton);
        signoutButton.setBounds(10, 90, 100, 30);

        //---- inboxButton ----
        inboxButton.setText("Inbox");
        inboxButton.setFocusable(false);
        inboxButton.addActionListener(e -> inbox(e));
        add(inboxButton);
        inboxButton.setBounds(10, 50, 100, 30);

        //---- profileButton ----
        profileButton.setText("Profile");
        profileButton.setFocusable(false);
        profileButton.addActionListener(e -> profile(e));
        add(profileButton);
        profileButton.setBounds(10, 10, 100, 30);
        add(accommodationSearchPanel1);
        accommodationSearchPanel1.setBounds(260, 85, accommodationSearchPanel1.getPreferredSize().width, 245);

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
    private JButton signoutButton;
    private JButton inboxButton;
    private JButton profileButton;
    private AccommodationSearchPanel accommodationSearchPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
