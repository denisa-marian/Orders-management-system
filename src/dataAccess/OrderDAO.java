package dataAccess;

import model.Order;
import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the insertion of new Order records into the database
 */

public class OrderDAO {

    /**
     * Inserts a new order record into the database and returns the generated ID
     */
    public int insertOrder(Order order) {
        String query = "INSERT INTO OrderTable (client_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Retrieves all order records from the database
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM OrderTable";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
