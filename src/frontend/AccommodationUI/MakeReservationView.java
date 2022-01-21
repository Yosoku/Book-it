/*
 * Created by JFormDesigner on Wed Jan 12 21:45:35 EET 2022
 */

package frontend.AccommodationUI;

import backend.accommodations.Accommodation;
import backend.accommodations.Reservation;
import backend.accommodations.TimePeriod;
import backend.application.Application;
import backend.application.DatabaseAPI;
import frontend.CreditCardPayment;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * @author Edward
 */
public class MakeReservationView extends JFrame {


    public MakeReservationView() {
        initComponents();
    }

    public MakeReservationView(Accommodation accommodation) {
        MakeReservationView.accommodation = accommodation;
        initComponents();
    }

    private void checkin(ActionEvent e) {
        System.out.printf("Day %d Month %d Year %d \n", checkinCalendar.currentDay, checkinCalendar.currentMonth, checkinCalendar.currentYear);

    }

    private void checkout(ActionEvent e) {
        System.out.printf("Day %d Month %d Year %d \n", checkoutCalendar.currentDay, checkoutCalendar.currentMonth, checkoutCalendar.currentYear);
    }

    private void makeReservation(ActionEvent e) {
        LocalDate checkin;
        LocalDate checkout;
        try {
            checkin = LocalDate.of(checkinCalendar.currentYear, checkinCalendar.currentMonth + 1, checkinCalendar.currentDay);
            checkout = LocalDate.of(checkoutCalendar.currentYear, checkoutCalendar.currentMonth + 1, checkoutCalendar.currentDay);
            if (checkin.isAfter(checkout))
                throw new DateTimeException("Check in after checkout");
            if (checkin.isBefore(LocalDate.of(checkinCalendar.realYear, checkinCalendar.realMonth + 1, checkinCalendar.realDay)))
                throw new DateTimeException("Feature available only to Time Wizards"); // User trying to access dates in the past
            if (!DatabaseAPI.accommodationsCalendarDatabase.accommodationAvailableInTimePeriod(accommodation, new TimePeriod(checkin, checkout)))
                throw new DateTimeException("Accommodation not available in this period. Unavailability is shown with gray colours");
        } catch (DateTimeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "You will be redirected to a payment page. Check the README.md to get valid credit card numbers", "Redirect", JOptionPane.INFORMATION_MESSAGE);
        reservation = new Reservation(Application.getInstance().getCurrentUser(), accommodation, new TimePeriod(checkin, checkout),
                "Add any comments here".equals(commentsText.getText()) ? "" : commentsText.getText());
        CreditCardPayment payment = new CreditCardPayment();
        payment.setVisible(true);
        dispose();
    }


    private void commentsTextFocusGained(FocusEvent e) {
        commentsText.selectAll();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        checkinButton = new JButton();
        checkoutButton = new JButton();
        makeReservationButton = new JButton();
        commentsText = new JTextArea();
        checkoutCalendar = new AccommodationCalendar(DatabaseAPI.accommodationsCalendarDatabase.selectCalendarFromAccommodation(accommodation));
        checkinCalendar = new AccommodationCalendar(DatabaseAPI.accommodationsCalendarDatabase.selectCalendarFromAccommodation(accommodation));

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new Color(0, 21, 36));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                    .EmptyBorder(0, 0, 0, 0), " ", javax.swing.border.TitledBorder.CENTER, javax
                    .swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD,
                    12), java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("\u0062ord\u0065r".equals(e.
                        getPropertyName())) throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- checkinButton ----
            checkinButton.setBackground(new Color(0, 21, 36));
            checkinButton.setForeground(new Color(221, 199, 160));
            checkinButton.setFont(checkinButton.getFont().deriveFont(checkinButton.getFont().getSize() + 3f));
            checkinButton.setIcon(new ImageIcon(getClass().getResource("/checkin.png")));
            checkinButton.setBorderPainted(false);
            checkinButton.setBorder(null);
            checkinButton.addActionListener(this::checkin);
            panel1.add(checkinButton);
            checkinButton.setBounds(545, 25, 95, 30);

            //---- checkoutButton ----
            checkoutButton.setBackground(new Color(0, 21, 36));
            checkoutButton.setForeground(new Color(221, 199, 160));
            checkoutButton.setFont(checkoutButton.getFont().deriveFont(checkoutButton.getFont().getSize() + 2f));
            checkoutButton.setIcon(new ImageIcon(getClass().getResource("/cheout.png")));
            checkoutButton.setBorder(null);
            checkoutButton.setBorderPainted(false);
            checkoutButton.addActionListener(this::checkout);
            panel1.add(checkoutButton);
            checkoutButton.setBounds(985, 25, 95, 30);

            //---- makeReservationButton ----
            makeReservationButton.setBackground(new Color(0, 21, 36));
            makeReservationButton.setForeground(new Color(221, 199, 160));
            makeReservationButton.setFont(makeReservationButton.getFont().deriveFont(makeReservationButton.getFont().getSize() + 3f));
            makeReservationButton.setBorderPainted(false);
            makeReservationButton.setBorder(null);
            makeReservationButton.setIcon(new ImageIcon(getClass().getResource("/reserve.png")));
            makeReservationButton.addActionListener(this::makeReservation);
            panel1.add(makeReservationButton);
            makeReservationButton.setBounds(995, 500, 90, 30);

            //---- commentsText ----
            commentsText.setText("Add any comments here");
            commentsText.setFont(commentsText.getFont().deriveFont(commentsText.getFont().getSize() + 3f));
            commentsText.setForeground(new Color(255, 236, 209));
            commentsText.setLineWrap(true);
            commentsText.setBackground(new Color(0, 21, 36));
            commentsText.setBorder(new TitledBorder(""));
            commentsText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    commentsTextFocusGained(e);
                }
            });
            panel1.add(commentsText);
            commentsText.setBounds(760, 395, 325, 100);

            //---- checkoutCalendar ----
            checkoutCalendar.setBackground(new Color(0, 21, 36));
            panel1.add(checkoutCalendar);
            checkoutCalendar.setBounds(760, 50, 325, 335);

            //---- checkinCalendar ----
            checkinCalendar.setBackground(new Color(0, 21, 36));
            panel1.add(checkinCalendar);
            checkinCalendar.setBounds(315, 50, 325, 335);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, -5, 1280, 690);

        contentPane.setPreferredSize(new Dimension(1280, 710));
        setSize(1280, 710);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JButton checkinButton;
    private JButton checkoutButton;
    private JButton makeReservationButton;
    private JTextArea commentsText;
    private AccommodationCalendar checkoutCalendar;
    private AccommodationCalendar checkinCalendar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static Reservation reservation = null;
    public static Accommodation accommodation;
}
