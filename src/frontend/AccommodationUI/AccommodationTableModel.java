package frontend.AccommodationUI;

import backend.accommodations.Accommodation;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AccommodationTableModel implements TableModel {
    private String[] columns = {"ID", "Space", "City", "Address", "Description", "Price"};
    private ArrayList<Accommodation> accommodations;

    public AccommodationTableModel(ArrayList<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationTableModel that = (AccommodationTableModel) o;
        return Arrays.equals(columns, that.columns) && Objects.equals(accommodations, that.accommodations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(accommodations);
        result = 31 * result + Arrays.hashCode(columns);
        return result;
    }

    public Accommodation getAccommodationAt(int row) {
        return accommodations.get(row);
    }

    @Override
    public int getRowCount() {
        return accommodations.size();
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
        Accommodation accommodation;
        try {
            accommodation = accommodations.get(rowIndex);
        } catch (IndexOutOfBoundsException exception) {
            System.err.println(exception.getMessage());
            accommodation = new Accommodation(0, "", "", null, "", 0);
        }
        switch (columnIndex) {
            case 0 -> {
                return accommodation.getID();
            }
            case 1 -> {
                return accommodation.getSpace();
            }
            case 2 -> {
                return accommodation.getCity();
            }
            case 3 -> {
                return accommodation.getAddress();
            }
            case 4 -> {
                return accommodation.getDescription();
            }
            case 5 -> {
                return accommodation.getPrice();
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
