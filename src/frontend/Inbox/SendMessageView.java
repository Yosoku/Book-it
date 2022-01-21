/*
 * Created by JFormDesigner on Thu Dec 30 10:30:04 EET 2021
 */

package frontend.Inbox;

import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.communication.Message;
import backend.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Edward
 */
public class SendMessageView extends JFrame {
    private Message messageToReply;

    public SendMessageView(Message messageToReply) {
        this.messageToReply = messageToReply;
        initComponents();
    }


    private void send(ActionEvent e) {
        if (DatabaseAPI.credentialsUserDatabase.selectUserByUsername(toField.getText()) == null) {
            errorLabel.setVisible(true);
            JOptionPane.showMessageDialog(null, "Cannot send message to invalid username", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            errorLabel.setVisible(false);
            User receiver = DatabaseAPI.credentialsUserDatabase.selectUserByUsername(toField.getText());
            if (receiver == Application.getInstance().getCurrentUser()) // message to self
                JOptionPane.showMessageDialog(null, "Cannot send message to self", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                Message message = new Message(Application.getInstance().getCurrentUser(), receiver, subjectField.getText(), messageArea.getText());
                DatabaseAPI.userMessagesDatabase.insertMessageToUser(receiver, message, true);
                JOptionPane.showMessageDialog(null, "Message sent", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        errorLabel = new JLabel();
        sendButton = new JButton();
        messageArea = new JTextArea();
        subjectField = new JTextField();
        toField = new JTextField();
        subjectLabel = new JLabel();
        toLabel = new JLabel();

        //======== this ========
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Message");
        setBackground(new Color(0, 21, 36));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                    EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER, javax.swing
                    .border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(e -> {
                if ("\u0062order".equals(e.getPropertyName()))
                    throw new RuntimeException();
            });
            panel1.setLayout(null);

            //---- errorLabel ----
            errorLabel.setText("Username not Found !");
            errorLabel.setFont(errorLabel.getFont().deriveFont(Font.BOLD | Font.ITALIC, errorLabel.getFont().getSize() - 1f));
            errorLabel.setVisible(false);
            errorLabel.setForeground(new Color(128, 0, 0));
            panel1.add(errorLabel);
            errorLabel.setBounds(195, 15, 160, errorLabel.getPreferredSize().height);

            //---- sendButton ----
            sendButton.setIcon(new ImageIcon(getClass().getResource("/inbox/send.png")));
            sendButton.setBorderPainted(false);
            sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sendButton.addActionListener(this::send);
            panel1.add(sendButton);
            sendButton.setBounds(260, 230, 100, 30);

            //---- messageArea ----
            messageArea.setBackground(new Color(0, 21, 36));
            messageArea.setBorder(UIManager.getBorder("ComboPopup.border"));
            messageArea.setForeground(new Color(221, 199, 160));
            messageArea.setFont(messageArea.getFont().deriveFont(messageArea.getFont().getSize() + 3f));
            messageArea.setLineWrap(true);
            panel1.add(messageArea);
            messageArea.setBounds(120, 115, 220, 95);

            //---- subjectField ----
            subjectField.setBackground(new Color(0, 21, 36));
            subjectField.setForeground(new Color(221, 199, 160));
            subjectField.setFont(subjectField.getFont().deriveFont(subjectField.getFont().getSize() + 3f));
            panel1.add(subjectField);
            subjectField.setBounds(195, 65, 145, 30);

            //---- toField ----
            toField.setToolTipText("Add the username you wish to message");
            toField.setBackground(new Color(0, 21, 36));
            toField.setForeground(new Color(221, 199, 160));
            toField.setFont(toField.getFont().deriveFont(toField.getFont().getSize() + 3f));
            panel1.add(toField);
            toField.setBounds(195, 30, 145, toField.getPreferredSize().height);

            //---- subjectLabel ----
            subjectLabel.setText("Subject");
            subjectLabel.setForeground(new Color(221, 202, 112));
            subjectLabel.setFont(subjectLabel.getFont().deriveFont(subjectLabel.getFont().getSize() + 5f));
            panel1.add(subjectLabel);
            subjectLabel.setBounds(120, 70, 85, 23);

            //---- toLabel ----
            toLabel.setText("To");
            toLabel.setForeground(new Color(221, 202, 112));
            toLabel.setFont(toLabel.getFont().deriveFont(toLabel.getFont().getSize() + 5f));
            panel1.add(toLabel);
            toLabel.setBounds(120, 35, 65, 23);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 450, 320);

        contentPane.setPreferredSize(new Dimension(450, 350));
        setSize(450, 350);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        if (messageToReply != null) {
            toField.setText(messageToReply.getSender().getCredentials().username());
            toField.setEditable(false);
            subjectField.setText("Re:" + messageToReply.getSubject());
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JLabel errorLabel;
    private JButton sendButton;
    private JTextArea messageArea;
    private JTextField subjectField;
    private JTextField toField;
    private JLabel subjectLabel;
    private JLabel toLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
