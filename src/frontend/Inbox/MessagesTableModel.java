package frontend.Inbox;

import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.communication.Message;
import backend.users.User;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class MessagesTableModel implements TableModel {
    private ArrayList<Message> messages;
    private String[] columns = {"Sender", "Subject", "Message"};

    public MessagesTableModel(ArrayList<Message> messages) {
        this.messages = DatabaseAPI.userMessagesDatabase.selectMessageFromUser(Application.getInstance().getCurrentUser());
        //this.messages = messages;
    }

    public Message getMessageAtRow(int row) {
        if (row < getRowCount() && row >= 0) {
            User sender = DatabaseAPI.credentialsUserDatabase.selectUserByFullname((String) getValueAt(row, 0));
            User receiver = Application.getInstance().getCurrentUser();
            String subject = (String) getValueAt(row, 1);
            String contents = (String) getValueAt(row, 2);
            return DatabaseAPI.userMessagesDatabase.selectMessageFromUserByContents(receiver, contents);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return messages.size();
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

        Message message = messages.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return message.getSender().getName();
            }
            case 1 -> {
                return message.getSubject();
            }
            case 2 -> {
                return message.getContents();
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
