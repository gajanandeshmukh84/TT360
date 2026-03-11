package Ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class SideBar extends JPanel {

    public SideBar(Consumer<String> onMenuClick){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(25, 42, 86));
        setPreferredSize(new Dimension(220,0));
        setBorder(BorderFactory.createEmptyBorder(20,15,20,15));

        JLabel brand = new JLabel("TT360");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 35));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(brand);

        add(Box.createVerticalStrut(50));

        add(createButton("DashBoard", "dashboard", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Department", "department", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Faculty", "faculty", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Subject", "subject", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Infrastructure", "infrastructure", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Courses", "courses", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("Scheduler", "scheduler", onMenuClick));
        add(Box.createVerticalStrut(4));
        add(createButton("ViewTT", "viewTT", onMenuClick));
    }

    public JButton createButton(String name, String panel, Consumer<String> action){
        JButton button = new JButton(name);
        button.setMaximumSize(new Dimension(190, 45));
        button.setBackground(new Color(40, 60, 120));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addActionListener(e -> action.accept(panel));
        return button;
    }
}
