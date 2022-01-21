/*
 * Created by JFormDesigner on Thu Dec 30 22:09:38 EET 2021
 */

package frontend.AccommodationUI;

import backend.accommodations.Reservation;
import frontend.ReservationInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Edward
 */
public class ReservationTablePanel extends JPanel {
    ArrayList<Reservation> reservations;

    public JTable getTable() {
        return reservationTable;
    }

    public ReservationTablePanel() {
        this.reservations = new ArrayList<>();
        initComponents();
    }

    public ReservationTablePanel(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
        initComponents();
    }

    private void reservationTableMouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        row = reservationTable.convertRowIndexToModel(row);
        if (table.getSelectedRow() != -1 && row != -1) {
            if (e.getClickCount() == 2) {
                Reservation selectedReservation = ((ReservationTableModel) reservationTable.getModel()).getReservationAt(row);
                ReservationInfo reservationInfo = new ReservationInfo(selectedReservation);
                reservationInfo.setVisible(true);
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        scrollPane1 = new JScrollPane();
        reservationTable = new JTable();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion"
                , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                , new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12)
                , java.awt.Color.red), getBorder()));
        addPropertyChangeListener(
                e -> {
                    if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException()
                            ;
                });
        setLayout(new CardLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setBackground(new Color(0, 21, 36));

            //---- reservationTable ----
            reservationTable.setFillsViewportHeight(true);
            reservationTable.setBackground(new Color(0, 21, 36));
            reservationTable.setFont(reservationTable.getFont().deriveFont(reservationTable.getFont().getSize() + 3f));
            reservationTable.setForeground(new Color(255, 236, 209));
            reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            reservationTable.setShowHorizontalLines(false);
            reservationTable.setShowVerticalLines(false);
            reservationTable.setFocusable(false);
            reservationTable.setGridColor(new Color(0, 21, 36));
            reservationTable.setIntercellSpacing(new Dimension(1, 0));
            reservationTable.setRowHeight(20);
            reservationTable.setSelectionBackground(new Color(21, 97, 109));
            reservationTable.setSelectionForeground(new Color(221, 199, 160));
            reservationTable.setUpdateSelectionOnSort(false);
            reservationTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    reservationTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(reservationTable);
        }
        add(scrollPane1, "card1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        reservationTable.setModel(new ReservationTableModel(reservations));
        reservationTable.getSelectionModel().addListSelectionListener(event -> {
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JScrollPane scrollPane1;
    private JTable reservationTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
