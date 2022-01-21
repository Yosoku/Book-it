/*
 * Created by JFormDesigner on Wed Dec 29 10:13:22 EET 2021
 */

package frontend.Inbox;

import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.communication.Message;
import config.Configurations;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;


/**
 * @author Edward
 */
public class InboxView extends JFrame {
    ArrayList<Message> messages;
    private static InboxView instance;

    public static InboxView getInstance() {
        if (instance == null)
            instance = new InboxView();
        return instance;
    }

    private InboxView() {
        this.messages = DatabaseAPI.userMessagesDatabase.selectMessageFromUser(Application.getInstance().getCurrentUser());
        for (Message m : messages)
            System.out.println(m);
        initComponents();
    }


    private void reply(ActionEvent e) {
        int row = inboxTable.getSelectedRow();
        row = inboxTable.convertRowIndexToModel(row);
        if (row == -1)
            JOptionPane.showMessageDialog(null, "No messages selected", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Message message = messages.get(row);
            SendMessageView temp = new SendMessageView(message); //Message view with reply to message
            temp.setVisible(true);
        }
    }

    private void sendMessage(ActionEvent e) {
        SendMessageView sendMessageView = new SendMessageView(null);
        sendMessageView.setVisible(true);
    }

    private void deleteMessage(ActionEvent e) {
        int row = inboxTable.getSelectedRow();
        row = inboxTable.convertRowIndexToModel(row);
        if (row == -1)
            JOptionPane.showMessageDialog(null, "No messages selected", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            DatabaseAPI.userMessagesDatabase.dropMessageFromUser(Application.getInstance().getCurrentUser(), messages.get(row));
            inboxTable.setModel(new MessagesTableModel(messages));
            refreshCards();
        }

    }

    private void panel1MouseClicked(MouseEvent e) {
        inboxTable.getSelectionModel().clearSelection();
        replyButton.setVisible(false);
        deleteMessageButton.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        sendMessageButton = new JButton();
        cardPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        inboxTable = new JTable();
        noMessagesLabel = new JLabel();
        replyButton = new JButton();
        deleteMessageButton = new JButton();

        //======== this ========
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Inbox");
        setMinimumSize(new Dimension(860, 530));
        setBackground(new Color(0, 21, 36));
        setIconImage(null);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel1MouseClicked(e);
                }
            });
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                    (0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                    .TitledBorder.BOTTOM, new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12), java.awt
                    .Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("\u0062or\u0064er".equals(e.getPropertyName())) throw new RuntimeException()
                        ;
            });
            panel1.setLayout(null);

            //---- sendMessageButton ----
            sendMessageButton.setBackground(new Color(0, 21, 36));
            sendMessageButton.setIcon(new ImageIcon(getClass().getResource("/inbox/newMessage.png")));
            sendMessageButton.setBorderPainted(false);
            sendMessageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sendMessageButton.addActionListener(this::sendMessage);
            panel1.add(sendMessageButton);
            sendMessageButton.setBounds(45, 20, 145, 40);

            //======== cardPanel ========
            {
                cardPanel.setBackground(new Color(0, 21, 36));
                cardPanel.setLayout(new CardLayout());

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(new EtchedBorder());
                    scrollPane1.setBackground(new Color(0, 21, 36));

                    //---- inboxTable ----
                    inboxTable.setBackground(new Color(0, 21, 36));
                    inboxTable.setGridColor(new Color(0, 21, 33));
                    inboxTable.setFillsViewportHeight(true);
                    inboxTable.setForeground(new Color(221, 199, 160));
                    inboxTable.setRowMargin(0);
                    inboxTable.setRowHeight(20);
                    inboxTable.setShowHorizontalLines(false);
                    inboxTable.setShowVerticalLines(false);
                    inboxTable.setIntercellSpacing(new Dimension(0, 0));
                    inboxTable.setSelectionBackground(new Color(21, 97, 109));
                    inboxTable.setSelectionForeground(new Color(221, 199, 160));
                    inboxTable.setBorder(null);
                    inboxTable.setFont(inboxTable.getFont().deriveFont(inboxTable.getFont().getSize() + 3f));
                    inboxTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    inboxTable.setUpdateSelectionOnSort(false);
                    inboxTable.setFocusable(false);
                    scrollPane1.setViewportView(inboxTable);
                }
                cardPanel.add(scrollPane1, "INBOX");

                //---- noMessagesLabel ----
                noMessagesLabel.setText("Your inbox is empty");
                noMessagesLabel.setForeground(new Color(221, 199, 160));
                noMessagesLabel.setBackground(new Color(0, 21, 36));
                noMessagesLabel.setHorizontalTextPosition(SwingConstants.LEFT);
                noMessagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                noMessagesLabel.setFont(noMessagesLabel.getFont().deriveFont(noMessagesLabel.getFont().getSize() + 10f));
                noMessagesLabel.setVerticalAlignment(SwingConstants.TOP);
                cardPanel.add(noMessagesLabel, "NO_MESSAGES_LABEL");
            }
            panel1.add(cardPanel);
            cardPanel.setBounds(new Rectangle(new Point(355, 15), cardPanel.getPreferredSize()));

            //---- replyButton ----
            replyButton.setBorderPainted(false);
            replyButton.setIcon(new ImageIcon(getClass().getResource("/inbox/reply.png")));
            replyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            replyButton.addActionListener(this::reply);
            panel1.add(replyButton);
            replyButton.setBounds(595, 450, 100, 30);

            //---- deleteMessageButton ----
            deleteMessageButton.setBorderPainted(false);
            deleteMessageButton.setIcon(new ImageIcon(getClass().getResource("/inbox/delete.png")));
            deleteMessageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            deleteMessageButton.addActionListener(this::deleteMessage);
            panel1.add(deleteMessageButton);
            deleteMessageButton.setBounds(710, 450, 100, 30);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 860, 505);

        contentPane.setPreferredSize(new Dimension(860, 530));
        setSize(860, 530);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        deleteMessageButton.setVisible(false);
        replyButton.setVisible(false);


        inboxTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (table.getSelectedRow() != -1 && row != -1) {
                    if (mouseEvent.getClickCount() == 2) {
                        Message message = messages.get(row);
                        DatabaseAPI.userMessagesDatabase.updateMessageSeen(Application.getInstance().getCurrentUser(), message);
                        MessageView messageView = new MessageView(message);
                        messageView.setVisible(true);
                    }
                    if (mouseEvent.getClickCount() == 1) {
                        Message message = messages.get(row);
                        DatabaseAPI.userMessagesDatabase.updateMessageSeen(Application.getInstance().getCurrentUser(), message);
                        if (Objects.equals(messages.get(row).getSubject(), Configurations.CONFIRMATION_MESSAGE_SUBJECT) && Objects.equals(messages.get(row).getContents(), Configurations.CONFIRMATION_MESSAGE))
                            Server.sendRequest("confirm");
                        deleteMessageButton.setVisible(true);
                        replyButton.setVisible(true);
                    }
                }
            }
        });


        UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        refreshCards();
    }

    private void refreshCards() {
        if (messages.isEmpty()) {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "NO_MESSAGES_LABEL");
        } else {
            Color beige = new Color(221, 199, 160);
            Color darkBlue = new Color(0, 21, 36);
            inboxTable.getTableHeader().setBackground(darkBlue);
            inboxTable.getTableHeader().setForeground(beige);
            inboxTable.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 20));
            DefaultTableCellRenderer d = new DefaultTableCellRenderer();
            inboxTable.setDefaultRenderer(inboxTable.getModel().getColumnClass(0), new MessageTableRenderer());
            inboxTable.setModel(new MessagesTableModel(messages));
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "INBOX");
        }
    }

    public static void closeView() {
        InboxView.getInstance().dispose();
        InboxView.instance = null;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JButton sendMessageButton;
    private JPanel cardPanel;
    private JScrollPane scrollPane1;
    private JTable inboxTable;
    private JLabel noMessagesLabel;
    private JButton replyButton;
    private JButton deleteMessageButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
