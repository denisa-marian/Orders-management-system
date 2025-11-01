package businessLogic;

import dataAccess.BillDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Bill;
import model.Order;
import model.Product;
import java.util.List;

/**
 * Handles business logic related to orders, including placing orders, updating product stock, and generating bills.
 */
public class OrderBLL {
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final BillDAO billDAO = new BillDAO();

    /**
     * Places an order if the product has sufficient stock
     * Updates the product stock and generates a bill
     * return true if the order was successfully placed, false otherwise
     */
    public boolean placeOrder(Order order, String clientName, String productName) {
        Product product = productDAO.findProductById(order.getProductId());
        if (product.getStock() >= order.getQuantity()) {
            int orderId = orderDAO.insertOrder(order);
            if (orderId == -1) return false;

            int newStock = product.getStock() - order.getQuantity();
            productDAO.updateProduct(new Product(product.getId(), product.getName(), newStock, product.getPrice()));

            Bill bill = new Bill(orderId, clientName, productName, order.getQuantity(), product.getPrice() * order.getQuantity());
            billDAO.insertBill(bill);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves all existing orders from the database
     */
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}
