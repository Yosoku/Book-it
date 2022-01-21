package frontend.AccommodationUI;

import backend.accommodations.Reservation;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ReservationTableModel implements TableModel {
    private ArrayList<Reservation> reservations;
    private String[] columns = {"Customer", "Accommodation", "Time Period", "Comments"};

    public ReservationTableModel(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Reservation getReservationAt(int row) {
        return reservations.get(row);
    }

    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= 0 && columnIndex < getColumnCount())
            return getValueAt(0, columnIndex).getClass();
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = reservations.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return reservation.user().getName();
            }
            case 1 -> {
                return reservation.accommodation().getAddress();
            }
            case 2 -> {
                return reservation.period();
            }
            case 3 -> {
                return reservation.comments();
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
