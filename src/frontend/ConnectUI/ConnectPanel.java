package frontend.ConnectUI;


import config.Configurations;

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
        signInPanel = new SignInPanel();
        signIn = new JButton();
        signUpPanel = new SignUpPanel();
        signUp = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1920, 1080));
        setMinimumSize(new Dimension(1920, 1080));
        setMaximumSize(new Dimension(1920, 1080));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder.CENTER, javax.swing
                .border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));
        addPropertyChangeListener(e -> {
            if ("bord\u0065r".equals(e.getPropertyName()))
                throw new RuntimeException();
        });
        setLayout(null);

        //---- signInPanel ----
        signInPanel.setOpaque(false);
        add(signInPanel);
        signInPanel.setBounds(490, 85, 290, 295);

        //---- signIn ----
        signIn.setText("Login");
        signIn.setToolTipText("Already registered?Sign in");
        signIn.addActionListener(this::signIn);
        add(signIn);
        signIn.setBounds(485, 5, 300, 70);

        //---- signUpPanel ----
        signUpPanel.setOpaque(false);
        add(signUpPanel);
        signUpPanel.setBounds(805, 85, signUpPanel.getPreferredSize().width, 400);

        //---- signUp ----
        signUp.setText("Register");
        signUp.setToolTipText("New User? Sign up!");
        signUp.addActionListener(this::signUp);
        add(signUp);
        signUp.setBounds(805, 5, 300, 70);

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
    private SignInPanel signInPanel;
    private JButton signIn;
    private SignUpPanel signUpPanel;
    private JButton signUp;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
