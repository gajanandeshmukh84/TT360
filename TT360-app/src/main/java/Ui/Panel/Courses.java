package Ui.Panel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Courses extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public Courses() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createTableSection(), BorderLayout.CENTER);

        loadDummyData(); // <-- dummy data added here
    }

    // HEADER
    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Courses");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel subtitle = new JLabel("Manage academic courses offered");
        subtitle.setForeground(Color.GRAY);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        JPanel rightControls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightControls.setBackground(Color.WHITE);

        searchField = new JTextField(15);

        JButton addBtn = new JButton("Add Course");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        rightControls.add(new JLabel("Search:"));
        rightControls.add(searchField);
        rightControls.add(addBtn);
        rightControls.add(editBtn);
        rightControls.add(deleteBtn);

        header.add(titlePanel, BorderLayout.WEST);
        header.add(rightControls, BorderLayout.EAST);

        addBtn.addActionListener(e -> openCourseDialog(null));
        editBtn.addActionListener(e -> editCourse());
        deleteBtn.addActionListener(e -> deleteCourse());

        return header;
    }

    // TABLE SECTION
    private JScrollPane createTableSection() {

        String[] columns = {
                "ID",
                "Department",
                "Course Name",
                "Abbreviation",
                "Specialization"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        enableSearch();

        return scroll;
    }

    // SEARCH FUNCTION
    private void enableSearch() {

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {

                String text = searchField.getText();

                if (text.trim().length() == 0)
                    sorter.setRowFilter(null);
                else
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });
    }

    // ADD / EDIT DIALOG
    private void openCourseDialog(Integer rowIndex) {

        JTextField dept = new JTextField();
        JTextField cname = new JTextField();
        JTextField abbr = new JTextField();
        JTextField spec = new JTextField();

        if (rowIndex != null) {

            dept.setText(model.getValueAt(rowIndex, 1).toString());
            cname.setText(model.getValueAt(rowIndex, 2).toString());
            abbr.setText(model.getValueAt(rowIndex, 3).toString());
            spec.setText(model.getValueAt(rowIndex, 4).toString());
        }

        Object[] fields = {
                "Department:", dept,
                "Course Name:", cname,
                "Abbreviation:", abbr,
                "Specialization:", spec
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                rowIndex == null ? "Add Course" : "Edit Course",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            if (rowIndex == null) {

                model.addRow(new Object[]{
                        model.getRowCount() + 1,
                        dept.getText(),
                        cname.getText(),
                        abbr.getText(),
                        spec.getText()
                });

            } else {

                model.setValueAt(dept.getText(), rowIndex, 1);
                model.setValueAt(cname.getText(), rowIndex, 2);
                model.setValueAt(abbr.getText(), rowIndex, 3);
                model.setValueAt(spec.getText(), rowIndex, 4);
            }
        }
    }

    // EDIT COURSE
    private void editCourse() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a course to edit");

            return;
        }

        row = table.convertRowIndexToModel(row);

        openCourseDialog(row);
    }

    // DELETE COURSE
    private void deleteCourse() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a course to delete");

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this course?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            row = table.convertRowIndexToModel(row);
            model.removeRow(row);
        }
    }

    // DUMMY DATA
    private void loadDummyData() {

        model.addRow(new Object[]{1, "Commerce And Management", "Masters of Computer Application", "MCA", "Cloud Computing"});
        model.addRow(new Object[]{2, "Commerce And Management", "Masters of Business Administration", "MBA", "Dual"});

    }
}