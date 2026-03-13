package Ui.Panel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Infrastructure extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public Infrastructure() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createTableSection(), BorderLayout.CENTER);

        loadDummyData();
    }

    // HEADER
    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Infrastructure");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel subtitle = new JLabel("Manage rooms and facilities");
        subtitle.setForeground(Color.GRAY);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controls.setBackground(Color.WHITE);

        searchField = new JTextField(15);

        JButton addBtn = new JButton("Add Room");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        controls.add(new JLabel("Search:"));
        controls.add(searchField);
        controls.add(addBtn);
        controls.add(editBtn);
        controls.add(deleteBtn);

        header.add(titlePanel, BorderLayout.WEST);
        header.add(controls, BorderLayout.EAST);

        addBtn.addActionListener(e -> openRoomDialog(null));
        editBtn.addActionListener(e -> editRoom());
        deleteBtn.addActionListener(e -> deleteRoom());

        return header;
    }

    // TABLE
    private JScrollPane createTableSection() {

        String[] columns = {
                "ID",
                "Building",
                "Room Number",
                "Type",
                "Capacity"
        };

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN,13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD,14));

        enableSearch();

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));

        return scroll;
    }

    // SEARCH FUNCTION
    private void enableSearch(){

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {

                String text = searchField.getText();

                if(text.trim().length()==0)
                    sorter.setRowFilter(null);
                else
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
            }
        });
    }

    // ADD / EDIT ROOM
    private void openRoomDialog(Integer rowIndex){

        JTextField building = new JTextField();
        JTextField room = new JTextField();
        JTextField type = new JTextField();
        JTextField capacity = new JTextField();

        if(rowIndex != null){

            building.setText(model.getValueAt(rowIndex,1).toString());
            room.setText(model.getValueAt(rowIndex,2).toString());
            type.setText(model.getValueAt(rowIndex,3).toString());
            capacity.setText(model.getValueAt(rowIndex,4).toString());
        }

        Object[] fields = {
                "Building:", building,
                "Room Number:", room,
                "Type:", type,
                "Capacity:", capacity
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                rowIndex==null ? "Add Room" : "Edit Room",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if(option == JOptionPane.OK_OPTION){

            if(rowIndex == null){

                model.addRow(new Object[]{
                        model.getRowCount()+1,
                        building.getText(),
                        room.getText(),
                        type.getText(),
                        capacity.getText()
                });

            } else {

                model.setValueAt(building.getText(), rowIndex,1);
                model.setValueAt(room.getText(), rowIndex,2);
                model.setValueAt(type.getText(), rowIndex,3);
                model.setValueAt(capacity.getText(), rowIndex,4);
            }
        }
    }

    // EDIT
    private void editRoom(){

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(this,
                    "Select a room to edit",
                    "Warning",
                    JOptionPane.PLAIN_MESSAGE);
            return;
        }

        row = table.convertRowIndexToModel(row);

        openRoomDialog(row);
    }

    // DELETE
    private void deleteRoom(){

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(this,
                    "Select a room to delete",
                    "Warning",
                    JOptionPane.PLAIN_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this room?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if(confirm == JOptionPane.YES_OPTION){

            row = table.convertRowIndexToModel(row);
            model.removeRow(row);
        }
    }

    // DUMMY data
    private void loadDummyData(){

        model.addRow(new Object[]{1,"ECO-Building","1104","Classroom",60});
        model.addRow(new Object[]{2,"ECO-Building","1102","Lab",60});

    }
}
