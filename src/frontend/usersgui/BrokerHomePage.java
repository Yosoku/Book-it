/*
 * Created by JFormDesigner on Thu Dec 30 20:53:58 EET 2021
 */

package frontend.usersgui;

import backend.accommodations.Accommodation;
import backend.accommodations.Reservation;
import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Broker;
import backend.users.Privilege;
import config.Configurations;
import frontend.AccommodationUI.AccommodationTableModel;
import frontend.AccommodationUI.AccommodationView;
import frontend.AccommodationUI.ReservationTableModel;
import frontend.ReservationInfo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
        reservationTable.setModel(new ReservationTableModel(new ArrayList<>(DatabaseAPI.reservationDatabase.selectAllReservationsByBroker(broker))));
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, "RESERVATIONS");

    }

    private void accommodations(ActionEvent e) {
        // TODO add your code here
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, "SEARCH");

    }

    private void filter(ActionEvent e) {
        filterPanel.setVisible(!filterPanel.isVisible());
    }


    private void search(ActionEvent e) {
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                results = new ArrayList<>(DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsBySpaceAndPriceAndCityAndOwner
                        (cityField.getText(), spaceSlider.getValue(), priceSlider.getValue(), broker));
                return null;
            }

            @Override
            protected void done() {
                if (accommodationTable.getModel().equals(new AccommodationTableModel(results)))
                    return;
                accommodationTable.setModel(new AccommodationTableModel(results));
                DefaultTableCellRenderer d = new DefaultTableCellRenderer();
                d.setHorizontalAlignment(SwingConstants.CENTER);
                accommodationTable.getColumn("Price").setCellRenderer(d);
                accommodationTable.getColumn("ID").setCellRenderer(d);
                accommodationTable.getColumn("Space").setCellRenderer(d);
                accommodationTable.setAutoCreateRowSorter(true);
            }
        }.execute();
        cl.show(cardsPanel, "RESULTS");
    }

    private void priceSliderStateChanged(ChangeEvent e) {
        priceLabel.setText(priceSlider.getValue() + " ???");
    }

    private void spaceSliderStateChanged(ChangeEvent e) {
        spaceLabel.setText(spaceSlider.getValue() + " m2");
    }

    private void thisMouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            CardLayout cl = (CardLayout) cardsPanel.getLayout();
            cl.show(cardsPanel, "SEARCH");
        }
    }

    private void add(ActionEvent e) {
        Server.sendRequest("add_new_accommodation");
    }

    private void reservationTableMouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        row = reservationTable.convertRowIndexToModel(row);
        if (table.getSelectedRow() != -1 && row != -1) {
            if (e.getClickCount() == 2) {
                Reservation reservation = ((ReservationTableModel) reservationTable.getModel()).getReservationAt(row);
                ReservationInfo info = new ReservationInfo(reservation);
                info.setVisible(true);
            }
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        reservationButton = new JButton();
        accommodationsButton = new JButton();
        cardsPanel = new JPanel();
        searchPanel = new JPanel();
        filterButton = new JButton();
        cityField = new JFormattedTextField();
        searchButton = new JButton();
        filterPanel = new JPanel();
        priceLabel = new JLabel();
        spaceLabel = new JLabel();
        priceSlider = new JSlider();
        spaceSlider = new JSlider();
        resultsPanel = new JPanel();
        addButton = new JButton();
        scrollPane1 = new JScrollPane();
        accommodationTable = new JTable();
        label1 = new JLabel();
        reservationPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        reservationTable = new JTable();
        confirmationLabel = new JLabel();
        profileButton = new JButton();
        signoutButton = new JButton();
        inboxButton = new JButton();
        helpPanel1 = new HelpPanel();

        //======== this ========
        setFocusable(false);
        setBackground(new Color(0, 21, 36));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
        });
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                swing.border.EmptyBorder(0, 0, 0, 0), "", javax.swing.border
                .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font(""
                , java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder
                ()));
        addPropertyChangeListener(e -> {
            if ("borde\u0072".equals(e.getPropertyName())) throw new RuntimeException
                    ();
        });
        setLayout(null);

        //---- reservationButton ----
        reservationButton.setIcon(new ImageIcon(getClass().getResource("/BrokerHomePage/reservations.png")));
        reservationButton.setBorder(null);
        reservationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reservationButton.addActionListener(this::reservation);
        add(reservationButton);
        reservationButton.setBounds(15, 70, 130, 40);

        //---- accommodationsButton ----
        accommodationsButton.setFont(new Font("Ubuntu", Font.PLAIN, 11));
        accommodationsButton.setIcon(new ImageIcon(getClass().getResource("/BrokerHomePage/accommodations.png")));
        accommodationsButton.setBorderPainted(false);
        accommodationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accommodationsButton.addActionListener(this::accommodations);
        add(accommodationsButton);
        accommodationsButton.setBounds(15, 135, 130, 40);

        //======== cardsPanel ========
        {
            cardsPanel.setLayout(new CardLayout());

            //======== searchPanel ========
            {
                searchPanel.setPreferredSize(new Dimension(600, 200));
                searchPanel.setBackground(new Color(0, 21, 36));
                searchPanel.setLayout(null);

                //---- filterButton ----
                filterButton.setFocusable(false);
                filterButton.setIcon(new ImageIcon(getClass().getResource("/AccommodationSearchPanel/fitlers.png")));
                filterButton.setBorder(null);
                filterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                filterButton.addActionListener(this::filter);
                searchPanel.add(filterButton);
                filterButton.setBounds(45, 40, 100, 30);

                //---- cityField ----
                cityField.setText("Thessaloniki");
                cityField.setForeground(new Color(221, 199, 160));
                cityField.setBackground(new Color(0, 21, 36));
                cityField.setCaretColor(new Color(221, 199, 160));
                cityField.setSelectedTextColor(new Color(247, 248, 247));
                cityField.setFont(cityField.getFont().deriveFont(cityField.getFont().getSize() + 5f));
                searchPanel.add(cityField);
                cityField.setBounds(150, 40, 325, 30);

                //---- searchButton ----
                searchButton.setIcon(new ImageIcon(getClass().getResource("/AccommodationSearchPanel/search.png")));
                searchButton.setBorder(null);
                searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                searchButton.addActionListener(this::search);
                searchPanel.add(searchButton);
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
                    spaceLabel.setBounds(230, 75, 95, 20);

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
                searchPanel.add(filterPanel);
                filterPanel.setBounds(150, 80, 325, 115);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < searchPanel.getComponentCount(); i++) {
                        Rectangle bounds = searchPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = searchPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    searchPanel.setMinimumSize(preferredSize);
                    searchPanel.setPreferredSize(preferredSize);
                }
            }
            cardsPanel.add(searchPanel, "SEARCH");

            //======== resultsPanel ========
            {
                resultsPanel.setBackground(new Color(0, 21, 36));
                resultsPanel.setBorder(new TitledBorder(null, "Accommodations", TitledBorder.RIGHT, TitledBorder.TOP,
                        new Font("Ubuntu", Font.PLAIN, 20), new Color(21, 97, 109)));
                resultsPanel.setMaximumSize(new Dimension(770, 630));
                resultsPanel.setMinimumSize(new Dimension(770, 630));
                resultsPanel.setPreferredSize(new Dimension(770, 630));
                resultsPanel.setLayout(null);

                //---- addButton ----
                addButton.setBorderPainted(false);
                addButton.setBorder(null);
                addButton.setIcon(new ImageIcon(getClass().getResource("/addAccommodation.png")));
                addButton.addActionListener(e -> {
                    add(e);
                    add(e);
                });
                resultsPanel.add(addButton);
                addButton.setBounds(675, 35, 90, 30);

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(null);
                    scrollPane1.setForeground(new Color(21, 97, 109));

                    //---- accommodationTable ----
                    accommodationTable.setBackground(new Color(0, 21, 36));
                    accommodationTable.setFillsViewportHeight(true);
                    accommodationTable.setFont(accommodationTable.getFont().deriveFont(accommodationTable.getFont().getSize() + 3f));
                    accommodationTable.setForeground(new Color(255, 236, 209));
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
                    scrollPane1.setViewportView(accommodationTable);
                }
                resultsPanel.add(scrollPane1);
                scrollPane1.setBounds(45, 85, 705, 545);

                //---- label1 ----
                label1.setText("Right click to return to search screen");
                label1.setBackground(new Color(0, 21, 36));
                label1.setForeground(new Color(221, 199, 160));
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
                resultsPanel.add(label1);
                label1.setBounds(45, 35, 375, 28);
            }
            cardsPanel.add(resultsPanel, "RESULTS");

            //======== reservationPanel ========
            {
                reservationPanel.setLayout(new CardLayout());

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
                    reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    reservationTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            reservationTableMouseClicked(e);
                        }
                    });
                    scrollPane2.setViewportView(reservationTable);
                }
                reservationPanel.add(scrollPane2, "card1");
            }
            cardsPanel.add(reservationPanel, "RESERVATIONS");
        }
        add(cardsPanel);
        cardsPanel.setBounds(245, 75, 805, 685);
        add(confirmationLabel);
        confirmationLabel.setBounds(1100, 10, 30, 30);

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
        profileButton.setBounds(15, 5, 130, 40);

        //---- signoutButton ----
        signoutButton.setFocusable(false);
        signoutButton.setIcon(new ImageIcon(getClass().getResource("/signoutIcon.png")));
        signoutButton.setBorder(null);
        signoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signoutButton.addActionListener(this::signout);
        add(signoutButton);
        signoutButton.setBounds(15, 255, 130, 40);

        //---- inboxButton ----
        inboxButton.setFocusable(false);
        inboxButton.setIcon(new ImageIcon(getClass().getResource("/inboxIcon.png")));
        inboxButton.setBorder(null);
        inboxButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inboxButton.addActionListener(this::inbox);
        add(inboxButton);
        inboxButton.setBounds(15, 195, 130, 40);
        add(helpPanel1);
        helpPanel1.setBounds(1140, 0, 140, 1025);

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

        Dimension size = new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        if (!DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(broker)) {
            reservationButton.setVisible(false);
            accommodationsButton.setVisible(false);
            ImageIcon warningIcon = new ImageIcon("res/GUI/confirmationIcon.png");
            Image image = warningIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(confirmationLabel.getWidth(), confirmationLabel.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            warningIcon = new ImageIcon(newimg);  // transform it back
            confirmationLabel.setIcon(warningIcon);
            confirmationLabel.setToolTipText("Check your inbox for a confirmation email");
        }
        //card layout shit
        reservationButton.setVisible(DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(broker));
        accommodationsButton.setVisible(DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(broker));

        // search
        cityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(() -> cityField.selectAll());
            }
        });
        filterPanel.setVisible(false);
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, "SEARCH");
        //results
        addButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER);
        accommodationTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                row = accommodationTable.convertRowIndexToModel(row);
                if (table.getSelectedRow() != -1 && row != -1) {
                    if (mouseEvent.getClickCount() == 2) {
                        Accommodation accommodation = results.get(row);
                        AccommodationView ac = new AccommodationView(accommodation);
                        ac.setVisible(true);
                    }
                }
            }
        });


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton reservationButton;
    private JButton accommodationsButton;
    private JPanel cardsPanel;
    private JPanel searchPanel;
    private JButton filterButton;
    private JFormattedTextField cityField;
    private JButton searchButton;
    private JPanel filterPanel;
    private JLabel priceLabel;
    private JLabel spaceLabel;
    private JSlider priceSlider;
    private JSlider spaceSlider;
    private JPanel resultsPanel;
    private JButton addButton;
    private JScrollPane scrollPane1;
    private JTable accommodationTable;
    private JLabel label1;
    private JPanel reservationPanel;
    private JScrollPane scrollPane2;
    private JTable reservationTable;
    private JLabel confirmationLabel;
    private JButton profileButton;
    private JButton signoutButton;
    private JButton inboxButton;
    private HelpPanel helpPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private ArrayList<Accommodation> results;


}
