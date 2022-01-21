/*
 * Created by JFormDesigner on Wed Jan 19 13:05:07 EET 2022
 */

package frontend.Inbox;

import backend.communication.Message;

import javax.swing.*;
import java.awt.*;

/**
 * @author Edward
 */
public class MessageView extends JFrame {
    private Message message;

    public MessageView(Message message) {
        this.message = message;
        initComponents();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        panel1 = new JPanel();
        messageArea = new JTextArea();
        subjectField = new JTextField();
        fromField = new JTextField();
        subjectLabel = new JLabel();
        fromLabel = new JLabel();

        //======== this ========
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 21, 36));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                    javax.swing.border.EmptyBorder(0, 0, 0, 0), "", javax
                    .swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java
                    .awt.Font("", java.awt.Font.BOLD, 12), java.awt
                    .Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(new java.beans.
                    PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062or\u0064er".
                            equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
            panel1.setLayout(null);

            //---- messageArea ----
            messageArea.setBackground(new Color(0, 21, 36));
            messageArea.setBorder(UIManager.getBorder("ComboPopup.border"));
            messageArea.setForeground(new Color(221, 199, 160));
            messageArea.setFont(messageArea.getFont().deriveFont(messageArea.getFont().getSize() + 3f));
            messageArea.setEditable(false);
            messageArea.setLineWrap(true);
            panel1.add(messageArea);
            messageArea.setBounds(115, 125, 345, 205);

            //---- subjectField ----
            subjectField.setBackground(new Color(0, 21, 36));
            subjectField.setForeground(new Color(221, 199, 160));
            subjectField.setFont(subjectField.getFont().deriveFont(subjectField.getFont().getSize() + 3f));
            subjectField.setEditable(false);
            panel1.add(subjectField);
            subjectField.setBounds(230, 75, 230, 30);

            //---- fromField ----
            fromField.setToolTipText("Add the username you wish to message");
            fromField.setBackground(new Color(0, 21, 36));
            fromField.setForeground(new Color(221, 199, 160));
            fromField.setFont(fromField.getFont().deriveFont(fromField.getFont().getSize() + 3f));
            fromField.setEditable(false);
            panel1.add(fromField);
            fromField.setBounds(230, 40, 230, fromField.getPreferredSize().height);

            //---- subjectLabel ----
            subjectLabel.setText("Subject");
            subjectLabel.setForeground(new Color(221, 202, 112));
            subjectLabel.setFont(subjectLabel.getFont().deriveFont(subjectLabel.getFont().getSize() + 5f));
            panel1.add(subjectLabel);
            subjectLabel.setBounds(120, 80, 105, 23);

            //---- fromLabel ----
            fromLabel.setText("From");
            fromLabel.setForeground(new Color(221, 202, 112));
            fromLabel.setFont(fromLabel.getFont().deriveFont(fromLabel.getFont().getSize() + 5f));
            panel1.add(fromLabel);
            fromLabel.setBounds(120, 45, 65, 23);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 585, 470);

        contentPane.setPreferredSize(new Dimension(585, 500));
        setSize(585, 500);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fromField.setText(message.getSender().getName());
        subjectField.setText(message.getSubject());
        messageArea.setText(message.getContents());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JPanel panel1;
    private JTextArea messageArea;
    private JTextField subjectField;
    private JTextField fromField;
    private JLabel subjectLabel;
    private JLabel fromLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
