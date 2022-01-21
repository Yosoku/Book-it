package frontend.ConnectUI;


import config.Configurations;
import frontend.usersgui.HelpPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ConnectPanel extends JPanel {
    Image background = Toolkit.getDefaultToolkit().createImage("Background.png");

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    public ConnectPanel() {
        initComponents();
    }

    private void signIn(ActionEvent e) {
        signInPanel.setVisible(true);
        signUpPanel.setVisible(false);
    }

    private void signUp(ActionEvent e) {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Edward
        signIn = new JButton();
        signUp = new JButton();
        signInPanel = new SignInPanel();
        signUpPanel = new SignUpPanel();
        helpPanel1 = new HelpPanel();

        //======== this ========
        setPreferredSize(new Dimension(1920, 1080));
        setMinimumSize(new Dimension(1920, 1080));
        setMaximumSize(new Dimension(1920, 1080));
        setBackground(new Color(0, 21, 36));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0, 0, 0, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                .TitledBorder.BOTTOM, new java.awt.Font("", java.awt.Font.BOLD, 12), java.awt
                .Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException()
                    ;
        });
        setLayout(null);

        //---- signIn ----
        signIn.setToolTipText("Already registered?Sign in");
        signIn.setIcon(new ImageIcon(getClass().getResource("/ConnectPanel/login.png")));
        signIn.setBorder(null);
        signIn.addActionListener(this::signIn);
        add(signIn);
        signIn.setBounds(330, 5, 310, 70);

        //---- signUp ----
        signUp.setToolTipText("New User? Sign up!");
        signUp.setIcon(new ImageIcon(getClass().getResource("/ConnectPanel/register.png")));
        signUp.setBorder(null);
        signUp.addActionListener(this::signUp);
        add(signUp);
        signUp.setBounds(655, 5, 310, 70);
        add(signInPanel);
        signInPanel.setBounds(330, 110, 309, 340);
        add(signUpPanel);
        signUpPanel.setBounds(655, 95, 310, signUpPanel.getPreferredSize().height);
        add(helpPanel1);
        helpPanel1.setBounds(1140, 0, 140, 1025);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        //===Panel config
        setPreferredSize(new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT));
        setMinimumSize(new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT));
        setMaximumSize(new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT));
        //label1.setSize(new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT));
        signUpPanel.setVisible(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Edward
    private JButton signIn;
    private JButton signUp;
    private SignInPanel signInPanel;
    private SignUpPanel signUpPanel;
    private HelpPanel helpPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
