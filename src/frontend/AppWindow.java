package frontend;

import backend.application.Application;
import config.Configurations;
import frontend.ConnectUI.ConnectPanel;
import frontend.Inbox.InboxView;
import frontend.usersgui.ProfileView;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    private static AppWindow instance;
    private AppWindow() {
        initSettings();
    }

    public static AppWindow getInstance() {
        if (instance == null)
            instance = new AppWindow();
        return instance;
    }

    private void initSettings() {
        setTitle("Booking Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(Configurations.WINDOW_WIDTH, Configurations.WINDOW_HEIGHT));
        getContentPane().add(new ConnectPanel());
        setResizable(false);
        setVisible(true);
    }

    public void changePanel(JPanel newPanel) {
        getContentPane().removeAll();
        newPanel.setVisible(true);
        getContentPane().add(newPanel);
        revalidate();
    }


    public void signOut() {
        String message = "This will return you to the login screen,are you sure you want to continue?";
        int result = JOptionPane.showConfirmDialog(null, message, "Are you sure?", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            changePanel(new ConnectPanel());
            Application.getInstance().setConnected(false);
            InboxView.closeView();
            ProfileView.closeView();
        }

    }
}
