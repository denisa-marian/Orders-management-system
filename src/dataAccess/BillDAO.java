package dataAccess;

import model.Bill;
import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs database operations for Log entries, allowing adding new logs
 */

public class BillDAO {
    /**
     * Inserts a new bill record into the log table
     */
    public void insertBill(Bill bill) {
        String query = "INSERT INTO log (order_id, client_name, product_name, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.quantity());
            statement.setDouble(5, bill.totalPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all bill records from the log table
     */
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM log";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("order_id"),
                        resultSet.getString("client_name"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("total_price")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
