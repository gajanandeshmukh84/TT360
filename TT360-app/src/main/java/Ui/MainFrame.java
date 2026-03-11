package Ui;

import Ui.Panel.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel panel;

    public MainFrame(){
        setTitle("TT360");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        SideBar sideBar = new SideBar(this::showPanel);
        add(sideBar, BorderLayout.WEST);

        JPanel rightContainer = new JPanel(new BorderLayout());
        add(rightContainer, BorderLayout.CENTER);

        TopBar topBar = new TopBar();
        rightContainer.add(topBar, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        rightContainer.add(panel, BorderLayout.CENTER);

        panel.add(new DashBoard(), "dashboard");
        panel.add(new Department(), "department");
        panel.add(new Faculty(), "faculty");
        panel.add(new Subject(), "subject");
        panel.add(new Infrastructure(), "infrastructure");
        panel.add(new Courses(), "courses");
        panel.add(new Scheduler(), "scheduler");
        panel.add(new ViewTT(), "viewTT");

        setVisible(true);
    }

    public void showPanel(String name){
        cardLayout.show(panel, name);
    }
}
