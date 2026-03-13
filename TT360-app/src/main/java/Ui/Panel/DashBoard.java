package Ui.Panel;

import Ui.ShadowPanel;
import javax.swing.*;
import java.awt.*;

public class DashBoard extends JPanel {

    int totalFaculty = 100;
    String totalFacultyString = String.valueOf(totalFaculty);

    int totalClassroom = 11;
    String getTotalclassroomString = String.valueOf(totalClassroom);

    int courseSubjects = 26;
    String courseSubjectsString = String.valueOf(courseSubjects);

    int totalCourses = 7;
    String totalCoursesString = String.valueOf(totalCourses);

    public DashBoard(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(209, 211, 217));
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        add(createCard("Total Faculty", totalFacultyString));
        add(createCard("Total Classroom(s)", getTotalclassroomString));
        add(createCard("Course Subject(s)", courseSubjectsString));
        add(createCard("Total Course(s)", totalCoursesString));
    }

    public JPanel createCard(String title, String value){
        ShadowPanel card = new ShadowPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(315,120));
        card.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Segeo UI", Font.PLAIN, 13));

        JLabel cardValue = new JLabel(value);
        cardValue.setFont(new Font("Segeo UI", Font.BOLD, 35));

        card.add(cardTitle, "North");
        card.add(cardValue, "Center");

        return card;
    }
}
