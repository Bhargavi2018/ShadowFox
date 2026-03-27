import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InventorySystem {

    JFrame frame;
    JTextField idField, nameField, priceField, quantityField;
    JButton addBtn, updateBtn, deleteBtn;
    JTable table;
    DefaultTableModel model;

    public InventorySystem() {

        frame = new JFrame("Inventory Management System");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

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
        model.addColumn("Price");
        model.addColumn("Quantity");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(deleteBtn, BorderLayout.SOUTH);

        // ADD
        addBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String price = priceField.getText();
            String quantity = quantityField.getText();

            if(id.isEmpty() || name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields required!");
                return;
            }

            model.addRow(new Object[]{id, name, price, quantity});
            clearFields();
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row!");
                return;
            }

            model.setValueAt(idField.getText(), row, 0);
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(priceField.getText(), row, 2);
            model.setValueAt(quantityField.getText(), row, 3);

            clearFields();
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure?");
            if(confirm == 0) {
                model.removeRow(row);
            }
        });

        // TABLE CLICK
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                priceField.setText(model.getValueAt(row, 2).toString());
                quantityField.setText(model.getValueAt(row, 3).toString());
            }
        });

        frame.setVisible(true);
    }

    void clearFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        new InventorySystem();
    }
}