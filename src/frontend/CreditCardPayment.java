/*
 * Created by JFormDesigner on Thu Jan 13 19:20:24 EET 2022
 */

package frontend;

import backend.application.Server;
import config.Configurations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class CreditCardPayment extends JFrame {
    private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private String[] years = {"22", "23", "24", "25", "26", "27", "28", "29", "30"};

    public CreditCardPayment() {
        initComponents();
    }

    private void pay(ActionEvent e) {
        String cardNumber = cardNumberField.getText().replaceAll("[!@#$%^&*()]", "");
        String error = "";
        if (!cardNumber.matches(Configurations.CREDIT_CARD_REGEX))
            error += "Card Number invalid\n";
        if (!nameField.getText().matches(Configurations.NAME_REGEX))
            error += "Name invalid\n";
        if (!CVVfield.getText().matches(Configurations.CVV_REGEX))
            error += "CVV Invalid\n";

        if (error.length() == 0) {

            JOptionPane.showMessageDialog
                    (null, "Payment made successfully,the window will now close", "Payment successful", JOptionPane.INFORMATION_MESSAGE);
            Server.sendRequest("make_reservation");
            dispose();
        } else
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        nameField = new JTextField();
        label3 = new JLabel();
        cardNumberField = new JTextField();
        label4 = new JLabel();
        CVVfield = new JTextField();
        label5 = new JLabel();
        monthBox = new JComboBox(months);
        yearBox = new JComboBox(years);
        payButton = new JButton();

        //======== this ========
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
                if ("bord\u0065r".equals(e.getPropertyName()))
                    throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Payment details");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setForeground(new Color(255, 236, 209));
            panel1.add(label1);
            label1.setBounds(195, 25, 445, 65);

            //---- label2 ----
            label2.setText("Name on Card");
            label2.setForeground(new Color(255, 236, 209));
            panel1.add(label2);
            label2.setBounds(195, 105, 110, 25);
            panel1.add(nameField);
            nameField.setBounds(195, 128, 445, 30);

            //---- label3 ----
            label3.setText("Card Number");
            label3.setForeground(new Color(255, 236, 209));
            panel1.add(label3);
            label3.setBounds(195, 185, 110, 25);
            panel1.add(cardNumberField);
            cardNumberField.setBounds(195, 209, 445, 30);

            //---- label4 ----
            label4.setText("CVV Number");
            label4.setForeground(new Color(255, 236, 209));
            panel1.add(label4);
            label4.setBounds(195, 260, 110, 25);
            panel1.add(CVVfield);
            CVVfield.setBounds(195, 290, 85, 30);

            //---- label5 ----
            label5.setText("Expires");
            label5.setForeground(new Color(255, 236, 209));
            panel1.add(label5);
            label5.setBounds(370, 255, 75, 30);

            //---- monthBox ----
            monthBox.setFont(monthBox.getFont().deriveFont(monthBox.getFont().getSize() - 3f));
            monthBox.setForeground(new Color(255, 236, 209));
            monthBox.setBackground(new Color(21, 97, 109));
            panel1.add(monthBox);
            monthBox.setBounds(370, 285, 40, 35);

            //---- yearBox ----
            yearBox.setFont(yearBox.getFont().deriveFont(yearBox.getFont().getSize() - 3f));
            yearBox.setForeground(new Color(255, 236, 209));
            yearBox.setBackground(new Color(21, 97, 109));
            panel1.add(yearBox);
            yearBox.setBounds(410, 285, 40, 35);

            //---- payButton ----
            payButton.setText("Pay");
            payButton.setForeground(Color.black);
            payButton.setFont(payButton.getFont().deriveFont(payButton.getFont().getSize() + 5f));
            payButton.addActionListener(this::pay);
            panel1.add(payButton);
            payButton.setBounds(new Rectangle(new Point(560, 370), payButton.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 805, 475);

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
        setSize(805, 505);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        monthBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JTextField nameField;
    private JLabel label3;
    private JTextField cardNumberField;
    private JLabel label4;
    private JTextField CVVfield;
    private JLabel label5;
    private JComboBox monthBox;
    private JComboBox yearBox;
    private JButton payButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
