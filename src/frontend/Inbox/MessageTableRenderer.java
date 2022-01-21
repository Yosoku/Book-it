package frontend.Inbox;

import backend.communication.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MessageTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumn("Sender").setCellRenderer(this);
        table.getColumn("Subject").setCellRenderer(this);
        table.getColumn("Message").setCellRenderer(this);
        Message message = ((MessagesTableModel) (table.getModel())).getMessageAtRow(row);
        if (!message.isSeen()) {
            this.setFont(new Font("Ubuntu", Font.BOLD, 17));
            //System.out.println("Message was not seen + " + message);
        } else {
            this.setFont(new Font("Ubuntu", Font.PLAIN, 17));
            // System.out.println("Message was seen + " + message);
        }
        return this;
    }
}

