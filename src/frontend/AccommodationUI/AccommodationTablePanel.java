/*
 * Created by JFormDesigner on Sun Dec 26 22:30:27 EET 2021
 */

package frontend.AccommodationUI;

import backend.accommodations.Accommodation;
import backend.application.Application;
import backend.application.Server;
import backend.users.Privilege;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Edward
 */
public class AccommodationTablePanel extends JPanel {
    ArrayList<Accommodation> accommodations;

    public AccommodationTablePanel() {
        this.accommodations = new ArrayList<>();
        initComponents();
    }

    public AccommodationTablePanel(HashSet<Accommodation> accommodations) {
        this.accommodations = new ArrayList<>(accommodations);
        initComponents();
    }

    public JTable getTable() {
        return accommodationTable;
    }


    private void add(ActionEvent e) {
        Server.sendRequest("add_new_accommodation");
    }

    private void accommodationTableMouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        row = accommodationTable.convertRowIndexToModel(row);
        if (table.getSelectedRow() != -1 && row != -1) {
            if (e.getClickCount() == 2) {
                Accommodation accommodation = ((AccommodationTableModel) accommodationTable.
                        getModel()).getAccommodationAt(row);
                AccommodationView ac = new AccommodationView(accommodation);
                ac.setVisible(true);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        addButton = new JButton();
        scrollPane1 = new JScrollPane();
        accommodationTable = new JTable();

        //======== this ========
        setBackground(new Color(0, 21, 36));
        setBorder(new TitledBorder(null, "Accommodations", TitledBorder.RIGHT, TitledBorder.TOP,
                new Font("Ubuntu", Font.PLAIN, 20), new Color(21, 97, 109)));
        setMaximumSize(new Dimension(770, 630));
        setMinimumSize(new Dimension(770, 630));
        setPreferredSize(new Dimension(770, 630));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
                .border.EmptyBorder(0, 0, 0, 0), "", javax.swing.border.TitledBorder
                .CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("", java.
                awt.Font.BOLD, 12), java.awt.Color.red), getBorder()))
        ;
        addPropertyChangeListener(e -> {
            if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
        })
        ;
        setLayout(null);

        //---- addButton ----
        addButton.setBorderPainted(false);
        addButton.setBorder(null);
        addButton.setIcon(new ImageIcon(getClass().getResource("/addAccommodation.png")));
        addButton.addActionListener(e -> {
            add(e);
            add(e);
        });
        add(addButton);
        addButton.setBounds(680, 35, 90, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setBackground(new Color(0, 21, 36));

            //---- accommodationTable ----
            accommodationTable.setBackground(new Color(0, 21, 36));
            accommodationTable.setFillsViewportHeight(true);
            accommodationTable.setFont(accommodationTable.getFont().deriveFont(accommodationTable.getFont().getSize() + 3f));
            accommodationTable.setForeground(new Color(255, 236, 209));
            accommodationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            accommodationTable.setShowHorizontalLines(false);
            accommodationTable.setShowVerticalLines(false);
            accommodationTable.setFocusable(false);
            accommodationTable.setGridColor(new Color(0, 21, 36));
            accommodationTable.setRowMargin(0);
            accommodationTable.setRowHeight(20);
            accommodationTable.setSelectionBackground(new Color(21, 97, 109));
            accommodationTable.setSelectionForeground(new Color(221, 199, 160));
            accommodationTable.setUpdateSelectionOnSort(false);
            accommodationTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    accommodationTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(accommodationTable);
        }
        add(scrollPane1);
        scrollPane1.setBounds(45, 85, 705, 545);

        setPreferredSize(new Dimension(775, 630));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        if (Application.getInstance().getCurrentUser() != null)
            addButton.setVisible(Application.getInstance().getCurrentUser().getPrivilege() == Privilege.BROKER
                    || Application.getInstance().getCurrentUser().getPrivilege() == Privilege.ADMIN);

        TableModel tableModel = new AccommodationTableModel(new ArrayList<>(accommodations));
        accommodationTable.setModel(tableModel);
        accommodationTable.setAutoCreateRowSorter(true);
        DefaultTableCellRenderer d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(SwingConstants.CENTER);
        accommodationTable.getColumn("Price").setCellRenderer(d);
        accommodationTable.getColumn("ID").setCellRenderer(d);
        accommodationTable.getColumn("Space").setCellRenderer(d);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton addButton;
    private JScrollPane scrollPane1;
    private JTable accommodationTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
