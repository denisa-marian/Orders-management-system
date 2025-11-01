package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Graphical user interface for managing clients and products
 */

public class View extends JFrame {
    public final JTable clientTable = new JTable();
    public final JTable productTable = new JTable();
    public final JTable orderTable = new JTable();
    public final JTable billTable = new JTable();

    public final JButton addClientBtn = new JButton("Add Client");
    public final JButton editClientBtn = new JButton("Edit Client");
    public final JButton deleteClientBtn = new JButton("Delete Client");
    public final JButton addProductBtn = new JButton("Add Product");
    public final JButton editProductBtn = new JButton("Edit Product");
    public final JButton deleteProductBtn = new JButton("Delete Product");
    public final JButton placeOrderBtn = new JButton("Place Order");
    public final JButton refreshBillsBtn = new JButton("Refresh Bills");

    public final JTextField clientNameField = new JTextField(20);
    public final JTextField clientEmailField = new JTextField(20);
    public final JTextField clientAddressField = new JTextField(20);
    public final JTextField productNameField = new JTextField(20);
    public final JTextField productPriceField = new JTextField(20);
    public final JTextField productStockField = new JTextField(20);
    public final JTextField orderClientIdField = new JTextField(10);
    public final JTextField orderProductIdField = new JTextField(10);
    public final JTextField orderQuantityField = new JTextField(10);

    private final Color bgColor = new Color(209, 193, 241, 255);         // #2C3E50
    private final Color panelColor = new Color(31, 27, 50);      // #34495E
    private final Color accentColor = new Color(237, 236, 241);  // #ECF0F1
    private final Color btnColor = new Color(88, 44, 204, 255);      // #1ABC9C
    private final Color btnHoverColor = new Color(64, 32, 149); // #16A085

    public View() {
        setTitle("Orders Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(bgColor);

        JTabbedPane tabbedPane = new JTabbedPane();
        UIManager.put("TabbedPane.background", bgColor);
        UIManager.put("TabbedPane.selected", panelColor);

        tabbedPane.setBackground(panelColor);
        tabbedPane.setForeground(accentColor);

        tabbedPane.add("Clients", createClientPanel());
        tabbedPane.add("Products", createProductPanel());
        tabbedPane.add("Orders", createOrderPanel());
        tabbedPane.add("Bills", createBillPanel());

        add(tabbedPane, BorderLayout.CENTER);
        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createScrollPaneWithPreferredHeight(clientTable, 300), BorderLayout.CENTER);

        JPanel input = new JPanel(new GridBagLayout());
        input.setBackground(panelColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        input.add(createLabel("Client Name:"), gbc);
        gbc.gridx = 1;
        input.add(clientNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        input.add(createLabel("Client Email:"), gbc);
        gbc.gridx = 1;
        input.add(clientEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        input.add(createLabel("Client Address:"), gbc);
        gbc.gridx = 1;
        input.add(clientAddressField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(panelColor);
        styleButton(addClientBtn);
        styleButton(editClientBtn);
        styleButton(deleteClientBtn);
        buttons.add(addClientBtn);
        buttons.add(editClientBtn);
        buttons.add(deleteClientBtn);

        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setBackground(panelColor);
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createScrollPaneWithPreferredHeight(productTable, 300), BorderLayout.CENTER);

        JPanel input = new JPanel(new GridBagLayout());
        input.setBackground(panelColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        input.add(createLabel("Product Name:"), gbc);
        gbc.gridx = 1;
        input.add(productNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        input.add(createLabel("Price:"), gbc);
        gbc.gridx = 1;
        input.add(productPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        input.add(createLabel("Stock:"), gbc);
        gbc.gridx = 1;
        input.add(productStockField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(panelColor);
        styleButton(addProductBtn);
        styleButton(editProductBtn);
        styleButton(deleteProductBtn);
        buttons.add(addProductBtn);
        buttons.add(editProductBtn);
        buttons.add(deleteProductBtn);

        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setBackground(panelColor);
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createScrollPaneWithPreferredHeight(orderTable, 300), BorderLayout.CENTER);

        JPanel input = new JPanel(new GridBagLayout());
        input.setBackground(panelColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        input.add(createLabel("Client ID:"), gbc);
        gbc.gridx = 1;
        input.add(orderClientIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        input.add(createLabel("Product ID:"), gbc);
        gbc.gridx = 1;
        input.add(orderProductIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        input.add(createLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        input.add(orderQuantityField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBackground(panelColor);
        styleButton(placeOrderBtn);
        buttons.add(placeOrderBtn);

        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setBackground(panelColor);
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBillPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(createScrollPaneWithPreferredHeight(billTable, 300), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setBackground(panelColor);
        styleButton(refreshBillsBtn);
        buttons.add(refreshBillsBtn);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    private JScrollPane createScrollPaneWithPreferredHeight(JTable table, int height) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, height));
        return scrollPane;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(accentColor);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        return label;
    }

    private void styleButton(JButton button) {
        button.setBackground(btnColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(button.getFont().deriveFont(Font.BOLD, 13f));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(btnHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(btnColor);
            }
        });
    }
}
