package frontend.usersgui;

import backend.application.DatabaseAPI;
import backend.users.User;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;


public class UserTableModel implements TableModel {

    private ArrayList<User> users;
    private String[] columns = {"Name", "Age", "Email", "Gender", "Phone", "Privilege", "Confirmed"};

    public UserTableModel(ArrayList<User> users) {
        this.users = users;
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
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

    public User getUserAt(int row) {
        return users.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return user.getName();
            }
            case 1 -> {
                return user.getAge();
            }
            case 2 -> {
                return user.getEmail();
            }
            case 3 -> {
                return user.getGender();
            }
            case 4 -> {
                return user.getPhone();
            }
            case 5 -> {
                return user.getPrivilege();
            }
            case 6 -> {
                if (DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(user))
                    return "CONFIRMED";
                else
                    return "UNCONFIRMED";
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
