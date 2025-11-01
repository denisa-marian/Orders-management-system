package presentation;

import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import dataAccess.BillDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.util.List;

/**
 * Connects the user interface with the business logic and data layers.
 */

public class Controller {
    private final View view;
    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();
    private final OrderBLL orderBLL = new OrderBLL();
    private final BillDAO billDAO = new BillDAO();


    public Controller(View view) {
        this.view = view;
        initialize();
    }

    /**
     * Initializes the UI event listeners for buttons and table selections,
     * and refreshes all data tables
     */
    private void initialize() {
        view.addClientBtn.addActionListener(e -> {
            String name = view.clientNameField.getText();
            String email = view.clientEmailField.getText();
            String address = view.clientAddressField.getText();
            clientBLL.addClient(new Client(name, email, address));
            refreshClients();
        });

        view.editClientBtn.addActionListener(e -> {
            int row = view.clientTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) view.clientTable.getValueAt(row, 0);
                String name = view.clientNameField.getText();
                String email = view.clientEmailField.getText();
                String address = view.clientAddressField.getText();
                clientBLL.updateClient(new Client(id, name, email, address));
                refreshClients();
            }
        });

        view.deleteClientBtn.addActionListener(e -> {
            int row = view.clientTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) view.clientTable.getValueAt(row, 0);
                clientBLL.deleteClient(id);
                refreshClients();
            }
        });

        view.clientTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = view.clientTable.getSelectedRow();
                if (row >= 0) {
                    view.clientNameField.setText(view.clientTable.getValueAt(row, 1).toString());
                    view.clientEmailField.setText(view.clientTable.getValueAt(row, 2).toString());
                    view.clientAddressField.setText(view.clientTable.getValueAt(row, 3).toString());
                }
            }
        });

        view.addProductBtn.addActionListener(e -> {
            String name = view.productNameField.getText();
            int stock = (int) Double.parseDouble(view.productStockField.getText());
            double price = Double.parseDouble(view.productPriceField.getText());
            productBLL.addProduct(new Product(name, stock, price));
            refreshProducts();
        });

        view.editProductBtn.addActionListener(e -> {
            int row = view.productTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) view.productTable.getValueAt(row, 0);
                String name = view.productNameField.getText();
                int stock = (int) Double.parseDouble(view.productStockField.getText());
                double price = Double.parseDouble(view.productPriceField.getText());
                productBLL.updateProduct(new Product(id, name, stock, price));
                refreshProducts();
            }
        });

        view.deleteProductBtn.addActionListener(e -> {
            int row = view.productTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) view.productTable.getValueAt(row, 0);
                productBLL.deleteProduct(id);
                refreshProducts();
            }
        });

        view.productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = view.productTable.getSelectedRow();
                if (row >= 0) {
                    view.productNameField.setText(view.productTable.getValueAt(row, 1).toString());
                    view.productStockField.setText(view.productTable.getValueAt(row, 2).toString());
                    view.productPriceField.setText(view.productTable.getValueAt(row, 3).toString());

                }
            }
        });

        view.placeOrderBtn.addActionListener(e -> {
            int clientId = Integer.parseInt(view.orderClientIdField.getText());
            int productId = Integer.parseInt(view.orderProductIdField.getText());
            int quantity = Integer.parseInt(view.orderQuantityField.getText());

            Product product = productBLL.getProductById(productId);
            List<Client> clients = clientBLL.getAllClients();
            String clientName = clients.stream()
                    .filter(c -> c.getId() == clientId)
                    .map(Client::getName)
                    .findFirst()
                    .orElse("Unknown");

            if (orderBLL.placeOrder(new Order(clientId, productId, quantity), clientName, product.getName())) {
                refreshOrders();
                refreshProducts();
            } else {
                JOptionPane.showMessageDialog(view, "Insufficient stock!");
            }
        });

        view.refreshBillsBtn.addActionListener(e -> refreshBills());

        refreshClients();
        refreshProducts();
        refreshOrders();
        refreshBills();
    }

    private void refreshClients() {
        List<Client> clients = clientBLL.getAllClients();
        view.clientTable.setModel(TableGenerator.generateTable(clients));
    }

    private void refreshProducts() {
        List<Product> products = productBLL.getAllProducts();
        view.productTable.setModel(TableGenerator.generateTable(products));
    }

    private void refreshOrders() {
        List<Order> orders = orderBLL.getAllOrders();
        view.orderTable.setModel(TableGenerator.generateTable(orders));
    }

    private void refreshBills() {
        List<Bill> bills = billDAO.getAllBills();
        view.billTable.setModel(TableGenerator.generateTable(bills));
    }

}
