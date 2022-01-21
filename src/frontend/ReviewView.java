/*
 * Created by JFormDesigner on Tue Dec 28 11:04:47 EET 2021
 */

package frontend;

import backend.accommodations.Accommodation;
import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.communication.Review;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class ReviewView extends JFrame {
    private Accommodation accommodation;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JTextArea description;
    private JLabel label2;
    private JSlider starSlider;
    private JLabel label1;
    private JButton submitButton;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public ReviewView(Accommodation accommodation) {
        this.accommodation = accommodation;
        initComponents();
    }

    private void submit(ActionEvent e) {
        Review review =
                new Review(starSlider.getValue(), description.getText(), Application.getInstance().getCurrentUser(), accommodation);
        if (DatabaseAPI.reviewsDatabase.isUniqueReview(accommodation, Application.getInstance().getCurrentUser())) {
            DatabaseAPI.reviewsDatabase.insertReview(review);
            JOptionPane.showMessageDialog(null, "Review added successfully", "Review successful", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            //duplicate
            JOptionPane.showMessageDialog(null, "You have already made a review in this accommodation",
                    "Review duplicate error", JOptionPane.ERROR_MESSAGE, null);
        }
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        description = new JTextArea();
        label2 = new JLabel();
        starSlider = new JSlider();
        label1 = new JLabel();
        submitButton = new JButton();

        //======== this ========
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                    border.EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER
                    , javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font
                    .BOLD, 12), java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(
                    e -> {
                        if ("bord\u0065r"
                                .equals(e.getPropertyName())) throw new RuntimeException();
                    });
            panel1.setLayout(null);

            //---- description ----
            description.setBackground(new Color(0, 21, 36));
            description.setForeground(new Color(255, 236, 209));
            description.setLineWrap(true);
            description.setCaretColor(new Color(221, 199, 160));
            description.setBorder(new TitledBorder(""));
            description.setFont(description.getFont().deriveFont(description.getFont().getSize() + 3f));
            panel1.add(description);
            description.setBounds(300, 145, 190, 85);

            //---- label2 ----
            label2.setText("Description");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
            label2.setForeground(new Color(221, 199, 160));
            panel1.add(label2);
            label2.setBounds(300, 110, 140, 28);

            //---- starSlider ----
            starSlider.setMaximum(5);
            starSlider.setMinimum(1);
            starSlider.setMinorTickSpacing(1);
            starSlider.setSnapToTicks(true);
            starSlider.setValue(1);
            starSlider.setBackground(new Color(0, 21, 36));
            starSlider.setForeground(new Color(221, 199, 160));
            starSlider.setPaintTicks(true);
            starSlider.setPaintLabels(true);
            starSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel1.add(starSlider);
            starSlider.setBounds(300, 70, 200, 35);

            //---- label1 ----
            label1.setText("Stars");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
            label1.setForeground(new Color(221, 199, 160));
            panel1.add(label1);
            label1.setBounds(300, 45, 50, 25);

            //---- submitButton ----
            submitButton.setBorderPainted(false);
            submitButton.setBorder(null);
            submitButton.setIcon(new ImageIcon(getClass().getResource("/submitReview.png")));
            submitButton.addActionListener(this::submit);
            panel1.add(submitButton);
            submitButton.setBounds(410, 260, 90, 30);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 810, 405);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
