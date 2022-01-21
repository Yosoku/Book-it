/*
 * Created by JFormDesigner on Fri Dec 24 08:12:48 EET 2021
 */

package frontend.AccommodationUI;

import backend.accommodations.Accommodation;
import backend.application.DatabaseAPI;
import backend.application.Server;
import frontend.AppWindow;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;

/**
 * @author Edward
 */
public class AccommodationSearchPanel extends JPanel {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton filterButton;
    private JFormattedTextField cityField;
    private JButton searchButton;
    private JPanel filterPanel;
    private JLabel priceLabel;
    private JLabel spaceLabel;
    private JSlider priceSlider;
    private JSlider spaceSlider;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private JTable table;


    public AccommodationSearchPanel() {
        initComponents();
    }


    //===================================Button events============================================
    private void filter(ActionEvent e) {
        filterPanel.setVisible(!filterPanel.isVisible());
    }


    private void search(ActionEvent e) {
        Server.sendRequest("search");
        SwingUtilities.invokeLater(() -> {
            HashSet<Accommodation> accommodations = DatabaseAPI.brokerAccommodationsDatabase.
                    selectAccommodationsBySpaceAndPriceAndCity(cityField.getText(), spaceSlider.getValue(), priceSlider.getValue());
            AppWindow.getInstance().changePanel(new AccommodationTablePanel(accommodations));
        });


    }


    private void space(ActionEvent e) {
    }

    private void priceSliderStateChanged(ChangeEvent e) {
        priceLabel.setText(priceSlider.getValue() + " €");
    }

    private void spaceSliderStateChanged(ChangeEvent e) {
        spaceLabel.setText(spaceSlider.getValue() + " m2");
    }

    private void cityFieldCaretUpdate(CaretEvent e) {
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        filterButton = new JButton();
        cityField = new JFormattedTextField();
        searchButton = new JButton();
        filterPanel = new JPanel();
        priceLabel = new JLabel();
        spaceLabel = new JLabel();
        priceSlider = new JSlider();
        spaceSlider = new JSlider();

        //======== this ========
        setPreferredSize(new Dimension(600, 200));
        setBackground(new Color(0, 21, 36));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing
                .border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("bord\u0065r".equals(e.getPropertyName()))
                throw new RuntimeException();
        });
        setLayout(null);

        //---- filterButton ----
        filterButton.setFocusable(false);
        filterButton.setIcon(new ImageIcon(getClass().getResource("/AccommodationSearchPanel/fitlers.png")));
        filterButton.setBorder(null);
        filterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filterButton.addActionListener(this::filter);
        add(filterButton);
        filterButton.setBounds(45, 40, 100, 30);

        //---- cityField ----
        cityField.setText("Thessaloniki");
        cityField.setForeground(new Color(221, 199, 160));
        cityField.setBackground(new Color(0, 21, 36));
        cityField.setCaretColor(new Color(221, 199, 160));
        cityField.setSelectedTextColor(new Color(247, 248, 247));
        cityField.setFont(cityField.getFont().deriveFont(cityField.getFont().getSize() + 5f));
        cityField.addCaretListener(this::cityFieldCaretUpdate);
        add(cityField);
        cityField.setBounds(150, 40, 325, 30);

        //---- searchButton ----
        searchButton.setIcon(new ImageIcon(getClass().getResource("/AccommodationSearchPanel/search.png")));
        searchButton.setBorder(null);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(this::search);
        add(searchButton);
        searchButton.setBounds(495, 40, 100, 30);

        //======== filterPanel ========
        {
            filterPanel.setBackground(new Color(0, 21, 36));
            filterPanel.setLayout(null);

            //---- priceLabel ----
            priceLabel.setText("\u20ac");
            priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
            priceLabel.setFocusable(false);
            priceLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            priceLabel.setForeground(new Color(221, 199, 160));
            priceLabel.setFont(priceLabel.getFont().deriveFont(priceLabel.getFont().getSize() + 5f));
            filterPanel.add(priceLabel);
            priceLabel.setBounds(230, 25, 85, 25);

            //---- spaceLabel ----
            spaceLabel.setText("m2");
            spaceLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            spaceLabel.setFont(spaceLabel.getFont().deriveFont(spaceLabel.getFont().getSize() + 5f));
            spaceLabel.setForeground(new Color(221, 199, 160));
            filterPanel.add(spaceLabel);
            spaceLabel.setBounds(230, 75, 80, 20);

            //---- priceSlider ----
            priceSlider.setMaximum(1000);
            priceSlider.setValue(1000);
            priceSlider.setMajorTickSpacing(100);
            priceSlider.setMinorTickSpacing(50);
            priceSlider.setPaintTicks(true);
            priceSlider.setToolTipText("Max Price value to search");
            priceSlider.setOpaque(false);
            priceSlider.setBackground(new Color(221, 199, 160));
            priceSlider.setForeground(new Color(221, 199, 160));
            priceSlider.addChangeListener(this::priceSliderStateChanged);
            filterPanel.add(priceSlider);
            priceSlider.setBounds(30, 25, 195, priceSlider.getPreferredSize().height);

            //---- spaceSlider ----
            spaceSlider.setMaximum(1000);
            spaceSlider.setValue(1000);
            spaceSlider.setMajorTickSpacing(100);
            spaceSlider.setMinorTickSpacing(50);
            spaceSlider.setPaintTicks(true);
            spaceSlider.setToolTipText("Max square meters to search");
            spaceSlider.setOpaque(false);
            spaceSlider.setBackground(new Color(221, 199, 160));
            spaceSlider.setForeground(new Color(221, 199, 160));
            spaceSlider.addChangeListener(this::spaceSliderStateChanged);
            filterPanel.add(spaceSlider);
            spaceSlider.setBounds(30, 75, 195, 24);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < filterPanel.getComponentCount(); i++) {
                    Rectangle bounds = filterPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = filterPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                filterPanel.setMinimumSize(preferredSize);
                filterPanel.setPreferredSize(preferredSize);
            }
        }
        add(filterPanel);
        filterPanel.setBounds(150, 80, 325, 115);

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
        filterButton.requestFocusInWindow();
        cityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(() -> cityField.selectAll());
            }
        });

        filterPanel.setVisible(false);
    }


}
