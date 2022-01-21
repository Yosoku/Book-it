package frontend.AccommodationUI;

import backend.accommodations.TimePeriod;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.util.TreeSet;

// https://javahungry.blogspot.com/2013/06/calendar-implementation-gui-based.html

public class AccommodationCalendar extends JPanel {

    private JLabel monthLabel;
    private JButton previousButton, nextButton;
    private JTable tableCalendar;
    private JComboBox<String> yearComboBox;
    private Container pane;
    private DefaultTableModel mtableCalendar; //Table model
    private JScrollPane scrollpaneCalendar; //The scrollpane
    public int realYear, realMonth, realDay, currentYear, currentMonth, currentDay = -1;
    private TreeSet<TimePeriod> timePeriods;

    public AccommodationCalendar(TreeSet<TimePeriod> timePeriods) {
        System.out.println("Arguement constructor called");
        this.timePeriods = timePeriods;
        for (TimePeriod period : timePeriods)
            System.out.println(period);
        System.out.println("Finished printing periods");
        initComponents();
    }

    public AccommodationCalendar() {
        System.out.println("Empty constructor call");
        this.timePeriods = new TreeSet<>();
        initComponents();

    }


    public void initComponents() {
        //Create controls
        monthLabel = new JLabel("January");
        yearComboBox = new JComboBox<>();
        previousButton = new JButton("<");
        nextButton = new JButton(">");
        mtableCalendar = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tableCalendar = new JTable(mtableCalendar);
        scrollpaneCalendar = new JScrollPane(tableCalendar);
        setLayout(null);
        add(monthLabel);
        add(yearComboBox);
        add(previousButton);
        add(nextButton);
        add(scrollpaneCalendar);
        setBorder(BorderFactory.createTitledBorder("Calendar"));

        //Register action listeners
        previousButton.addActionListener(this::previousButton_Action);
        nextButton.addActionListener(this::nextButton_Action);
        yearComboBox.addActionListener(this::yearComboBox_Action);
        //Set bounds
        setBounds(0, 0, 320, 335);
        monthLabel.setBounds(160 - monthLabel.getPreferredSize().width / 2, 25, 100, 25);
        yearComboBox.setBounds(230, 305, 80, 20);
        previousButton.setBounds(10, 25, 50, 25);
        nextButton.setBounds(260, 25, 50, 25);
        scrollpaneCalendar.setBounds(10, 50, 300, 250);

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;


        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (String day : headers) {
            mtableCalendar.addColumn(day);
        }

        tableCalendar.getParent().setBackground(tableCalendar.getBackground()); //Set background

        //No resize/reorder
        tableCalendar.getTableHeader().setResizingAllowed(false);
        tableCalendar.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        tableCalendar.setColumnSelectionAllowed(true);
        tableCalendar.setRowSelectionAllowed(true);
        tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        tableCalendar.setRowHeight(38);
        mtableCalendar.setColumnCount(7);
        mtableCalendar.setRowCount(6);


        //Populate table
        for (int i = realYear; i <= realYear + 2; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }
        //Refresh calendar
        refreshCalendar(realMonth, realYear); //Refresh calendar

    }

    public void refreshCalendar(int month, int year) {
        //Variables
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        //Allow/disallow buttons
        previousButton.setEnabled(true);
        nextButton.setEnabled(true);
        if (month == 0 && year <= realYear - 10) {
            previousButton.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear + 100) {
            nextButton.setEnabled(false);
        } //Too late
        monthLabel.setText(months[month]); //Refresh the month label (at the top)
        monthLabel.setBounds(160 - monthLabel.getPreferredSize().width / 2, 25, 180, 25); //Re-align label with calendar
        yearComboBox.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mtableCalendar.setValueAt(null, i, j);
            }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar
        for (int i = 1; i <= nod; i++) {
            int row = (i + som - 2) / 7;
            int column = (i + som - 2) % 7;
            mtableCalendar.setValueAt(i, row, column);
        }

        //Apply renderers
        tableCalendar.setDefaultRenderer(tableCalendar.getColumnClass(0), new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                currentDay = -1;
                if (column == 0 || column == 6) { //Week-end
                    setBackground(new Color(255, 255, 255));
                } else { //Week
                    setBackground(new Color(255, 255, 255));
                }

                if (value != null) {
                    if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) { //Today
                        setBackground(new Color(221, 199, 160));
                    }
                    for (TimePeriod period : timePeriods) {
                        for (int i = period.start().getDayOfMonth(); i <= period.end().getDayOfMonth(); ++i)
                            if (Integer.parseInt(value.toString()) == i && currentMonth == period.start().getMonth().getValue() - 1 && currentYear == period.start().getYear()) {
                                setBackground(new Color(205, 205, 205));
                                setFocusable(false);
                            }
                    }
                    if (selected) {
                        setBackground(new Color(21, 97, 109));
                        currentDay = Integer.parseInt(value.toString());
                    }
                }

                setBorder(null);
                setForeground(Color.black);
                return this;
            }
        });
    }

    public void previousButton_Action(ActionEvent e) {
        if (currentMonth == 0) { //Back one year
            currentMonth = 11;
            currentYear -= 1;
        } else { //Back one month
            currentMonth -= 1;
        }
        System.out.println("Refreshed");
        refreshCalendar(currentMonth, currentYear);
    }

    public void nextButton_Action(ActionEvent e) {
        if (currentMonth == 11) { //Foward one year
            currentMonth = 0;
            currentYear += 1;
        } else { //Foward one month
            currentMonth += 1;
        }
        refreshCalendar(currentMonth, currentYear);

    }

    public void yearComboBox_Action(ActionEvent e) {
        if (yearComboBox.getSelectedItem() != null) {
            String b = yearComboBox.getSelectedItem().toString();
            currentYear = Integer.parseInt(b);
            refreshCalendar(currentMonth, currentYear);
        }
    }


}