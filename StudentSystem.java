import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentSystem {

    JFrame frame;
    JTextField idField, nameField, marksField;
    JButton addBtn, updateBtn, deleteBtn;
    JTable table;
    DefaultTableModel model;

    public StudentSystem() {

        frame = new JFrame("Student Information System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel (Input Fields)
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        panel.add(marksField);

        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");

        panel.add(addBtn);
        panel.add(updateBtn);

        frame.add(panel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Marks");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(deleteBtn, BorderLayout.SOUTH);

        // Add Button
        addBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String marks = marksField.getText();

            if(id.isEmpty() || name.isEmpty() || marks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields required!");
                return;
            }

            model.addRow(new Object[]{id, name, marks});

            clearFields();
        });

        // Update Button
        updateBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row to update!");
                return;
            }

            model.setValueAt(idField.getText(), selectedRow, 0);
            model.setValueAt(nameField.getText(), selectedRow, 1);
            model.setValueAt(marksField.getText(), selectedRow, 2);

            clearFields();
        });

        // Delete Button
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure?");
            if(confirm == 0) {
                model.removeRow(selectedRow);
            }
        });

        // Table click → load data into fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                marksField.setText(model.getValueAt(row, 2).toString());
            }
        });

        frame.setVisible(true);
    }

    void clearFields() {
        idField.setText("");
        nameField.setText("");
        marksField.setText("");
    }

    public static void main(String[] args) {
        new StudentSystem();
    }
}